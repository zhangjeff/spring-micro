package com.jeff.annotation;

import com.jeff.ioc.context.AbstractApplicationContext;
import com.jeff.ioc.context.AbstractBeanFactory;
import com.jeff.ioc.context.AutowireCapableBeanFactory;

/**
 * @author zhangying
 */
public class GenericApplicationContext extends AbstractApplicationContext {

    public GenericApplicationContext() {
        this(new AutowireCapableBeanFactory());
    }

    public GenericApplicationContext(AbstractBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {

    }
}
