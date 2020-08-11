package com.mht.am.thread.cas.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: AtomicIntegerTest
 * @description: 原子类使用
 * @author: am
 * @create: 2020-08-11 14:09
 **/
public class AtomicIntegerTest {
    static AtomicInteger a=new AtomicInteger();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(a.incrementAndGet());
                    }
                }
            }).start();
        }
    }
}
