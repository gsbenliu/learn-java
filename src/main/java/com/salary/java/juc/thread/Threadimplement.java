package com.salary.java.juc.thread;

import java.util.concurrent.*;

public class Threadimplement {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 实现线程的第一种方式，实现runnable接口
         */
        RunnableThread runnableThread = new RunnableThread();
        Thread first = new Thread(runnableThread);
        first.start();
        System.out.println("first thread Runnable output first");

        /**
         * 集成Thread类实现run方法
         */
        ThreadThread threadThread = new ThreadThread();
        threadThread.start();
        System.out.println("second thread Runnable output second");

        /**
         * 实现callable接口
         */
        CallbleThread callbleThread = new CallbleThread();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(callbleThread);
        System.out.println("third thread Callable output " + future.get());

        /**
         * futuretask
         */
        FutureTask futureTask = new FutureTask(new CallbleThread());
        new Thread(futureTask).start();
        System.out.println("forth thread FutureTask output " + futureTask.get());
    }
}
