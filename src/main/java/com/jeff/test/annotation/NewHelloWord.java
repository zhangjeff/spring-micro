package com.jeff.test.annotation;

import com.jeff.annotation.stereotype.Component;
import com.jeff.annotation.stereotype.Controller;

//@Controller
@Component
public class NewHelloWord {

    public void sayHi(){
        System.out.println("hi");
    }
}