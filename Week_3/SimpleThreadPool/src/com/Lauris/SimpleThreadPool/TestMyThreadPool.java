package com.Lauris.SimpleThreadPool;

/**
 * Created by Lawrence on 2017/7/30.
 */
public class TestMyThreadPool {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(10);
        for (int i = 0; i<20; i++) {
            final int num = i;
            myThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +"  " + num + "完成");
                    if (num == 0) {
                        System.out.println("+++++++++++");
                        myThreadPool.showAllThread();
                        System.out.println("-----------");
                    }
                }
            });
        }
        //销毁线程池
        myThreadPool.destroy();
        System.out.println("+++++++++++");
        myThreadPool.showAllThread();
        System.out.println("-----------");

        System.out.println(Thread.currentThread().getName()+"\t\tover!");
    }

}

