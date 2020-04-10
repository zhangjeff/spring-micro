package com.jeff.annotation;

import com.jeff.ioc.beandefinition.AbstractBeanDefinitionReader;
import com.jeff.ioc.beandefinition.BeanDefinition;
import com.jeff.ioc.beandefinition.XmlBeanDefinitionReader;
import com.jeff.ioc.context.AbstractApplicationContext;
import com.jeff.ioc.context.AbstractBeanFactory;

import java.util.Map;

public class DefaultListableBeanFactory extends AbstractBeanFactory {

    private String configLocation;

}
