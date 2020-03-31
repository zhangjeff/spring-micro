package com.jeff.aop;

public interface MethodInterceptor {

    Object invoke(MethodInvocation invocation) throws Throwable;
}
