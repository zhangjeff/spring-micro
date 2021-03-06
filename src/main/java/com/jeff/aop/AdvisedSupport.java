package com.jeff.aop;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

@Data
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;
}
