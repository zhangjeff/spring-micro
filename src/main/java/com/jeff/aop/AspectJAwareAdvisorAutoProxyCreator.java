package com.jeff.aop;

import com.jeff.ioc.context.AbstractBeanFactory;
import com.jeff.ioc.postProcessor.BeanPostProcessor;

import java.util.List;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor {

    private AbstractBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory
                .getBeansForType(AspectJExpressionPointcutAdvisor.class);
//        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
//            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
//                ProxyFactory advisedSupport = new ProxyFactory();
//                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
//                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
//
//                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
//                advisedSupport.setTargetSource(targetSource);
//
//                return advisedSupport.getProxy();
//            }
//        }
        return bean;
    }

    public void setBeanFactory(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
