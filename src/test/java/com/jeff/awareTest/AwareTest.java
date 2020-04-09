package com.jeff.awareTest;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class AwareTest {

    @Test
    public void testApplicationAware() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("awarebeans.xml");
        Abean abean = (Abean) context.getBean("abean");
        abean.helloword();
        ApplicationContext applicationContext = abean.getApplicationContext();
        Bbean bbean = (Bbean) applicationContext.getBean("bbean");
        bbean.helloword();

    }
}
