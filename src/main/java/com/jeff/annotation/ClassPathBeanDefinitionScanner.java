package com.jeff.annotation;

import com.jeff.annotation.resource.ResourcePatternResolver;
import com.jeff.annotation.stereotype.Component;
import com.jeff.ioc.beandefinition.BeanDefinition;
import com.jeff.util.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class ClassPathBeanDefinitionScanner {
    final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

    private AnnotatedGenericBeanDefinition annotatedGenericBeanDefinition;

    private org.springframework.core.io.support.ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private final List<TypeFilter> includeFilters = new LinkedList<TypeFilter>();

    private final List<TypeFilter> excludeFilters = new LinkedList<TypeFilter>();

    private MetadataReaderFactory metadataReaderFactory =
            new CachingMetadataReaderFactory(this.resourcePatternResolver);

//    private ConditionEvaluator conditionEvaluator;

    public ClassPathBeanDefinitionScanner() {
        this.includeFilters.add(new AnnotationTypeFilter(Component.class));
    }

    public Set<BeanDefinitionHolder> scan(String... basePackages) throws Exception{
        Set<BeanDefinitionHolder> beanDefinitionHolderSet = doScan(basePackages);
        return beanDefinitionHolderSet;
    }

    protected Set<BeanDefinitionHolder> doScan(String... basePackages) throws Exception{
        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
//                ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
//                candidate.setScope(scopeMetadata.getScopeName());
//                String beanName = this.beanNameGenerator.generateBeanName(candidate, this.registry);
                String beanName = decapitalize(ClassUtils.getShortName(candidate.getBeanClassName()));
//
//                if (candidate instanceof AbstractBeanDefinition) {
//                    postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName);
//                }
//                if (candidate instanceof AnnotatedBeanDefinition) {
//                    AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
//                }
//                if (checkCandidate(beanName, candidate)) {
                BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanName, candidate);
//                definitionHolder = AnnotationConfigUtils.applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry);
//                    beanDefinitions.add(definitionHolder);
                beanDefinitions.add(definitionHolder);
//                registerBeanDefinition(definitionHolder, this.registry);
//                }
            }
        }
        return beanDefinitions;
    }

    public static String decapitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) &&
                Character.isUpperCase(name.charAt(0))){
            return name;
        }
        char chars[] = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    public Set<BeanDefinition> findCandidateComponents(String basePackage) throws Exception {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    resolveBasePackage(basePackage) + "/" + this.DEFAULT_RESOURCE_PATTERN;
            Resource[] resources = getResources(packageSearchPath);
//            List<Resource> resources = new ArrayList(urls.length);
//            for (URL url : urls) {
//                Resource resource = new UrlResource(url);
//                resources.add(resource);
//            }
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    try {
                        MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                        if (isCandidateComponent(metadataReader)) {
                            ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
                            sbd.setResource(resource);
                            sbd.setSource(resource);
//                            if (isCandidateComponent(sbd)) {
                            candidates.add(sbd);
//                            }
                        }
                    } catch (Throwable ex) {
                        throw new Exception(
                                "Failed to read candidate component class: " + resource, ex);
                    }
                }
            }
        } catch (Exception ex) {
            throw new Exception("I/O failure during classpath scanning", ex);
        }
        return candidates;
    }

    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        for (TypeFilter tf : this.excludeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return false;
            }
        }
        for (TypeFilter tf : this.includeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
//                return isConditionMatch(metadataReader);
                return true;
            }
        }
        return false;
    }


