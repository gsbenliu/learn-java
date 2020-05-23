package com.salary.java._01juc._004.lock.synchronized03;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class _01_SynchronizedNoLock {

    public static void main(String[] args) throws InterruptedException {
        Synchronized03 synchronized03 = new Synchronized03("_01_SynchronizedNoLock", 8);
        System.out.println(synchronized03.getName());
        System.out.println(synchronized03.getAge());

        System.out.println(ClassLayout.parseInstance(synchronized03).toPrintable());
        System.out.println(VM.current().details());
    }
}
