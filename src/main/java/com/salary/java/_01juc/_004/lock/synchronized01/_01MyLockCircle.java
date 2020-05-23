package com.salary.java._01juc._004.lock.synchronized01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class _01MyLockCircle {

    /**
     * https://stackoverflow.com/questions/22626808/what-does-the-sun-reflect-callersensitive-annotation-mean
     * CallerSensitive 官方解释
     * http://openjdk.java.net/jeps/176
     */
    static Unsafe unsafe = null;
    private static long offset = 0;

    static {
        try {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(User.class.getDeclaredField("age"));//获取User中age字段在内存中的偏移量
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        _01MyLockCircle myLockCircle = new _01MyLockCircle();
        myLockCircle.compareAndSet();
    }

    /**
     * boolean sun.misc.Unsafe.compareAndSwapInt(Object arg0, long arg1, int arg2, int arg3)
     * <p>
     * -- @param arg0 -比较的对象
     * -- @param arg1 -需要修改的字段内存偏移量
     * -- @param arg2 -期待值（对比参考值）
     * -- @param arg3 -修改的值         描述：argo对象中arg1偏移量指向的字段值如果等于arg2，则将arg1偏移量指向的字段值改为arg3，返回true否则不做任何操作，返回false
     */
    private void compareAndSet() {
        final User user = new User(5);
        for (int i = 0; i < 20; i++) {
            final int j = i;
            new Thread(new Runnable() {
                public void run() {
                    int result = j * 2 + 3;
                    boolean b = unsafe.compareAndSwapInt(user, offset, j, result);
                    if (b) {
                        result = j * 5 - 3;
                        System.out.println("compareAndSet no " + j + ",result " + b + " age = " + user.age + "   " + "compareAndSwapInt(this, " + offset + ", " + j + ", " + result + ")");
                    }else{
                        System.out.println("compareAndSet no " + j + ",result " + b + " age = " + user.age + "   " + "compareAndSwapInt(this, " + offset + ", " + j + ", " + result + ")");
                    }
                }
            }).start();
        }
    }


    /**
     * 通过代码反射获取Unsafe静态类
     * 直接通过Unsafe.getUnsafe()获取会抛安全性异常
     *
     * -- @CallerSensitive 这个注解是为了堵住漏洞用的。曾经有黑客通过构造双重反射来提升权限.原理是当时反射只检查固定深度的调用者的类，看它有没有特权，
     * 例如固定看两层的调用者（getCallerClass(2)）。如果我的类本来没足够权限群访问某些信息，那我就可以通过双重反射去达到目的：
     * 反射相关的类是有很高权限的，而在 我->反射1->反射2 这样的调用链上，反射2检查权限时看到的是反射1的类，这就被欺骗了，导致安全漏洞。
     * 使用CallerSensitive后，getCallerClass不再用固定深度去寻找 actual caller（“我”），而是把所有跟反射相关的接口方法都标注上
     * CallerSensitive，搜索时凡看到该注解都直接跳过，这样就有效解决了前面举例的问题
     */
    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }


    class User {
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
