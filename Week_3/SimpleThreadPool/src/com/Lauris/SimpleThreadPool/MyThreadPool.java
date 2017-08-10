package com.Lauris.SimpleThreadPool;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lawrence on 2017/7/29.
 */
public class MyThreadPool implements Executor {
    //用于存储需要完成的任务
    private BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>();
    //用于存储线程
    private List<Thread> taskWorkers = new LinkedList<>();
    private int MAX_SIZE;


    public MyThreadPool() {
        this(8);
    }

    public MyThreadPool(int corePoolSize) {
        if (corePoolSize < 0) {
            throw new IllegalArgumentException();
        }
        MAX_SIZE = corePoolSize;

        //仅在主线程调用的时候定义，缺乏线程池所需的灵活性
      /*  synchronized (taskWorkers) {
            while (taskWorkers.size() < MAX_SIZE) {
                taskWorkers.add(new TaskWorker());
            }
        }*/
    }


    public void showAllThread() {
        Map map = Thread.getAllStackTraces(); //也可以map<Thread, StackTraceElement[]>
        int number = map.size();
       //System.out.println("当前线程数：" + map.size());
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Thread t = (Thread) it.next();
            if (!t.getName().startsWith("Thread")) {
                number--;
                continue;
            }
            System.out.println(t.getName());
        }
        System.out.println("线程池线程总数：" + number);
    }

    @Override
    public void execute(Runnable task) {
        //线程锁
        synchronized (taskWorkers) {
            while (taskWorkers.size() < MAX_SIZE) {
                Thread worker = new TaskWorker();
                taskWorkers.add(worker);
                worker.start();
            }
        }

        //任务锁
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notifyAll();
        }
    }


    public void destroy() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Iterator<Thread> iterator = taskWorkers.iterator();
        while (iterator.hasNext()) {
            Thread temp = iterator.next();
            temp.stop();
        }
    }

    private class TaskWorker extends Thread {
        Runnable r = null;
        private boolean isRunning = true;

        public void stopThread() {
            isRunning = false;
        }

        public void run() {
            synchronized (taskQueue) {

                while (isRunning) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        r = taskQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (r != null) {
                        //以下使本线程完成后等待，直到被其他线程唤醒
                        //使得线程池的多个线程特性更明显
                        taskQueue.notifyAll();
                        r.run();
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("线程等待失败");
                        }
                    }

                }
            }
        }
    }
}

