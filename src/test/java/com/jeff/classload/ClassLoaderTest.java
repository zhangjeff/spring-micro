package com.jeff.classload;


import java.io.*;

public class ClassLoaderTest extends ClassLoader {

    private String classLoaderName;

    private String fileExtension = ".class";

    public ClassLoaderTest(){
        super();
    }

    public ClassLoaderTest(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public ClassLoaderTest(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

//    @Override
//    public String toString() {
//        return "ClassLoaderTest{" +
//                "classLoaderName='" + classLoaderName + '\'' +
//                '}';
//    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] data = this.loadClassData(className);
        return this.defineClass(className,data,0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
//            this.classLoaderName = this.classLoaderName.replace(".", "/");
            is = new FileInputStream(new File(name + fileExtension));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
              is.close();
              baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.jeff.classload.ClassLoaderTest");
        Object object = clazz.newInstance();
        System.out.println(object);
    }

    public static void main(String[] args) throws Exception {
        ClassLoaderTest classLoaderTest = new ClassLoaderTest("loader1");
        test(classLoaderTest);
    }
}
