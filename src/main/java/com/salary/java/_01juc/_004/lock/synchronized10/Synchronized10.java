package com.salary.java._01juc._004.lock.synchronized10;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Synchronized10 {


    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 8L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(0));
//        for (int i = 0; i < 10; i++) {
//            final int j = i;
        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                System.out.println("这是第" + 0+ "个线程的处理");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
//        }
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(15);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                threadPoolExecutor.setMaximumPoolSize(16);
//            }
//        }).start();

        TimeUnit.SECONDS.sleep(50);
    }
}
