package com.ghy.componentproject;

import android.util.ArrayMap;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.internal.util.VolatileSizeArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println("aa");
        List<String> attr=new ArrayList<>();
        attr.add("红");
        attr.add("黄");
        attr.add("绿");
        Goods a=new Goods("苹果",attr,10);
        attr=new ArrayList<>();

        attr.add("绿");
        attr.add("黄");
        attr.add("红");
        Goods b=new Goods("苹果",attr,2);

        attr=new ArrayList<>();
        attr.add("黑");
        attr.add("黄");
        Goods c=new Goods("栗子",attr,15);

        attr=new ArrayList<>();
        attr.add("青");
        attr.add("黄");
        Goods d=new Goods("香蕉",attr,15);

        attr=new ArrayList<>();

        attr.add("绿");
        attr.add("黄");
        attr.add("红");
        Goods e=new Goods("苹果",attr,7);
        attr=new ArrayList<>();
        attr.add("黄");
        attr.add("青");
        Goods f=new Goods("香蕉",attr,15);
        attr=new ArrayList<>();
        attr.add("绿");
        attr.add("黄");
        attr.add("红");
        Goods g=new Goods("苹果",attr,7);
        attr=new ArrayList<>();
        attr.add("黄");
        attr.add("绿");
        Goods h=new Goods("苹果",attr,7);
        attr=new ArrayList<>();
        attr.add("绿");
        attr.add("黄");
        Goods i=new Goods("苹果",attr,7);


        List<Goods> goods=new ArrayList<>();
        goods.add(a);
        goods.add(b);
        goods.add(c);
        goods.add(d);
        goods.add(e);
        goods.add(f);
        goods.add(e);
        goods.add(g);
        goods.add(h);
        goods.add(i);
        List<Goods> merge= MergeUtils.mergeList(goods);

        for (Goods goods1:merge) {
            System.out.println(goods1.name+goods1.attr+goods1.count);
        }
    }


    class Goods
    {
        String name;
        List<String> attr;
        int count;

        public Goods(String name, List<String> attr, int count) {
            this.name = name;
            this.attr = attr;
            this.count = count;
        }


    }

    static class MergeUtils
    {
        public static List<Goods> deleteGoos=new ArrayList<>();
        public static List<Goods> mergeList(List<Goods> goods)
        {

            List<Goods> merges=new ArrayList<>();
            for (Goods g:
                 goods) {
                Collections.sort(g.attr);

            }

            for (int i=0;i<goods.size();i++)
            {

                merges.add(compare(goods,goods.get(i),i)) ;
            }
            for (Goods g:
                 deleteGoos) {
                merges.remove(g);
            }
            return merges;
        }

        public static Goods compare(List<Goods> goods, Goods good,int i)
        {

            int count=good.count;
            String  flag=good.name+Arrays.toString(good.attr.toArray());
             for (;i<goods.size();i++)
             {
                 Goods next=goods.get(i);
                 if (next==good)
                 {
                     continue;
                 }else
                 {
                     if (flag.equals(next.name+Arrays.toString(next.attr.toArray())))
                     {
                         count+=next.count;
                         deleteGoos.add(next);
                     }

                 }
             }

            good.count=count;
            return good;
        }
    }
}