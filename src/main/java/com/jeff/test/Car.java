package com.jeff.test;

/**
 * Jeff
 * 2020年03月26日17:33:59
 *
 */
public class Car  {
    private String brand;
    private String color;
    private Integer maxSpeed;

    public Car() {
        System.out.println("调用Car()构造函数");
    }

    public void setBrand(String brand) {
        System.out.println("调用setBrand()设置属性");
        this.brand = brand;
    }

    public void introduce() {
        System.out.println("brand:" + brand + ";color:" + color + ";maxSpeed:" + maxSpeed);
    }

    public void myInit() {
        System.out.println("调用init-method指定的myInit()，将maxSpeed设置为240");
        this.maxSpeed = 240;
    }
    /**
     * 通过<bean>的destroy-method属性指定的初始化方法
     */
    public void myDestroy() {
        System.out.println("调用destroy-method指定的myDestroy()");
    }

    //--------------getter and setter--------------------------------

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}