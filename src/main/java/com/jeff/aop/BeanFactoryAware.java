package com.jeff.aop;

import com.jeff.ioc.context.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
