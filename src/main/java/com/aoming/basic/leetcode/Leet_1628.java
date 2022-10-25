package com.aoming.basic.leetcode;

import java.util.Stack;

/**
 * @classname: Leet_1628
 * @description: 用两个栈实现队列
 * @author: am
 * @create: 2020-09-23 17:04
 **/
public class Leet_1628 {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public Leet_1628() {
        stack1=new Stack();
        stack2=new Stack();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if (!stack2.isEmpty()){
            return stack2.pop();
        }else{
            return -1;
        }
    }
}
