package com.salary.java._01juc._001.thread;

import java.util.concurrent.Callable;

public class CallbleThread implements Callable<String> {
    public String call() throws Exception {
        return "this is a callable thread";
    }
}
