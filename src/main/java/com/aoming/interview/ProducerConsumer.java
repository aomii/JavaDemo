package com.aoming.interview;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {

    // 共享阻塞队列
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
    private static ExecutorService producerPool = Executors.newFixedThreadPool(2);
    private static ExecutorService consumerPool = Executors.newFixedThreadPool(2);
    private static ExecutorService subTaskPool = Executors.newFixedThreadPool(5);
    private static volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        // 启动生产者
        for (int i = 0; i < 2; i++) {
            producerPool.submit(new Producer(i));
        }

        // 启动消费者
        for (int i = 0; i < 2; i++) {
            consumerPool.submit(new Consumer(i));
        }

        // 运行一段时间后停止
        Thread.sleep(10000);
        isRunning = false;
        producerPool.shutdown();
        consumerPool.shutdown();
        subTaskPool.shutdown();
    }

    static class Producer implements Runnable {
        private int id;

        public Producer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            int taskId = 1;
            try {
                while (isRunning) {
                    queue.put(taskId);
                    System.out.println("Producer-" + id + " produced task: " + taskId++);
                    Thread.sleep(300); // 模拟延迟
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer interrupted");
            }
        }
    }

    static class Consumer implements Runnable {
        private int id;

        public Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (isRunning || !queue.isEmpty()) {
                    Integer taskId = queue.take();
                    System.out.println("Consumer-" + id + " is processing task: " + taskId);

                    // 拆分为5个子任务
                    CountDownLatch latch = new CountDownLatch(5);
                    AtomicInteger successCount = new AtomicInteger(0);

                    for (int i = 1; i <= 5; i++) {
                        int subId = i;
                        subTaskPool.submit(() -> {
                            try {
                                String result = doSubTask(taskId, subId);
                                System.out.println("SubTask " + taskId + "-" + subId + " result: " + result);
                                successCount.incrementAndGet();
                            } catch (Exception e) {
                                System.err.println("SubTask " + taskId + "-" + subId + " failed: " + e.getMessage());
                            } finally {
                                latch.countDown();
                            }
                        });
                    }

                    // 等待所有子任务完成
                    latch.await();

                    System.out.println("Consumer-" + id + " 完成任务：" + taskId + "，子任务成功数：" + successCount.get());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Consumer interrupted");
            }
        }

        // 模拟子任务处理
        private String doSubTask(int taskId, int subId) throws Exception {
            if (Math.random() < 0.1) {
                throw new Exception("子任务失败");
            }
            Thread.sleep((long) (Math.random() * 300));
            return "Success-task-" + taskId + "-sub-" + subId;
        }
    }
}