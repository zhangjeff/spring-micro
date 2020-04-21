package com.jeff.test.factoryBean;

import com.jeff.ioc.context.FactoryBean;

public class FactoryBeanTest implements FactoryBean {
    @Override
    public Object getObject() {
        return new Car();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
