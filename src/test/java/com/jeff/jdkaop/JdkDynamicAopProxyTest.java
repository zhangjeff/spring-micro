package com.jeff.jdkaop;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import com.jeff.test.aop.HelloWorldService;
import org.junit.Test;

public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
