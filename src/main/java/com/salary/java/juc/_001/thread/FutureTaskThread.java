package com.salary.java.juc._001.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskThread extends FutureTask {
    public FutureTaskThread(Callable callable) {
        super(callable);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("this is a callable thread");
    }
}
