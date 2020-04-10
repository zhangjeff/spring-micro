package com.jeff.annotation;

public interface AnnotationConfigRegistry {

    void register(Class<?>... annotatedClasses);

    void scan(String... basePackages);
}
