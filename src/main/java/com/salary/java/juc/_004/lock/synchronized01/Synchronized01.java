package com.salary.java.juc._004.lock.synchronized01;

import java.util.concurrent.TimeUnit;

public class Synchronized01 {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final Synchronized01 synchronized01 = new Synchronized01();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    synchronized01.increase();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(6L);
        System.out.println("" + i);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    synchronized01.decrease();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(6L);
        System.out.println("" + i);
    }


    public void increase() {
        synchronized (this) {
            ++i;
        }
    }


    public void decrease() {
        synchronized (this) {
            --i;
        }
    }

}
