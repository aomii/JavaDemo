package com.aoming.basic.leetcode.common;

/**
 * @classname: ListNode
 * @description:
 * @author: am
 * @create: 2020-09-21 17:39
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) { val = x; }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

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

   public void print(){
       ListNode temp = this;
       while (temp!=null){
           System.out.print(temp.val+"->");
           temp=temp.next;
       }

   }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
