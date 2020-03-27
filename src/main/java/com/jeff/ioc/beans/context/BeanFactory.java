package com.jeff.ioc.beans.context;

public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
