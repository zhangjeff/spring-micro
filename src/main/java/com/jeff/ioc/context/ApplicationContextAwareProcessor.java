package com.jeff.ioc.context;

import com.jeff.ioc.postProcessor.BeanPostProcessor;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        invokeAwareInterfaces(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    private void invokeAwareInterfaces(Object bean) throws Exception{
        if (bean instanceof Aware) {
            if (bean instanceof ApplicationContextAware) {
                ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
            }
        }
    }
}
