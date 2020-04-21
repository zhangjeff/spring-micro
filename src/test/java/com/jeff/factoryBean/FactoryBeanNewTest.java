package com.jeff.factoryBean;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import com.jeff.test.factoryBean.Car;
import com.jeff.test.factoryBean.FactoryBeanTest;
import org.junit.Test;

public class FactoryBeanNewTest {

    @Test
    public void testFactoryBean() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-factoryBean.xml");
        Car car = (Car)applicationContext.getBean("factoryBeanTest");
        car.setColorName("222");
    }
}
