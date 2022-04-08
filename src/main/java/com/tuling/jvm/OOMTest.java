package com.tuling.jvm;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @Description: 可达性分析算法
 *                user中重写finalize（）方法，标记即将回收的对象
 *
 * @Author: aoming
 * @Date: 2022/4/1 14:30
 * @Version: 1.0
 */
public class OOMTest {
    public static void main(String[] args) {

        // JVM设置
        // ‐Xms10M ‐Xmx10M ‐XX:+PrintGCDetails ‐XX:+HeapDumpOnOutOfMemoryError ‐XX:HeapDumpPath=D:\jvm.dump
        ArrayList<Object> list = new ArrayList<>();
        int i=0,j=0;
        while (true){
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--,UUID.randomUUID().toString());
        }
    }

}
