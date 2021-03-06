package com.jeff;

import com.jeff.ioc.beandefinition.AbstractBeanDefinitionReader;
import com.jeff.ioc.beandefinition.XmlBeanDefinitionReader;
import org.junit.Test;

public class BeanDefinitionTest {

    @Test
    public void loadBeanDefinitionsTest() {
        try {
            AbstractBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader();
            beanDefinitionReader.loadBeanDefinitions("spring-ioc.xml");
            System.out.println(beanDefinitionReader.getRegistry().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
