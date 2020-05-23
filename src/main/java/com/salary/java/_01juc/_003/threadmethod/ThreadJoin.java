package com.salary.java._01juc._003.threadmethod;

import java.util.concurrent.TimeUnit;

public class ThreadJoin {

    public static void main(String[] args) {
        final Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("this my thread_1 join example");
                }
            }
        });
        thread.start();

        Thread thread_2 = new Thread(new Runnable() {
            public void run() {
                try {
                    thread.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("this my thread_2 join example");
                }
            }
        });
        thread_2.start();
    }
}
