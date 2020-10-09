package com.mht.am.leetcode.common;

/**
 * @classname: ListNode
 * @description:
 * @author: am
 * @create: 2020-09-21 17:39
 **/
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode(int[] arr){
        ListNode temp = new ListNode(0);
        ListNode root=temp;
        for (int i = 0; i <arr.length ; i++) {
            temp.next=new ListNode(arr[i]);
            temp=temp.next;
        }
        this.next=root.next.next;
        this.val=root.next.val;
    }
}
