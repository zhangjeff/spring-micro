
package com.jeff.util;


import com.jeff.ioc.beandefinition.BeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.Assert;


public class ScannedGenericBeanDefinition extends BeanDefinition {

	private final AnnotationMetadata metadata;

	private Resource resource;

    private Object source;

//    private Object beanClass;
	/**
	 * Create a new ScannedGenericBeanDefinition for the class that the
	 * given MetadataReader describes.
	 * @param metadataReader the MetadataReader for the scanned target class
	 */
	public ScannedGenericBeanDefinition(MetadataReader metadataReader) {
		Assert.notNull(metadataReader, "MetadataReader must not be null");
		this.metadata = metadataReader.getAnnotationMetadata();
		setBeanClassName(this.metadata.getClassName());
	}

//    public void setBeanClassName(String beanClassName) {
//        this.beanClass = beanClassName;
//    }

	public final AnnotationMetadata getMetadata() {
		return this.metadata;
	}


	public MethodMetadata getFactoryMethodMetadata() {
		return null;
	}

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
