package com.jeff.interfaceTest;

public class InterFaceTest {

    public static void main(String[] args) {
        BeanDefinition beanDefinition = new AbBeanDefinition();
        beanDefinition.setScope("aaaaa");
        System.out.println(beanDefinition.getScope());
    }
}
