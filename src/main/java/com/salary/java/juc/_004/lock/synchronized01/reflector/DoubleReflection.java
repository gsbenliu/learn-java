package com.salary.java.juc._004.lock.synchronized01.reflector;

import java.lang.reflect.Method;

public class DoubleReflection {
    public static void main(String[] args) throws Exception {
        Class aClass = Class.forName("com.salary.java.juc._004.lock.synchronized01.reflector.User");
        Method method = aClass.getDeclaredMethod("setAge", int.class);
        method.setAccessible(true);
        Object object = aClass.newInstance();
        method.invoke(object, 10);

        Class bClass = object.getClass();
        Method methodB = bClass.getDeclaredMethod("setAge", int.class);
        methodB.setAccessible(true);
        methodB.invoke(object, 20);


        User u = (User) object;
        System.out.println(u.getAge());
    }


}
