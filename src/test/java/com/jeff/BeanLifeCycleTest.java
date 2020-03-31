package com.jeff;

import com.jeff.ioc.context.ApplicationContext;
import com.jeff.ioc.context.ClassPathXmlApplicationContext;
import com.jeff.test.Car;
import org.junit.Test;

public class BeanLifeCycleTest {

    /**
     * Jeff
     * 2020年03月26日09:52:49
     * 单例测试
     */
    @Test
    public void testSingleton() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-singleton.xml");
        Car car1 = (Car)applicationContext.getBean("car");
        car1.setBrand("2222");
        Car car2 = (Car)applicationContext.getBean("car");
        System.out.println(car2.getBrand());
        System.out.println("car1 与 car2的对象 = " + (car1 == car2));
    }

    /**
     * Jeff
     * 2020年03月26日09:52:49
     * 单例测试
     */
    @Test
    public void testPrototype() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-prototype.xml");
        Car car1 = (Car)applicationContext.getBean("car");
        car1.setBrand("2222");
        Car car2 = (Car)applicationContext.getBean("car");
        System.out.println(car2.getBrand());
        System.out.println("car1 与 car2的对象 = " + (car1 == car2));
    }
}

