package com.jeff.util;


import org.springframework.core.io.Resource;

public interface ResourceLoader {

	String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

	Resource getResource(String location);


	ClassLoader getClassLoader();

}