//    private boolean isConditionMatch(MetadataReader metadataReader) {
//        if (this.conditionEvaluator == null) {
//            this.conditionEvaluator = new ConditionEvaluator(getRegistry(), getEnvironment(), getResourceLoader());
//        }
//        return !this.conditionEvaluator.shouldSkip(metadataReader.getAnnotationMetadata());
//        return false;
//    }

    protected String resolveBasePackage(String basePackage){
       return basePackage.replace(".", "/");
    }

    public boolean isPattern(String path) {
        return (path.indexOf('*') != -1 || path.indexOf('?') != -1);
    }

    public Resource[] getResources(String locationPattern) throws IOException {
        if (locationPattern.startsWith(CLASSPATH_ALL_URL_PREFIX)) {
            // a class path resource (multiple resources for same name possible)
            if (isPattern(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()))) {
                // a class path resource pattern
                return findPathMatchingResources(locationPattern);
            } else {
                // all class path resources with the given name
                return findAllClassPathResources(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()));
            }
        }
        return  null;
    }

    protected Resource[] findPathMatchingResources(String locationPattern) throws IOException {
        String rootDirPath = determineRootDir(locationPattern);
        String subPattern = locationPattern.substring(rootDirPath.length());
        Resource[] rootDirResources = getResources(rootDirPath);
        Set<Resource> result = new LinkedHashSet<Resource>(16);
        for (Resource rootDirResource : rootDirResources) {
//            rootDirResource = resolveRootDirResource(rootDirResource);
            result.addAll(doFindPathMatchingFileResources(rootDirResource.getURL(), subPattern));
        }
        return result.toArray(new Resource[result.size()]);
    }

    protected String determineRootDir(String location) {
        int prefixEnd = location.indexOf(":") + 1;
        int rootDirEnd = location.length();
        while (rootDirEnd > prefixEnd && isPattern(location.substring(prefixEnd, rootDirEnd))) {
            rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
        }
        if (rootDirEnd == 0) {
            rootDirEnd = prefixEnd;
        }
        return location.substring(0, rootDirEnd);
    }


    protected Resource[] findAllClassPathResources(String location) throws IOException {
        String path = location;
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        Set<Resource> result = doFindAllClassPathResources(path);
        return result.toArray(new Resource[result.size()]);
    }

    public PathMatcher getPathMatcher() {
        return new AntPathMatcher();
    }

    protected Set<Resource> doFindPathMatchingFileResources(URL rootDirResource, String subPattern)
            throws IOException {

        File rootDir;
        try {
            rootDir = new File(toURI(rootDirResource.toString()).getSchemeSpecificPart()).getAbsoluteFile();
//            rootDir =  new File(toURI(rootDirResource.toString()).).getFile().getAbsoluteFile();
        }
        catch (Exception ex) {
            return Collections.emptySet();
        }
        return doFindMatchingFileSystemResources(rootDir, subPattern);
    }

    public URI toURI(String location) throws URISyntaxException {
        return new URI(org.springframework.util.StringUtils.replace(location, " ", "%20"));
    }

    protected Set<Resource> doFindMatchingFileSystemResources(File rootDir, String subPattern) throws IOException {
        Set<File> matchingFiles = retrieveMatchingFiles(rootDir, subPattern);
        Set<Resource> result = new LinkedHashSet<Resource>(matchingFiles.size());
        for (File file : matchingFiles) {
            result.add(new FileSystemResource(file));
        }
        return result;
    }

    protected Set<File> retrieveMatchingFiles(File rootDir, String pattern) throws IOException {
        if (!rootDir.exists()) {
            // Silently skip non-existing directories.

            return Collections.emptySet();
        }
        if (!rootDir.isDirectory()) {
            // Complain louder if it exists but is no directory.

            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {

            return Collections.emptySet();
        }
        String fullPattern = rootDir.getAbsolutePath().replace(File.separator, "/");
        if (!pattern.startsWith("/")) {
            fullPattern += "/";
        }
        fullPattern = fullPattern + pattern.replace(File.separator, "/");
        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(fullPattern, rootDir, result);
        return result;
    }

    protected void doRetrieveMatchingFiles(String fullPattern, File dir, Set<File> result) throws IOException {

        File[] dirContents = dir.listFiles();
        if (dirContents == null) {

            return;
        }
        for (File content : dirContents) {
            String currPath = content.getAbsolutePath().replace(File.separator, "/");
            if (content.isDirectory() && getPathMatcher().matchStart(fullPattern, currPath + "/")) {
                if (!content.canRead()) {

                }
                else {
                    doRetrieveMatchingFiles(fullPattern, content, result);
                }
            }
            if (getPathMatcher().match(fullPattern, currPath)) {
                result.add(content);
            }
        }
    }


    protected Set<Resource> doFindAllClassPathResources(String path) throws IOException {
        Set<Resource> result = new LinkedHashSet<>(16);
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resourceUrls = (cl != null ? cl.getResources(path) : ClassLoader.getSystemResources(path));
        while (resourceUrls.hasMoreElements()) {
            URL url = resourceUrls.nextElement();
            result.add(new UrlResource(url));
        }
//        if ("".equals(path)) {
//            // The above result is likely to be incomplete, i.e. only containing file system references.
//            // We need to have pointers to each of the jar files on the classpath as well...
//            addAllClassLoaderJarRoots(cl, result);
//        }
        return result;
    }

//    protected Resource convertClassLoaderURL(URL url) {
//        return new UrlResource(url);
//    }

    public static void main(String[] args) throws Exception{
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner();
//        classPathBeanDefinitionScanner.doScan("com.jeff.test.annotation");
        classPathBeanDefinitionScanner.findCandidateComponents("com.jeff.test.annotation");

    }
}
