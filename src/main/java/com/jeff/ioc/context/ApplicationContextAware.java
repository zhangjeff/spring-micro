package com.jeff.ioc.context;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws Exception;
}
