package com.jeff.jdkaopExample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy2 implements InvocationHandler {

    private Object subject;

    public DynamicProxy2(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before rent house---------");

        System.out.println("Method:-------" + method);

        method.invoke(subject, args);
        System.out.println("after rent house-------------");

        return proxy;
    }
}
