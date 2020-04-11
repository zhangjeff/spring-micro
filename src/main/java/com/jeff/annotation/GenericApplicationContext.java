package com.jeff.annotation;

import com.jeff.ioc.beandefinition.AbstractBeanDefinitionReader;
import com.jeff.ioc.beandefinition.BeanDefinition;
import com.jeff.ioc.beandefinition.XmlBeanDefinitionReader;
import com.jeff.ioc.context.AbstractApplicationContext;
import com.jeff.ioc.context.AbstractBeanFactory;
import com.jeff.ioc.context.AutowireCapableBeanFactory;

import java.util.Map;

/**
 * @author zhangying
 */
public class GenericApplicationContext extends AbstractApplicationContext {

    public GenericApplicationContext() {
        this(new AutowireCapableBeanFactory());
    }

    public GenericApplicationContext(AbstractBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {

//        AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader();
//        beanDefinitionReader.loadBeanDefinitions(configLocation);
//        for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitionReader.getRegistry().entrySet()){
//            beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
//        }
    }


    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }
}
