package com.aoming.basic.leetcode;

import com.aoming.basic.leetcode.common.ListNode;
import org.junit.Test;

/**
 * @classname: Leet_22
 * @description: 链表中倒数第k个节点
 * @author: am
 * @create: 2020-09-30 10:54
 **/
public class Leet_22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        int index=0;
        ListNode root =head;
        ListNode root2 =head;
        while (root!=null){
            if (k<=index){
                root2=root2.next;
            }
            root=root.next;
            index++;

        }
        return root2;
    }


    /**
    * @Description: 递归
    * @Param:
    * @return:
    * @Author: am
    * @Date: 2020/9/30
    */
    int size;

    public ListNode getKthFromEnd2(ListNode head, int k) {
        if (head == null)
            return head;
        ListNode node = getKthFromEnd2(head.next, k);
        if (++size == k)
            return head;
        return node;
    }

    @Test
    public void test(){
        this.getKthFromEnd2(new ListNode(new int[]{1,2,3,4}),2);
    }

}
