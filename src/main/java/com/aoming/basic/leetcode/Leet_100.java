package com.aoming.basic.leetcode;

import com.aoming.basic.leetcode.common.TreeNode;

/**
 * TODO
 * @author ao921
 * @version 1.0
 * @date 2022/3/13 18:54
 */
public class Leet_100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
