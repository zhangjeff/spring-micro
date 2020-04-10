package com.jeff.annotation;

import com.jeff.ioc.context.AbstractApplicationContext;
import com.jeff.ioc.context.AbstractBeanFactory;

/**
 * @author zhangying
 */
public class GenericApplicationContext extends AbstractApplicationContext {


    public GenericApplicationContext(DefaultListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {

    }
}
