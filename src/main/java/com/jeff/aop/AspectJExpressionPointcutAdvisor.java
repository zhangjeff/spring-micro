package com.jeff.aop;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }
}
