package com.jeff.awareTest;


import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ApplicationContextAware;

public class Abean implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext) throws Exception {
        this.applicationContext = applicationContext;
    }

    public void helloword(){
        System.out.println("hello world!");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
