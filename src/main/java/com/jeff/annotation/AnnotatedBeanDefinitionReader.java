package com.jeff.annotation;

import com.jeff.annotation.stereotype.AnnotationMetadata;
import com.jeff.annotation.stereotype.StandardAnnotationMetadata;
import com.jeff.ioc.beandefinition.BeanDefinition;

public class AnnotatedBeanDefinitionReader {
//    private Annotation[] annotations;

    private BeanDefinitionHolder beanDefinitionHolder;

    private AnnotationMetadata metadata;

    public void register(Class<?>... annotatedClasses) {
        for (Class<?> annotatedClass : annotatedClasses) {
            registerBean(annotatedClass);
        }
    }

    public void registerBean(Class<?> annotatedClass){
//        annotations = annotatedClass.getAnnotations();
        AnnotatedGenericBeanDefinition bcd = new AnnotatedGenericBeanDefinition(annotatedClass);
//        metadata = new StandardAnnotationMetadata(annotatedClass);
        beanDefinitionHolder = new BeanDefinitionHolder(bcd.getBeanClass().getSimpleName(), bcd);
    }

    public void registerBeanDefinition(AnnotationMetadata metadata){

    }
    public BeanDefinitionHolder getBeanDefinitionHolder() {
        return beanDefinitionHolder;
    }

    public void setBeanDefinitionHolder(BeanDefinitionHolder beanDefinitionHolder) {
        this.beanDefinitionHolder = beanDefinitionHolder;
    }
}
