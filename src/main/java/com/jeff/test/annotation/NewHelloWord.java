package com.jeff.test.annotation;

import com.jeff.annotation.stereotype.Controller;

@Controller
public class NewHelloWord {

    public void sayHi(){
        System.out.println("hi");
    }
}