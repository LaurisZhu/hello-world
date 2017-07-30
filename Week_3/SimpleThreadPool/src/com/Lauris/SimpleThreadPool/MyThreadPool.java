package com.Lauris.SimpleThreadPool;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lawrence on 2017/7/29.
 */
public class MyThreadPool implements Executor {
    private Lock lock = new ReentrantLock();
    private int corePoolSize = 5;
    private volatile long keepAliveTime = 10;
    private volatile Long completedTaskCount;
    private BlockingQueue<Runnable> taskQueue;
    private TaskWorker[] taskWorkers;


    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public Long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public int getWaiteTaskCount() {
        return taskQueue.size();
    }

    public MyThreadPool() {
        this(4);
    }

    private MyThreadPool(int corePoolSize) {
        if (corePoolSize < 0) {
            throw new IllegalArgumentException();
        }
        this.corePoolSize = corePoolSize;
        taskQueue = new LinkedBlockingDeque<>(8);
        taskWorkers = new TaskWorker[corePoolSize];
        for (int i = 0; i < corePoolSize; i++) {
            taskWorkers[i] = new TaskWorker();
            taskWorkers[i].start();
        }
    }

    private void startThreadPool() {
        for (int i = 0; i < corePoolSize; i++) {
            taskWorkers[i] = new TaskWorker();
            taskWorkers[i].start();
        }
        for (int i=0; i<corePoolSize; i++) {
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                System.out.println("屏蔽主线程失败！");
            }
        }
    }

    private void showAllThread() {
        Map map = Thread.getAllStackTraces(); //也可以map<Thread, StackTraceElement[]>
        System.out.println("当前线程数：" + map.size());
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Thread t = (Thread) it.next(); //
            System.out.println(t.getName());
        }
    }

    public void execute(Runnable task) {
        synchronized (this) {
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                System.out.println("加入队列失败！");
            }
            // notify();
            showTask();
        }
    }

    public void execute(Runnable[] task) {
        synchronized (this) {
            for (int i = 0; i < task.length; i++)
                try {
                    taskQueue.put(task[i]);
                } catch (InterruptedException e) {
                    System.out.println("加入队列失败！");
                }
            //notify();
        }
        showTask();
    }

    private void showTask() {
        Iterator<Runnable> it = taskQueue.iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                it.next().run();
            }
        } else System.out.println("链表为空");
    }

    public void destroy() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < corePoolSize; i++) {
            taskWorkers[i].stopRun();
            taskWorkers[i] = null;
        }
        taskQueue.clear();

    }


    private class TaskWorker extends Thread {
        Runnable r = null;
        private boolean isRunning = true;
        //private Lock lock = new ReentrantLock();

        public void run() {
            while (isRunning) {
                //lock.lock();
                while (isRunning && taskQueue.isEmpty()) {
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //lock.lock();
                if (!taskQueue.isEmpty()) {
                    try {
                        r = taskQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // System.out.println((ReentrantLock)lock);
                lock.lock();
                if (r != null) {
                    r.run();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("线程睡眠失败");
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        public void stopRun() {
            isRunning = false;
        }

    }
}

