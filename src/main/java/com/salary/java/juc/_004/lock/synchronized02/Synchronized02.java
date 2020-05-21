package com.salary.java.juc._004.lock.synchronized02;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

public class Synchronized02 {

    private static int i = 0;

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        final Synchronized02 synchronized02 = new Synchronized02();

        new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized02.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized02.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(6L);
        System.out.println("" + i);

        System.out.println("befor increase thread " + ClassLayout.parseInstance(synchronized02).toPrintable());
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
