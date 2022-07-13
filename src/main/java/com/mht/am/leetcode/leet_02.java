package com.mht.am.leetcode;

import com.mht.am.leetcode.common.ListNode;

import org.junit.Test;

/**
 * TODO
 * 模板
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 * @author ao921
 * @version 1.0
 * @date 2022/7/08 11:52
 */
public class leet_02 {
    @Test
    public void test(){
        int [] a={9,9,9,9,9,9,9};
        int [] b={9,9,9,9};

        ListNode l1=new ListNode(a);
        ListNode l2=new ListNode(b);
        addTwoNumbers(l1,l2).print();

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return  dg(l1,l2,0);
    }

    /**
     *
     * @param l1
     * @param l2
     * @param index  进数
     * @return
     */
    private ListNode dg(ListNode l1, ListNode l2, int index) {
        if (l1==null && l2==null && index==0) return null;
        int i = (l1==null?0:l1.val) + (l2==null?0:l2.val)+index;
            ListNode next=dg(l1==null?null:l1.next,l2==null?null:l2.next,i/10);
            ListNode result = new ListNode(i%10,next);
            return result;
    }
}