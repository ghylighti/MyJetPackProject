package com.ghy.myview.activity.utils;

import java.util.HashMap;

public class MathUtils {
    HashMap hashMap=new HashMap(10);

    public static void main(String[] args) {
       tableSizeFor(65);
//        System.out.println(Long.toBinaryString(tableSizeFor(100014646546465464l)));
    }

    /**
     *
     * @param cap int类型
     * @return 求最相近的2的次方
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println(Integer.toBinaryString(n)+"");
        n= n >>> 1;
        System.out.println(Integer.toBinaryString(n)+"");

//        n = n >>> 2;
//        System.out.println(Integer.toBinaryString(n)+"");
        n= n >>> 4;
        System.out.println(Integer.toBinaryString(n)+"");
        n= n >>> 2;
        System.out.println(Integer.toBinaryString(n)+"");
        n = n >>> 16;
        System.out.println(Integer.toBinaryString(n)+"");
        return n;
    }
    /**
     *
     * @param cap long类型
     * @return 求最相近的2的次方
     */
    static final long tableSizeFor(long cap) {
        long n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        n |= n >>> 32;
        return n;
    }
}
