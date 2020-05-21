package com.salary.java.juc._004.lock.synchronized03;

public class Synchronized03 {

    private String name = "Synchronized03";

    private int age = 5;

    public Synchronized03() {
    }

    public Synchronized03(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
