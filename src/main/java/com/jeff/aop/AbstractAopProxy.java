package com.jeff.aop;

public abstract class AbstractAopProxy implements AopProxy {



    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised){
        this.advised = advised;
    }
}
