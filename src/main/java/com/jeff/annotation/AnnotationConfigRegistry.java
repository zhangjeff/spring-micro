package com.jeff.annotation;

import java.util.Set;

public interface AnnotationConfigRegistry {

    void register(Class<?>... annotatedClasses);

    void scan(String... basePackages);
}
