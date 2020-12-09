package com.mht.am.leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author com.mhout.am
 * @version 1.0.0
 * @ClassName: Leet_1226
 * @Description: TODO 哲学家进餐
 * @date 2020/11/16
 */
public class Leet_1226 {

    class zxj1{
        public zxj1() {
            for (int i = 0; i < locks.length; i++) {
                locks[i]=new ReentrantLock();
            }
        }
        Semaphore mutex=new Semaphore(4);
        Lock[] locks=new Lock [5];

        /**
         * @Author aoming
         * @Description //TODO  方式一 ：限制最多n-1人获取资源
         * @Date 14:48 2020/11/16
         * @Param [philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork]
         * @return void
         */
        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            int left=philosopher;
            int right=(philosopher+1)%5;
            mutex.acquire();// p操作
            locks[left].lock();
            locks[right].lock();
            pickLeftFork.run();
            pickRightFork.run();

            eat.run();

            putLeftFork.run();
            putRightFork.run();
            locks[left].unlock();
            locks[right].unlock();
            mutex.release();
        }
    }


    class zxj1Storage{
        private Semaphore mutex=new Semaphore(4);
        private AtomicInteger fork=new AtomicInteger(0);
        //每个叉子的int值(即二进制的00001, 00010, 00100, 01000, 10000)
        private final int[] forkMask=new int[]{1,2,4,8,16};

        /**
         * @Author aoming
         * @Description //TODO  位运算来表示5个状态
         * @Date 15:03 2020/11/16
         * @Param [philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork]
         * @return void
         */
        public void wantsToEat1Storage(int philosopher,
                                       Runnable pickLeftFork,
                                       Runnable pickRightFork,
                                       Runnable eat,
                                       Runnable putLeftFork,
                                       Runnable putRightFork) throws InterruptedException {
            int left=forkMask[philosopher];
            int right=forkMask[(philosopher+1)%5];
            mutex.acquire();// p操作
            while (!pickFork(left)) TimeUnit.SECONDS.sleep(1);
            while (!pickFork(right)) TimeUnit.SECONDS.sleep(1);
            pickLeftFork.run();
            pickRightFork.run();

            eat.run();

            putLeftFork.run();
            putRightFork.run();
            while (!putFork(left)) TimeUnit.SECONDS.sleep(1);
            while (!putFork(right)) TimeUnit.SECONDS.sleep(1);
            mutex.release();
        }

        private boolean putFork(int mask) {
            int expect = fork.get();
            return (expect & mask) >0? fork.compareAndSet(expect,expect^mask):false;
        }

        private boolean pickFork(int mask) {
            int expect = fork.get();
            return (expect & mask) >0? false:fork.compareAndSet(expect,expect^mask);
        }
    }
}
