package com.jeff.ioc.context;

import com.jeff.ioc.beandefinition.BeanDefinition;
import com.jeff.ioc.postProcessor.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList();

    private final List<String> beanDefinitionNames = new ArrayList<String>();

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    private final Set<String> singletonsCurrentlyInCreation =
            Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>(16));

    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    private final Map<String, BeanFactory> singletonFactories = new HashMap<String, BeanFactory>(16);

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new Exception("No bean named " + name + " is defined");
        }

        if (beanDefinition.getScope() == null || beanDefinition.getScope() == "") {
            beanDefinition.setScope("singleton");
        }

        Object bean = singletonObjects.get(name);
        if (bean != null) {
            return bean;
        }

        if(singletonsCurrentlyInCreation.contains(name)){
            bean = earlySingletonObjects.get(name);
            if (bean == null) {
                bean = singletonFactories.get(beanDefinition.getBeanClass().getSimpleName()).getBean(name);
                earlySingletonObjects.put(name, bean);
            }
            return bean;
        }

        if ("singleton".equals(beanDefinition.getScope())) {
            singletonsCurrentlyInCreation.add(name);
        }

        bean = doCreateBean(beanDefinition);
        bean = initializeBean(bean, name, beanDefinition);
        if ("singleton".equals(beanDefinition.getScope())) {
            singletonObjects.put(name, bean);
            singletonFactories.remove(beanDefinition.getBeanClass().getSimpleName());
            singletonsCurrentlyInCreation.remove(name);
        }
        return bean;
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = beanDefinition.getBeanClass().newInstance();
//        beanDefinition.setBean(bean);
        String beanName = beanDefinition.getBeanClass().getSimpleName();
        addSingletonFactory(beanName, new BeanFactory() {
            @Override
            public Object getBean(String name) throws Exception {
                return getEarlyBeanReference(name,beanDefinition,bean);
            }
        });
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void addSingletonFactory(String beanName, BeanFactory singletonFactory) {
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonFactories.put(beanName, singletonFactory);
                this.earlySingletonObjects.remove(beanName);
            }
        }
    }

    protected Object getEarlyBeanReference(String beanName, BeanDefinition mbd, Object bean) {
        Object exposedObject = bean;
        if (bean != null) {
//            for (BeanPostProcessor bp : getBeanPostProcessors()) {
//                if (bp instanceof SmartInstantiationAwareBeanPostProcessor) {
//                    SmartInstantiationAwareBeanPostProcessor ibp = (SmartInstantiationAwareBeanPostProcessor) bp;
//                    exposedObject = ibp.getEarlyBeanReference(exposedObject, beanName);
//                    if (exposedObject == null) {
//                        return exposedObject;
//                    }
//                }
//            }
        }
        return exposedObject;
    }

    public Object initializeBean(Object bean, String name, BeanDefinition beanDefinition) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        String initMethodname = beanDefinition.getInitMethod();
        if (initMethodname != null && initMethodname != "") {
            Method initMethod = bean.getClass().getDeclaredMethod(initMethodname);
            initMethod.invoke(bean);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class clazz) throws Exception {
        List beans = new ArrayList<Object>();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (clazz.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void preInstantiateSingletons() throws Exception {
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope() == null || beanDefinition.getScope() == "") {
                beanDefinition.setScope("singleton");
            }
            if ("singleton".equals(beanDefinition.getScope())) {
                getBean(beanName);
            }
        }
    }
}