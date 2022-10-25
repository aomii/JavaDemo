package com.aoming.tuling.jvm;

/**
 * TODO
 *
 * @author ao921
 * @version 1.0
 * @date 2022/3/29 16:46
 *
 *
 * 结果：
 * *************load TestDynamicLoad************
 * *************load A************
 * *************initial A************
 * *************load test************
 *
 *
 *
 *
 */
public class TestDynamicLoad {
    static {
        System.out.println("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************load test************");
        B b = null; //B不会加载，除非这里执行 new B()
    }
}

class A {
    static {
        System.out.println("*************load A************");
    }

    public A() {
        System.out.println("*************initial A************");
    }
}

class B {
    static {
        System.out.println("*************load B************");
    }

    public B() {
        System.out.println("*************initial B************");
    }
}

