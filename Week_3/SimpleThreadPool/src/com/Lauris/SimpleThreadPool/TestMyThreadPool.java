package com.Lauris.SimpleThreadPool;

/**
 * Created by Lawrence on 2017/7/30.
 */
public class TestMyThreadPool {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.execute(new Runnable[]{new SetTask(),new SetTask(),new SetTask()});
        myThreadPool.execute(new Runnable[]{new SetTask(),new SetTask(),new SetTask()});
        myThreadPool.execute(new Runnable[]{new SetTask(),new SetTask(),new SetTask()});
        myThreadPool.destroy();
        System.out.println(Thread.currentThread().getName()+"\t\tover!");
    }

    private static class SetTask implements Runnable {
        private static volatile int i = 0;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"\t任务\t"+(i++)+"\t完成");
        }
    }
}

