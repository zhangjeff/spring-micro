package com.jeff.jdkaop;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import com.jeff.test.HelloService;
import org.junit.Test;

public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor() throws Exception{
        System.out.println("---------------");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        HelloService helloWorldService = (HelloService) applicationContext.getBean("helloService");
    }
}
