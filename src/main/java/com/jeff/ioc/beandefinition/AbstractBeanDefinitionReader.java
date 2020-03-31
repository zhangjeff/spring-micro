package com.jeff.ioc.beandefinition;

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
