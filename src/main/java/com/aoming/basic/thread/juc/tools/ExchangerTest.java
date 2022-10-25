package com.aoming.basic.thread.juc.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @classname: ExchangerTest
 * @description: 交换器:起两个线程，线程A 线程B ，它们分别各自处理自己的数据，然后进行数据交换。
 * @author: am
 * @create: 2020-08-11 17:48
 **/
public class ExchangerTest {
    private static Exchanger<String> exchanger=new Exchanger<>();
    private static ExecutorService executorService= Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        executorService.execute(()->{
            String A="线程A的数据";
            try {
                String B=exchanger.exchange(A);
                System.err.println("线程A--->线程A交换到的线程B的数据：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            String B = "线程B的数据";
            try {
                String A = exchanger.exchange(B);
                System.err.println("线程B--->线程B交换到的线程A的数据：" + A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        executorService.shutdown();
    }
}
