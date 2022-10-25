package com.aoming.basic.thread.cas.aba;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @classname: AtomicStampedReferenceTest
 * @description: ABA问题解决办法
 * @author: am
 * @create: 2020-08-11 15:04
 **/
public class AtomicStampedReferenceTest {

    /**
     * ABA问题重现
     */
   /* public static AtomicInteger a = new AtomicInteger(1);
    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() + ",初始值 = " + a);  //定义变量 a = 1
            try {
                Thread.sleep(1000);  //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = a.compareAndSet(1, 2); // CAS操作
            System.out.println("操作线程" + Thread.currentThread() + ",CAS操作结果: " + isCASSuccess);
        }, "主操作线程");

        Thread other = new Thread(() -> {
            Thread.yield();  //确保thread-main线程优先执行
            a.incrementAndGet(); // a 加 1, a + 1 = 1 + 1 = 2
            System.out.println("操作线程" + Thread.currentThread() + ",【increment】 ,值 = " + a);
            a.decrementAndGet(); // a 减 1, a - 1 = 2 - 1 = 1
            System.out.println("操作线程" + Thread.currentThread() + ",【decrement】 ,值 = " + a);
        }, "干扰线程");

        main.start();
        other.start();
    }*/

    /**
     * ABA问题解决
     */

    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(1, 0);

    public static void main(String[] args)  {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicStampedRef.getReference()+",版本"+atomicStampedRef.getStamp());
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            try {
                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp +1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
            System.out.println("原始版本"+stamp+";  实际版本"+atomicStampedRef.getStamp());
        },"主操作线程");

        Thread other = new Thread(() -> {
            Thread.yield(); // 确保thread-main 优先执行
            atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference()+",版本"+atomicStampedRef.getStamp());
            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference()+",版本"+atomicStampedRef.getStamp());
        },"干扰线程");

        main.start();
        other.start();
    }

}
