package com.jeff;

import com.jeff.ioc.beans.context.ApplicationContext;
import com.jeff.ioc.beans.context.ClassPathXmlApplicationContext;
import com.jeff.test.HelloPropertyService;
import com.jeff.test.HelloService;
import org.junit.Test;

public class ClassPathXmlApplicationContextTest {

    @Test
    public void testGetName() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");
        HelloService helloService = (HelloService)applicationContext.getBean("helloService");
        helloService.output("hello world!");
    }

    @Test
    public void testBeanProperty() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");
        HelloPropertyService helloPropertyService = (HelloPropertyService)applicationContext.getBean("helloPropertyService");
        helloPropertyService.output();
    }

    @Test
    public void testInitMethod() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");
        HelloPropertyService helloPropertyService = (HelloPropertyService)applicationContext.getBean("helloPropertyService");
        helloPropertyService.output();
    }
}
