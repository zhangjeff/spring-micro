package com.jeff.ioc.beandefinition;

import lombok.Data;

@Data
public class BeanDefinition {
    private Object bean;

    private Class beanClass;

    private String beanClassName;

    public String  initMethod;

    public String scope;

    private PropertyValues propertyValues = new PropertyValues();

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;

        try {
            beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public String getBeanClassName(){
//        return beanClassName;
//    }
}