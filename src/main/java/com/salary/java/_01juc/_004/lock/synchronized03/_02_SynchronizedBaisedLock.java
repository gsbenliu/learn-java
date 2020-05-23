package com.salary.java._01juc._004.lock.synchronized03;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

public class _02_SynchronizedBaisedLock {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(VM.current().details());
        System.out.println("");
        System.out.println("");
        TimeUnit.SECONDS.sleep(6);

        BaisedLock baisedLock = new BaisedLock();
        ClassLayout classLayout =  ClassLayout.parseInstance(baisedLock);

        System.out.println("before lock ");
        System.out.println(classLayout.toPrintable());

        System.out.println("");
        System.out.println("");

        synchronized (baisedLock){
            System.out.println(" lock ing ....... ");
            System.out.println(classLayout.toPrintable());
        }

        System.out.println("");
        System.out.println("");

        System.out.println("after lock exit ");
        System.out.println(classLayout.toPrintable());

    }

    public static class BaisedLock{

    }
}
