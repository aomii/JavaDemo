package com.mht.am.leetcode;

import com.mht.am.leetcode.common.ListNode;

import java.util.Stack;


/**
 * @classname: Leet_1626
 * @description: 反向打印链表
 * @author: am
 * @create: 2020-09-21 17:37
 **/
public class Leet_1626 {
    /** 
    * @Description: 方式一：利用栈 
    * @Param: [head] 
    * @return: int[]
    * @Author: am 
    * @Date: 2020/9/21 
    */ 
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop().val;
        }
        return res;
    }


    /**
    * @Description: 方式二：递归
    * @Param: [head]
    * @return: int[]
    * @Author: am
    * @Date: 2020/9/21
    */
    public int[] reversePrint2(ListNode head) {
        int lenght=length(head);
        int[] ints= new int[lenght];
        reverseHelper(head,ints,lenght-1);
        return ints;
    }

    private void reverseHelper(ListNode head, int[] ints, int index) {
        if (head==null) return;
        reverseHelper(head.next,ints,index-1);
        ints[index]=head.val;
    }

    private int length(ListNode head) {
        int cout = 0;
        ListNode dummy = head;
        while (dummy != null) {
            cout++;
            dummy = dummy.next;
        }
        return cout;
    }
}

