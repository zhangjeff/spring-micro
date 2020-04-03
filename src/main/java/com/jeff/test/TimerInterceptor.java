package com.jeff.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Jeff
 * 2020年04月03日15:49:24
 */
public class TimerInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long time = System.nanoTime();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
		Object proceed = invocation.proceed();
		System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
				+ " nanoseconds.");
		return proceed;
	}

}
