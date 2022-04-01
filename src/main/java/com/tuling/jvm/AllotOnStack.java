package com.tuling.jvm;

/**
 * @Description: 栈上分配，标量替换
 *  /**
 * 2 * 栈上分配，标量替换
 * 3 * 代码调用了1亿次alloc()，如果是分配到堆上，大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
 * 4 *
 * 5 * 使用如下参数不会发生GC
 * 6 *  -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * 7 * 使用如下参数都会发生大量GC
 * 8 * ‐Xmx15m ‐Xms15m ‐XX:‐DoEscapeAnalysis ‐XX:+PrintGC ‐XX:+EliminateAllocations
 * 9 * ‐Xmx15m ‐Xms15m ‐XX:+DoEscapeAnalysis ‐XX:+PrintGC ‐XX:‐EliminateAllocations
 * 10
 *
 * @Author: aoming
 * @Date: 2022/4/1 13:32
 * @Version: 1.0
 */
public class AllotOnStack {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000*10000 ; i++) {
            alloc();

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void alloc() throws InterruptedException {
        User user = new User();
        user.setId(1);
        user.setName("zhang");
    }
}
