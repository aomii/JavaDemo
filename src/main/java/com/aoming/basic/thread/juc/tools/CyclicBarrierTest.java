package com.aoming.basic.thread.juc.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @classname: CyclicBarrierTest
 * @description: 循环栅栏使用案例：
 *
 * public CyclicBarrier(int parties)
 * public CyclicBarrier(int parties, Runnable barrierAction)
 * 解析：
 * parties 是参与线程的个数
 * 第二个构造方法有一个 Runnable 参数，这个参数的意思是最后一个到达线程要做的任务
 *
 *
 *
 * @author: am
 * @create: 2020-08-11 17:25
 **/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, ()-> {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
        });
        for (int i = 0; i < threadNum; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 到达栅栏 A");
                    barrier.await();//最后一个线程到达这就执行  System.out.println(Thread.currentThread().getName() + " 完成最后任务");
                                    // 然后并发执行到下个await（）

                    System.out.println(Thread.currentThread().getName() + " 冲破栅栏 A");

                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " 到达栅栏 B");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " 冲破栅栏 B");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
