package com.jeff.ioc.beandefinition;

import lombok.Data;

@Data
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name){
        this.name = name;
    }
}
