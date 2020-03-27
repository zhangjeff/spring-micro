package com.jeff.interfaceTest;

public class AbBeanDefinition implements BeanDefinition, BeanInfterface {

    private String scope;

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope=scope;
    }
}
