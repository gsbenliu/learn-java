package com.salary.java._01juc._004.lock.synchronized01.reflector;

public class User {
    private int age;

    public User() {
    }

    public static User getUser() {
        return new User(3);
    }

    private User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}