package com.jeff.annotation;

import com.jeff.ioc.beandefinition.BeanDefinition;

public class BeanDefinitionHolder {

    private final BeanDefinition beanDefinition;

    private final String beanName;

    public BeanDefinitionHolder(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }

}
