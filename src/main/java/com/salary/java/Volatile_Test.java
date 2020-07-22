package com.salary.java;

import java.math.BigDecimal;

public class Volatile_Test {

    public static void main(String[] args) {
//        Volatile_Test volatile_test = new Volatile_Test();

        BigDecimal bigDecimal =  new BigDecimal("0.989865891");
        System.out.println( bigDecimal.setScale(2,BigDecimal.ROUND_DOWN));
        System.out.println( bigDecimal.setScale(4,BigDecimal.ROUND_DOWN));
        System.out.println( bigDecimal.setScale(6,BigDecimal.ROUND_DOWN));
    }
}
