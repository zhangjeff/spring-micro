package com.jeff.annotation;

import com.jeff.test.annotation.NewHelloWord;
import org.junit.Test;

public class AnnotationTest {
    @Test
    public void testController() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(NewHelloWord.class);

        ctx.refresh();
        NewHelloWord newHelloWord = ctx.getBean(NewHelloWord.class);
        newHelloWord.sayHi();
    }
}
