package com.jeff.jdkaopExample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new DynamicProxy(realSubject);
//        handler = new DynamicProxy2(handler);
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);

//        System.out.println(subject.getClass().getName());

//        for (Class<?> interfaceType : subject.getClass().getInterfaces()) {
//            System.out.println("- " + interfaceType);
//        }


        subject.rent().hello("333333");
    }
}
