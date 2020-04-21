package com.jeff.ioc.context;

public interface FactoryBean<T> {

    T getObject();

    boolean isSingleton();
}
