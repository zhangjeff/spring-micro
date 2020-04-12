package com.jeff.annotation;

import com.jeff.test.annotation.NewHelloWord;
import org.junit.Test;

public class AnnotationTest {
    @Test
    public void testController() throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.jeff.test.annotation");

//        ctx.register(NewHelloWord.class);
//
//        ctx.refresh();
        NewHelloWord newHelloWord = (NewHelloWord)ctx.getBean("newHelloWord");
        newHelloWord.sayHi();
    }
}
