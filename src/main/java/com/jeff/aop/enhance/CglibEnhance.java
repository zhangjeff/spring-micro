package com.jeff.aop.enhance;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibEnhance {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDaoImpl.class);
        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("this is CGlib test");
                return proxy.invokeSuper(obj, args);
            }
        });
        UserDaoImpl userDao = (UserDaoImpl)enhancer.create();
        userDao.query("张三");
    }
}
