package com.mht.am.thread.juc.tools;

import java.util.concurrent.CountDownLatch;

/**
 * @classname: CountDownLatchTest
 * @description: 倒计数器使用案列;
 * @author: am
 * @create: 2020-08-11 17:05
 **/
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start=new CountDownLatch(1);
        CountDownLatch end=new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    start.await();//count==0放行,count>0则阻塞
                    System.out.println(Thread.currentThread().getName()+":doWork");
                    end.countDown();//count-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        start.countDown();// let all threads proceed
        System.out.println("等待线程都执行完，主线程" );
        end.await();           // wait for all to finish
        System.out.println("main 结束了");

    }
}
