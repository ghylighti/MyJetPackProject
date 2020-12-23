package com.ghy.componentproject;

import org.junit.Test;

public class Example2 {
    @Test
    public void git()
    {
      fun1();
      fun2();
      fun3();
    }

    private void fun3() {
        System.out.println("fun1");
        System.out.println("开发功能3");
        System.out.println("开发功能3.1");
    }

    private void fun2() {
        testFun2();

    }

    private void fun1() {
        System.out.println("fun1");
        System.out.println("开发功能1");
        System.out.println("开发功能2");
        System.out.println("开发功能3");
        System.out.println("开发功能模块1完成");

    }
    private void testFun2() {
        System.out.println("testFun2");
        System.out.println("开发功能模块2.1");
        bug();
        System.out.println("开发功能模块2.2");
        System.out.println("开发功能模块完成");

    }

    private void bug() {
        System.out.println("修复bug");
    }


}
