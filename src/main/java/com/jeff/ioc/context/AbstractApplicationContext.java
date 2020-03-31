package com.jeff.ioc.context;

import com.jeff.ioc.postProcessor.BeanPostProcessor;

import java.util.List;

public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String name) throws Exception{
        return beanFactory.getBean(name);
    }

    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);

        finishBeanFactoryInitialization(beanFactory);
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessorList = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessorList) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor)beanPostProcessor);
        }
    }
    //实例化单例模式的bean
    public void finishBeanFactoryInitialization(AbstractBeanFactory beanFactory) throws Exception {
        beanFactory.preInstantiateSingletons();
    }
}
