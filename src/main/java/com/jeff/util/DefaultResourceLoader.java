package com.jeff.util;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;
import java.net.URL;

import static com.jeff.util.ResourceUtils.CLASSPATH_URL_PREFIX;


public class DefaultResourceLoader implements ResourceLoader {

	private ClassLoader classLoader;


	/**
	 * Create a new DefaultResourceLoader.
	 * <p>ClassLoader access will happen using the thread context class loader
	 * at the time of this ResourceLoader's initialization.
	 * @see java.lang.Thread#getContextClassLoader()
	 */
	public DefaultResourceLoader() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}

	/**
	 * Create a new DefaultResourceLoader.
	 * @param classLoader the ClassLoader to load class path resources with, or {@code null}
	 * for using the thread context class loader at the time of actual resource access
	 */
	public DefaultResourceLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}


	/**
	 * Specify the ClassLoader to load class path resources with, or {@code null}
	 * for using the thread context class loader at the time of actual resource access.
	 * <p>The default is that ClassLoader access will happen using the thread context
	 * class loader at the time of this ResourceLoader's initialization.
	 */
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}


	@Override
	public ClassLoader getClassLoader() {
		return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
	}


	@Override
	public Resource getResource(String location) {
		if (location.startsWith("/")) {
//			return getResourceByPath(location);
		}
//		else if (location.startsWith(CLASSPATH_URL_PREFIX)) {
//			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()), getClassLoader());
//		}
		else {
			try {
				// Try to parse the location as a URL...
				URL url = new URL(location);
				return new UrlResource(url);
			}
			catch (MalformedURLException ex) {
				// No URL -> resolve as resource path.
//				return getResourceByPath(location);
			}
		}
		return null;
	}


//	protected Resource getResourceByPath(String path) {
//		return new ClassPathContextResource(path, getClassLoader());
//	}

//	protected static class ClassPathContextResource extends ClassPathResource implements ContextResource {
//
//		public ClassPathContextResource(String path, ClassLoader classLoader) {
//			super(path, classLoader);
//		}
//
//		@Override
//		public String getPathWithinContext() {
//			return getPath();
//		}
//
//		@Override
//		public Resource createRelative(String relativePath) {
//			String pathToUse = StringUtils.applyRelativePath(getPath(), relativePath);
//			return new ClassPathContextResource(pathToUse, getClassLoader());
//		}
//	}

}
