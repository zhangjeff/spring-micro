package com.jeff.test;

import com.jeff.ioc.beans.postProcessor.BeanPostProcessor;

public class BeanPostLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("postProcessBeforeInitialization test ---start");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("postProcessAfterInitialization test ---end");
        return bean;
    }
}
