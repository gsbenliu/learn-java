package com.salary.java._01juc._004.lock.synchronized10;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

public class Synchronized10 {


    public static void main(String[] args) {
        try {
            final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 8L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1), new CallerRunsPolicy());

            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        final int j = i;
                        threadPoolExecutor.execute(new Runnable() {
                            public void run() {
                                System.out.println("这是第" + j + "个线程的处理");
                                try {
                                    TimeUnit.SECONDS.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("目前最大线程数" + threadPoolExecutor.getMaximumPoolSize() + "核心线程数" + threadPoolExecutor.getCorePoolSize() + "活动线程数" + threadPoolExecutor.getActiveCount());
                    threadPoolExecutor.setMaximumPoolSize(16);
                    threadPoolExecutor.setCorePoolSize(8);
                }
            }).start();

           while (true) {
               System.out.println("目前最大线程数" + threadPoolExecutor.getMaximumPoolSize() + "核心线程数" + threadPoolExecutor.getCorePoolSize() + "活动线程数" + threadPoolExecutor.getActiveCount());
               if (threadPoolExecutor.getActiveCount() == 16){
                   threadPoolExecutor.setMaximumPoolSize(8);
                   threadPoolExecutor.setCorePoolSize(4);
                   System.out.println();
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
