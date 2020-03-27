package com.jeff.ioc.beans.beandefinition;

import com.jeff.ioc.beans.beandefinition.BeanDefinition;
import com.jeff.ioc.beans.beandefinition.BeanDefinitionReader;

import java.util.HashMap;
import java.util.Map;

public abstract  class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public AbstractBeanDefinitionReader(){
        registry = new HashMap();
    }
}
