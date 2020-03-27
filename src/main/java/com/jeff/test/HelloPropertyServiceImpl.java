package com.jeff.test;

public class HelloPropertyServiceImpl implements HelloPropertyService{

    private String text;

    @Override
    public void output() {
        System.out.println(text);
    }

    public void setText(String text){
        this.text = text;
    }

    public void myInit(){
        System.out.println("this is a test initialize method!");
    }
}
