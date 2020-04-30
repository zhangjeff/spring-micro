package com.jeff.jdkaopExample;

public class RealSubject implements Subject {
    @Override
    public Subject rent() {
        System.out.println("I want to rent my house");
        return null;
    }

    @Override
    public Subject hello(String str) {
        System.out.println("hello: " + str);
        return null;
    }
}
