package com.jeff.aop;

public class LogUserProxy implements UserDao{
    private UserDao userDao;

    public LogUserProxy(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void query(String name) {
        System.out.println("log proxy...");
        userDao.query(name);
    }
}
