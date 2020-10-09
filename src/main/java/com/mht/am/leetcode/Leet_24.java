package com.mht.am.leetcode;

import com.mht.am.leetcode.common.ListNode;

import java.util.Stack;

/**
 * @classname: Leet_24
 * @description: 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *  
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: am
 * @create: 2020-10-09 11:31
 **/
public class Leet_24 {

    /**
    * @Description: 方式1：栈数据结构
    * @Param: [head]
    * @return: com.mht.am.leetcode.common.ListNode
    * @Author: am
    * @Date: 2020/10/9
    */
    public ListNode reverseList(ListNode head) {
        Stack<Integer> stack=new Stack<>();
        ListNode temp=head;
        while (temp!=null){
            stack.push(temp.val);
            temp=temp.next;
        }
        ListNode root = new ListNode(0);
        ListNode temp2=root;
        while (!stack.isEmpty()){
            temp2.next=new ListNode(stack.pop());
            temp2=temp2.next;
        }
        return root.next;
    }

    /**
    * @Description: 方式2：双指针 *****
    * @Param: [head]
    * @return: com.mht.am.leetcode.common.ListNode
    * @Author: am
    * @Date: 2020/10/9
    */
    public ListNode reverseList2(ListNode head) {
        ListNode pre=null;
        while(head!=null){
            ListNode temp=head.next;
            head.next=pre;
            pre=head;
            head=temp;
        }
        return pre;
    }
}
