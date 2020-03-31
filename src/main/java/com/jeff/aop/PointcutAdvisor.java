package com.jeff.aop;

public interface PointcutAdvisor extends  Advisor{

    Pointcut getPointcut();
}
