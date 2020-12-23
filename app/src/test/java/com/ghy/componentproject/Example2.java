package com.ghy.componentproject;

import org.junit.Test;

public class Example2 {
    @Test
    public void git()
    {
      test1();
      test2();
      test2("test2");
      test1("test1");
    }

    private void test1(String test1) {
    }

    private void test2() {

        System.out.println("test2");
    }
    private void test2(String s) {
        System.out.println(s);
        System.out.println(s);
    }
    private void test1() {
        System.out.println("test1");
    }


}
