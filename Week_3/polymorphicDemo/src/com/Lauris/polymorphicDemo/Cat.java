package com.Lauris.polymorphicDemo;

/**
 * Created by Lawrence on 2017/7/30.
 */
public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }
    public Cat(String name) {
        super(name);
    }

    public void sleep() {
        super.sleep();
        System.out.println("喵喵喵~");
        this.isFull = false;
    }

    @Override
    public void eat() {
        System.out.println("吃鱼");
        this.isFull = true;
    }

    public void catchMouse() {
        if (!isFull) {
            System.out.println("吃老鼠");
            this.isFull = true;
        }
        else System.out.println("扔掉老鼠");
    }
}
