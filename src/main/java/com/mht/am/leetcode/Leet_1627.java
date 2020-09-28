package com.mht.am.leetcode;

import com.mht.am.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @classname: Leet_1627
 * @description: 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *
 * 先序遍历：根节点→左子树→右子树。
 *
 * 中序遍历：左子树→根节点→右子树。
 *
 * 后续遍历：左子树→右子树→根节点。
 *
 * @author: am
 * @create: 2020-09-23 13:26
 **/
public class Leet_1627 {
    /**
    * @Description: 方式一：递归
    * @Param: [preorder, inorder]
    * @return: javax.swing.tree.TreeNode
    * @Author: am
    * @Date: 2020/9/23
    */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preorderList = new ArrayList<>();
        List<Integer> inorderList = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            preorderList.add(preorder[i]);
            inorderList.add(inorder[i]);
        }
        return helper(preorderList, inorderList);
    }

    private TreeNode helper(List<Integer> preorderList, List<Integer> inorderList) {
        if (inorderList==null || inorderList.size()==0){
            return null;
        }
        Integer i = preorderList.remove(0);
        int index=inorderList.indexOf(i);
        TreeNode root=new TreeNode(i);
        root.left=helper(preorderList,inorderList.subList(0,index));
        root.right=helper(preorderList,inorderList.subList(index+1,inorderList.size()));
        return root;
    }
    
    
    /** 
    * @Description: 方式二：递归，用指针
    * @Param:  
    * @return:  
    * @Author: am 
    * @Date: 2020/9/23 
    */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (inStart>inEnd || preStart>preorder.length-1){
            return null;
        }
        TreeNode root=new TreeNode(preorder[preStart]);
        int index=0;
        for (int i = inStart; i <=inEnd ; i++) {
            if (inorder[i]==preorder[preStart]){
                index=i;
                break;
            }
        }
        root.left=helper(preStart+1,inStart,index-1,preorder,inorder);
        root.right=helper(preStart+1+index-inStart,index+1,inEnd,preorder,inorder);
        return root;
    }

    /**
    * @Description: 方式三：栈
    * @Param:
    * @return:
    * @Author: am
    * @Date: 2020/9/23
    */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode root=new TreeNode(preorder[0]);
        TreeNode curr=root;
        for (int i = 1,j=0; i <preorder.length ; i++) {
            if (curr.val!=inorder[j]){
                curr.left=new TreeNode(preorder[i]);
                stack.push(curr);
                curr=curr.left;
            }else {
                j++;
                while (!stack.isEmpty() && stack.peek().val==inorder[j]){
                    curr=stack.pop();
                    j++;
                }
                curr.right=new TreeNode(preorder[i]);
                curr=curr.right;
            }
        }
        return root;
    }
}
