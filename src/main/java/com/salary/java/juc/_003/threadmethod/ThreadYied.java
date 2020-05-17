package com.salary.java.juc._003.threadmethod;

public class ThreadYied {

    public static void main(String[] args) {
        Thread thread_1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("A" + i);
                    if (i % 10 == 0) Thread.yield();
                }
            }
        });

        Thread thread_2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("B" + i);
                    if (i % 10 == 0) Thread.yield();
                }
            }
        });
        thread_1.start();
        thread_2.start();
    }
}
