package com.jeff.ioc.beans.context;

import com.jeff.ioc.beans.beandefinition.AbstractBeanDefinitionReader;
import com.jeff.ioc.beans.beandefinition.BeanDefinition;
import com.jeff.ioc.beans.beandefinition.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception{
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader();
        beanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinition : beanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
        }
    }
}
