package com.salary.java.juc.thread;

import java.util.concurrent.Callable;

public class CallbleThread implements Callable<String> {
    public String call() throws Exception {
        return "this is a callable thread";
    }
}
