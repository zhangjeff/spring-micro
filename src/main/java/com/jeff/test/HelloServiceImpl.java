package com.jeff.test;

public class HelloServiceImpl implements HelloService {
    @Override
    public void output(String text){
        System.out.println(text);
    }
}
