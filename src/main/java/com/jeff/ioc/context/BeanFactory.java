package com.jeff.ioc.context;

public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
