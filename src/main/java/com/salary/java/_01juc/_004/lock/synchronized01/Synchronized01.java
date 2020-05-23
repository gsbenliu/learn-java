package com.salary.java._01juc._004.lock.synchronized01;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class Synchronized01 {

    private static int i = 0;

    public static void main(String[] args) throws Exception {

        final Synchronized01 synchronized01 = new Synchronized01();

        new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized01.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized01.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(6L);
        System.out.println("" + i);
    }


    public void increase() throws InterruptedException {
        System.out.println("befor increase thread " + ClassLayout.parseInstance(this).toPrintable());
        synchronized (this) {
            final int j = i;
            System.out.println("increase thread " + j + "" + ClassLayout.parseInstance(this).toPrintable());
            ++i;
            TimeUnit.SECONDS.sleep(3L);
        }
    }


    public void decrease() throws InterruptedException {
        System.out.println("befor decrease thread " + "" + ClassLayout.parseInstance(this).toPrintable());
        synchronized (this) {
            final int j = i;
            System.out.println("decrease thread " + j + "" + ClassLayout.parseInstance(this).toPrintable());
            --i;
            TimeUnit.SECONDS.sleep(3L);
        }
    }

}
