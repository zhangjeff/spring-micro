package com.jeff.annotation;

import com.jeff.ioc.beandefinition.BeanDefinition;
import com.jeff.ioc.context.AbstractBeanFactory;

import java.util.Set;

public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

    private final AnnotatedBeanDefinitionReader reader;

    private final ClassPathBeanDefinitionScanner scanner;

    @Override
    public void register(Class<?>... annotatedClasses) {
        reader.register(annotatedClasses);
    }

    @Override
    public void scan(String... basePackages) {
        try {
            Set<BeanDefinitionHolder> beanDefinitionHolderSet = scanner.scan(basePackages);
            reader.setBeanDefinitionHolder(beanDefinitionHolderSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnnotationConfigApplicationContext(){
        this.reader = new AnnotatedBeanDefinitionReader();
        this.scanner = new ClassPathBeanDefinitionScanner();
    }


    public AnnotationConfigApplicationContext(AbstractBeanFactory beanFactory){
        super(beanFactory);
        this.reader = new AnnotatedBeanDefinitionReader();
        this.scanner = new ClassPathBeanDefinitionScanner();
    }

    public AnnotationConfigApplicationContext(String... basePackages) throws Exception {
        this();
        scan(basePackages);
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {

        Set<BeanDefinitionHolder> beanDefinitionHolders = reader.getBeanDefinitionHolder();
        for(BeanDefinitionHolder bd : beanDefinitionHolders) {
            beanFactory.registerBeanDefinition(bd.getBeanName(), bd.getBeanDefinition());
        }
    }
}
