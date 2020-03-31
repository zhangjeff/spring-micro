package com.jeff;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import com.jeff.test.Car;
import org.junit.Test;

public class BeanPropertyTest {

    /**
     * Jeff
     * 2020年03月27日10:56:37
     */
    @Test
    public void testProperty() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-property.xml");
        Car car1 = (Car)applicationContext.getBean("car");
        car1.setBrand("2222");
        Car car2 = (Car)applicationContext.getBean("car");
        System.out.println(car2.getBrand());
        System.out.println("car1 与 car2的对象 = " + (car1 == car2));
    }
}
