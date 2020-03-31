package com.jeff.aop;

import lombok.Data;

@Data
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;
}
