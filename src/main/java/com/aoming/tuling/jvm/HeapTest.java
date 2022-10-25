package com.aoming.tuling.jvm;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author ao921
 * @version 1.0
 * @date 2022/3/30 13:24
 */
public class HeapTest {
    byte [] a=new byte[1024*1024];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true){
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }

}

