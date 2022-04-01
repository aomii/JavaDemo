package com.mht.am.leetcode;

import com.mht.am.leetcode.common.TreeNode;
import org.junit.Test;

/**
 * @Description: 合并二叉树
 * @Author: aoming
 * @Date: 2022/4/1 16:28
 * @Version: 1.0
 */
public class Leet_617 {
    @Test
    public void test(){

    }
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1!=null && root2!=null){
            root1.left=mergeTrees(root1.left,root2.left);
            root1.val=root1.val+root2.val;
            root1.right=mergeTrees(root1.right,root2.right);
            return root1;
        }else if (root1==null && root2==null){
            return null;
        }else if (root1==null){
            return root2;
        }else{
            return root1;
        }
    }

}
