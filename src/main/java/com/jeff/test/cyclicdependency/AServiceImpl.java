package com.jeff.test.cyclicdependency;


import java.sql.SQLOutput;

/**
 * @author yihua.huang@dianping.com
 */
public class AServiceImpl implements AService {

    private BService bService;

    public void setbService(BService bService) {
        this.bService = bService;
    }

    public BService getbService() {
        return bService;
    }

    @Override
    public void helloWorld() {
        System.out.println("hello world!");
    }
}
