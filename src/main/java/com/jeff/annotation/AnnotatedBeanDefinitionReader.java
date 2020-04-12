package com.jeff.annotation;

import com.jeff.annotation.stereotype.AnnotationMetadata;
import java.util.HashSet;
import java.util.Set;

public class AnnotatedBeanDefinitionReader {
//    private Annotation[] annotations;


    private Set<BeanDefinitionHolder> beanDefinitionHolder;

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
        beanDefinitionHolder = new HashSet<>();
        beanDefinitionHolder.add(new BeanDefinitionHolder(bcd.getBeanClass().getSimpleName(), bcd));
    }

    public void registerBeanDefinition(AnnotationMetadata metadata){

    }
    public Set<BeanDefinitionHolder> getBeanDefinitionHolder() {
        return beanDefinitionHolder;
    }

    public void setBeanDefinitionHolder(Set<BeanDefinitionHolder> beanDefinitionHolder) {
        this.beanDefinitionHolder = beanDefinitionHolder;
    }

}
