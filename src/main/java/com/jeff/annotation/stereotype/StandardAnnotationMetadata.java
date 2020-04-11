package com.jeff.annotation.stereotype;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class StandardAnnotationMetadata implements AnnotationMetadata {
    private Annotation[] annotations;

    @Override
    public Set<String> getAnnotationTypes() {
        Set<String> types = new LinkedHashSet();
        for (Annotation annotation : annotations) {
            types.add(annotation.annotationType().getName());
        }
        return types;
    }

    public StandardAnnotationMetadata(Class<?> introspectedClass) {
        annotations = introspectedClass.getAnnotations();
    }
}
