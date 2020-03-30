package com.jeff.aop;

public class UserDaoImpl implements UserDao {
    @Override
    public void query(String name) {
        System.out.println("query name=" +name);
    }
}
