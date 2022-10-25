package com.aoming.tuling.jvm;

/**
 * TODO
 *
 * @author ao921
 * @version 1.0
 * @date 2022/4/8 22:53
 */
public class DeadLockTest {
    private static Object lock1 = new User();
    private static Object lock2 = new User();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1){
                try {
                    System.out.println("thread1 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("thread1 end");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (lock2){
                try {
                    System.out.println("thread2 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("thread2 end");
                }
            }
        }).start();

        System.out.println("main thread end");
    }

}
