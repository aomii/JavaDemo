package com.mht.am.thread.juc.tools;

import java.util.concurrent.CountDownLatch;

/**
 * @classname: CountDownLatchTest
 * @description: 倒计数器使用案列;
 * 递减锁存器的计数，如果计数到达零，则释放所有等待的线程。如果当前计数大于零，则将计数减少.
 * @author: am
 * @create: 2020-08-11 17:05
 *
 *
 *
 *
 * 开始执行
 * Thread-6:doWork
 * 等待线程都执行完，主线程
 * Thread-3:doWork
 * Thread-5:doWork
 * Thread-0:doWork
 * Thread-7:doWork
 * Thread-8:doWork
 * Thread-4:doWork
 * Thread-2:doWork
 * Thread-1:doWork
 * Thread-9:doWork
 * main 结束了
 *
 *
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
        System.out.println("开始执行" );




        
        start.countDown();// let all threads proceed
        System.out.println("等待线程都执行完，主线程" );
        end.await();           // wait for all to finish 阻塞直到等待count=0
        System.out.println("main 结束了");

    }
}
