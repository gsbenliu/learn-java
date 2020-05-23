package com.salary.java._02juc202005;

import java.util.concurrent.TimeUnit;

/**
 * 对于  synchronized 关键字的使用
 * 在多线程并发编程中Synchronized一直是元老级角色，很多人都会称呼它为重量级锁，
 * 但是随着Java SE1.6对Synchronized进行了各种优化之后，有些情况下它并不那么重了，
 * 本文详细介绍了Java SE1.6中为了【减少】【获得锁】和【释放锁】带来的性能消耗而引入的【偏向锁和轻量级锁】，
 * 以及锁的存储结构和升级过程。
 */
public class SynchronizedExample {
    private int count = 10;

    public static void main(String[] args) {
        final SynchronizedExample synchronizedExample = new SynchronizedExample();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    synchronizedExample.decrease();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronizedExample.printCount();
    }

    /**
     * JVM规范规定JVM基于进入和退出Monitor对象来实现方法同步和代码块同步，
     * 但两者的实现细节不一样。
     * 1>代码块同步-使用monitorenter和monitorexit指令实现，
     * 2>方法同步-使用另外一种方式实现的，细节在JVM规范里并没有详细说明，但是方法的同步同样可以使用这两个指令来实现。
     * monitorenter指令是在编译后插入到同步代码块的开始位置，而monitorexit是插入到方法结束处和异常处，
     * JVM要保证每个monitorenter必须有对应的monitorexit与之配对。
     * <p>
     * 任何对象都有一个 monitor 与之关联，当且一个monitor 被持有后，它将处于锁定状态。线程执行到 monitorenter 指令时，
     * 将会尝试获取对象所对应的 monitor 的所有权，即尝试获得对象的锁。
     */

    public void decrease() {
        synchronized (this) {
            --count;
        }
    }

    public void printCount() {
        synchronized (this) {
            System.out.println(count);
        }
    }

}
