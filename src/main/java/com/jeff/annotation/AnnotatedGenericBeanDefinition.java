package com.jeff.annotation;

import com.jeff.ioc.beandefinition.BeanDefinition;

public class AnnotatedGenericBeanDefinition extends BeanDefinition {

    public AnnotatedGenericBeanDefinition(Class<?> beanClass) {
        setBeanClass(beanClass);
    }
}
