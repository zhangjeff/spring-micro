package com.jeff.aop;

public class Test {
    public static void main(String[] args) throws Exception {
        UserDao userDao = new UserDaoImpl();
//        userDao.query("张三");
//        UserDao proxy = new LogUserProxy(userDao);
//        proxy.query("李四");
        UserDao userDao2 = (UserDao)ProxyUtil.newProxyInstance(userDao);
        userDao2.query("zhangsan");
    }
}
