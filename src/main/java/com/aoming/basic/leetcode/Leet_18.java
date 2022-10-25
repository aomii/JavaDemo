package com.aoming.basic.leetcode;

import com.aoming.basic.leetcode.common.ListNode;

/**
 * @classname: Leet_18
 * @description: 删除链表的节点
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 *
 *
 * @author: am
 * @create: 2020-09-28 15:48
 **/
public class Leet_18 {
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val==val){
            return head.next;
        }
        ListNode root=head;
        while (head.next!=null){
            if (head.next.val==val){
                head.next=head.next.next;
                break;
            }
            head=head.next;
        }
        return root;
    }
}
