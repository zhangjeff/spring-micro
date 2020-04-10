package com.jeff.annotation;

import com.jeff.ioc.context.AbstractBeanFactory;

public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

    private final AnnotatedBeanDefinitionReader reader;

    @Override
    public void register(Class<?>... annotatedClasses) {

    }

    @Override
    public void scan(String... basePackages) {

    }

    public AnnotationConfigApplicationContext(){
        this.reader = new AnnotatedBeanDefinitionReader();
    }

    public AnnotationConfigApplicationContext(AbstractBeanFactory beanFactory){
        super(beanFactory);
        this.reader = new AnnotatedBeanDefinitionReader();
    }

    public AnnotationConfigApplicationContext(String... basePackages) throws Exception {
        this();
        scan(basePackages);
        refresh();
    }
}
