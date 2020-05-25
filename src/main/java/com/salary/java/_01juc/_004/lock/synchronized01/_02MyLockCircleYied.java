package com.salary.java._01juc._004.lock.synchronized01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

public class _02MyLockCircleYied {

    /**
     * 要解决自旋锁的性能问题必须让竞争锁失败的线程不忙等,而是在获取不到锁的时候能把cpu资源给让出来，说到让cpu资源，你可能想到了yield()方法，
     * 当线程竞争锁失败时，会调用yield方法让出cpu。需要注意的是该方法只是当前让出cpu，有可能操作系统下次还是选择运行该线程。其实现是
     * 将当期线程移动到所在优先调度队列的末端（操作系统线程调度了解一下？有时间的话，下次写写这块内容）。
     * 也就是说，如果该线程处于优先级最高的调度队列且该队列只有该线程，那操作系统下次还是运行该线程。
     * 自旋+yield的方式并没有完全解决问题，当系统只有两个线程竞争锁时，yield是有效的。但是如果有100个线程竞争锁，当线程1获得锁后，
     * 还有99个线程在反复的自旋+yield，线程2调用yield后，操作系统下次运行的可能是线程3；而线程3CAS失败后调用yield后，操作系统下次运行的可能是线程4...
     * 假如运行在单核cpu下，在竞争锁时最差只有1%的cpu利用率，导致获得锁的线程1一直被中断，执行实际业务代码时间变得更长，从而导致锁释放的时间变的更长。
     */

    private static Unsafe unsafe;

    private static Long offset;

    static {
        try {
            unsafe = getUnsafe();
            Field field = User.class.getDeclaredField("age");
            offset = unsafe.objectFieldOffset(field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        _02MyLockCircleYied myLockCircleYied = new _02MyLockCircleYied();
        User user = new User(0);
        myLockCircleYied.thread_1(user);
        myLockCircleYied.thread_2(user);

        TimeUnit.SECONDS.sleep(2000L);
        System.out.println("end of data " + user.getAge());
    }

    public void thread_1(final User user) {
        for (int i = 0; i < 20; i++) {
            final int j = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("开始执行线程 当前是 thread_1 index = " + j);
                    boolean b = false;
                    while (!(b = unsafe.compareAndSwapInt(user, offset, j, j + 1))) {
                        System.out.println(" thread_1 第 " + j + " 次，更新数据为 " + (j + 1) + " cas操作结果 = " + user.getAge() + " result = " + b);
                        Thread.currentThread().yield();
//                        if (user.getAge() > j) {
//                            System.out.println(" thread_1 第 " + j + " 次，更新数据为 " + (j + 1) + "  次主动结束 ");
//                            return;
//                        }
                        try {
                            TimeUnit.SECONDS.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (b) {
                        System.out.println(" thread_1 第 " + j + " 次，更新数据为 " + (j + 1) + " cas操作结果 = " + user.getAge() + " result = " + b);
                    }
                }
            }).start();
        }
    }

    public void thread_2(final User user) {
        for (int i = 0; i < 20; i++) {
            final int j = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("开始执行线程 当前是 thread_2 index = " + j);
                    boolean b = false;
                    while (!(b = unsafe.compareAndSwapInt(user, offset, j, j + 1))) {
                        System.out.println(" thread_2 第 " + j + " 次，更新数据为 " + (j + 1) + " cas操作结果 = " + user.getAge() + " result = " + b);
                        Thread.currentThread().yield();
//                        if (user.getAge() > j) {
//                            System.out.println(" thread_2 第 " + j + " 次，更新数据为 " + (j + 1) + "  次主动结束 ");
//                            return;
//                        }
                        try {
                            TimeUnit.SECONDS.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (b) {
                        System.out.println(" thread_1 第 " + j + " 次，更新数据为 " + (j + 1) + " cas操作结果 = " + user.getAge() + " result = " + b);
                    }
                }
            }).start();
        }
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static class User {

        private int age;

        public User(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
