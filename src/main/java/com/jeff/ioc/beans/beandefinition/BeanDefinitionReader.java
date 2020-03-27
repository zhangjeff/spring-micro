package com.jeff.ioc.beans.beandefinition;

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
