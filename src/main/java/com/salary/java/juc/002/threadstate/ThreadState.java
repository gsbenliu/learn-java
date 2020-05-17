package com.salary.java.juc.threadstate;

import javax.xml.soap.Text;
import java.util.concurrent.TimeUnit;

/**
 * 1>当前线程不含有当前对象的锁资源的时候，调用obj.wait()方法;
 * 2>当前线程不含有当前对象的锁资源的时候，调用obj.notify()方法。
 * 3>当前线程不含有当前对象的锁资源的时候，调用obj.notifyAll()方法。
 * https://www.jianshu.com/p/ec94ed32895f
 */

public class ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread state enter " + this.getState());
            System.out.println("this is my thread");
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
                        }
                    }
                }
            }
        }).start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TimeUnit.SECONDS.sleep(20L);
        System.out.println("system end thread state " + myThread.getState());
    }
}
