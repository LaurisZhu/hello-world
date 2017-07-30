package com.Lauris.polymorphicDemo;

/**
 * Created by Lawrence on 2017/7/30.
 */
public class TestDemo {
    public static void main(String[] args) {
        //进行向上转型，父类型的变量指向子类型的对象。
        Animal cat = new Cat("小黑");

        //当子类对象实现了父对象的抽象方法或者重写了父类的方法时，类型提升后
        //当变量调用方法时，运行的是子类的方法
        cat.eat();
        System.out.println();

        cat.sleep();
        System.out.println();

        haveFun(cat);
    }

    private static void haveFun(Animal animal) {
        animal.eat();
        animal.sleep();
        //如果animal指向的对象是Cat类的实例
        if (animal instanceof Cat) {
            //此次进行向下转型，才可调用对应子类的新方法
            animal.eat();
            ((Cat) animal).catchMouse();
        }
        System.out.println();
    }

}
