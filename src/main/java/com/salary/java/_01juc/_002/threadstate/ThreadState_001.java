package com.salary.java._01juc._002.threadstate;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 1>当前线程不含有当前对象的锁资源的时候，调用obj.wait()方法;
 * 2>当前线程不含有当前对象的锁资源的时候，调用obj.notify()方法。
 * 3>当前线程不含有当前对象的锁资源的时候，调用obj.notifyAll()方法。
 * https://www.jianshu.com/p/ec94ed32895f
 */

public class ThreadState_001 {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread state enter " + this.getState());
            System.out.println("this is my thread");
            System.out.println("==========================this no lock=============================" + ClassLayout.parseInstance(this).toPrintable().substring(0,700));
            synchronized (this) {
                try {
                    this.wait();
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final MyThread myThread = new MyThread();
        System.out.println("MyThread init state " + myThread.getState());
        myThread.start();
        TimeUnit.SECONDS.sleep(3L);
        System.out.println("==========================new Thread no lock=============================" + ClassLayout.parseInstance(myThread).toPrintable().substring(0,700));
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread count times " + i + " state " + myThread.getState());
                    try {
                        TimeUnit.SECONDS.sleep(2L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 3) {
                        synchronized (myThread) {
                            System.out.println("before thread count times " + i + " synchronized state " + myThread.getState());
                            myThread.notify();
                            System.out.println("after thread count times " + i + " synchronized state " + myThread.getState());
                            System.out.println("===========================lock============================" + ClassLayout.parseInstance(myThread).toPrintable().substring(0,700));
                        }
                    }
                }
            }
        }).start();
        try {
            myThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(20L);
        System.out.println("system end thread state " + myThread.getState());
    }
}
