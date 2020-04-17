package com.jeff;

public class Demo1 {

    private static  Integer a;
    Integer b;
    public static void main(String[] args) {
//        int i = 2;
////        i = ++i;
////        System.out.println(i);

//        System.out.println(a);
//        Demo1 demo1 = new Demo1();
//        System.out.println(demo1.b);
        System.out.println(StaticTest.aa);
        System.out.println(StaticTest2.bb);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("33333333");
        }
    }
}
//  -XX:+TraceClassLoading
class StaticTest{
    public final  static String aa = "aaaaaaaaaaaa";
}

class StaticTest2{
    public static String bb = "ccccccccccccccc";
}
