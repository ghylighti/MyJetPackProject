package com.ghy.componentproject;

import org.junit.Test;

public class Example2 {
    @Test
    public void git()
    {
      fun1();
      fun2();
    }

    private void fun2() {
        testFun2();

    }

    private void fun1() {
    }
    private void testFun2() {
        System.out.println("testFun2");
        System.out.println("开发功能模块2.1");
        bug();
    }

    private void bug() {
        System.out.println("bug");
    }


}
