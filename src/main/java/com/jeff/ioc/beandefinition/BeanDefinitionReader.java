package com.jeff.ioc.beandefinition;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
