package com.jeff.aop;

import com.jeff.classload.ClassLoaderTest;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtil {

    public static Object newProxyInstance(Object target) throws Exception{
        String content = "";
        Class targetInfo = target.getClass().getInterfaces()[0];
        String packageContent = "package com.jeff.aop;";
        String targetInfoName = targetInfo.getSimpleName();
        String importContent = "import " + targetInfo.getName() + ";";
        String firstContent= "public class $Proxy implements " +targetInfoName + "{";
        String fieldContent = "private " + targetInfoName + "  target;";

        String conStructorContent = "public $Proxy(" + targetInfoName + " target) {"
                +"  this.target = target;" +"}";

        Method[] methods = targetInfo.getDeclaredMethods();
        String methodsContent = "";

        for(Method method : methods) {
            String returnTypeName = method.getReturnType().getSimpleName();
            String methodName = method.getName();
            Class[] parameterTypes = method.getParameterTypes();
            String argContent = "";
            String paramsContent = "";
            int i = 0;
            for (Class parameterType : parameterTypes) {
                String simpleName = parameterType.getSimpleName();
                argContent += simpleName + " p"+i + ",";
                paramsContent +=" p"+i + ",";
                i++;
            }
            if (argContent.length() > 1) {
                argContent = argContent.substring(0, argContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }

            methodsContent += "public " + returnTypeName +" " + methodName + "("
                    + argContent +") {"
                    + "System.out.println(\"log proxy2...\");"
                    + "target." + methodName+"("+ paramsContent +");"
                    +"}";

            content = packageContent + importContent + firstContent + fieldContent + conStructorContent +
                    methodsContent + "}";


        }
        File file = new File("src/test/java/com/jeff/aop/$Proxy.java");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(file);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr,null,null,null,units);
        t.call();
        fileMgr.close();
        URL url = file.toURI().toURL();
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
//        Class clazz = urlClassLoader.loadClass("com.jeff.aop.$Proxy");
//        Class clazz = Thread.currentThread().getContextClassLoader().loadClass("com.jeff.aop.$Proxy");
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.loadClass("com.jeff.aop.$Proxy");
        Constructor constructor = clazz.getConstructor(targetInfo);
        Object obj = constructor.newInstance(target);
        return obj;
    }
}
