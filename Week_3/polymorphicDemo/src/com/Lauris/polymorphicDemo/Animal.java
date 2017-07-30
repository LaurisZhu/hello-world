package com.Lauris.polymorphicDemo;

/**
 * Created by Lawrence on 2017/7/30.
 */
public abstract class Animal {
    protected String name;
    protected int age;
    protected boolean isFull;

    protected Animal(String name) {
        setName(name);
    }

    protected Animal(String name,int age) {
        setAge(age);
        setName(name);
    }
    protected void setName(String name) {
        this.name = name;
    }
    protected void setAge(int age) {
        this.age = age;
    }
    abstract void eat();

    protected void sleep() {
        System.out.println("zzZZ");
    }

}
