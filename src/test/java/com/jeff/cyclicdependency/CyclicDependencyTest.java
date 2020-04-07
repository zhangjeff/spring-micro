package com.jeff.cyclicdependency;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import com.jeff.test.cyclicdependency.AService;
import org.junit.Test;

public class CyclicDependencyTest {

    @Test
    public void testCyclicDependency() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-cyclic.xml");
        AService aService = (AService) applicationContext.getBean("aService");
        aService.helloWorld();
    }
}
