package com.jeff.ioc.beans.context;

import com.jeff.ioc.beans.beandefinition.BeanDefinition;
import com.jeff.ioc.beans.postProcessor.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList();

    private final List<String> beanDefinitionNames = new ArrayList<String>();

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    @Override
    public Object getBean(String name) throws Exception {
        Object bean = singletonObjects.get(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new Exception("No bean named " + name + " is defined");
        }
//        bean = beanDefinition.getBean();
//        if (bean == null) {
        bean = doCreateBean(beanDefinition);
        bean = initializeBean(bean, name, beanDefinition);
//        }

        if (beanDefinition.getScope() == null || beanDefinition.getScope() == "" || "singleton".equals(beanDefinition.getScope())) {
            singletonObjects.put(name, bean);
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
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
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