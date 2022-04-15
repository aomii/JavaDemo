package com.mht.am.leetcode;

import com.mht.am.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 437. 路径总和 III
 * @Author: aoming
 * @Date: 2022/4/6 17:07
 * @Version: 1.0
 */
public class Leet_437 {
    int ans;
    int t;

    //方法一：两层递归
    public int pathSum(TreeNode root, int targetSum) {
        t = targetSum;
        if (root == null) return 0;
        dfs1(root);
        return ans;
    }

    //前序遍历 遍历节点为头节点
    private void dfs1(TreeNode root) {
        if (root == null) return;
        dfs2(root, root.val);
        dfs1(root.left);
        dfs1(root.right);

    }

    /**
     * todo 以root为头节点往下找
     *
     * @param [root, val]
     * @return void
     * @author aoming
     * @date 2022/4/14 17:25
     */
    private void dfs2(TreeNode root, int val) {
        if (val == t) ans++;
        if (root.left != null) dfs2(root.left, val + root.left.val);
        if (root.right != null) dfs2(root.right, val + root.right.val);
    }


    //方法二：树的遍历+前缀和
    Map<Integer, Integer> map = new HashMap<>();
    public int pathSum2(TreeNode root, int targetSum) {
        if (root == null) return 0;
        t = targetSum;
        map.put(0, 1);
        dfs(root, root.val);
        return ans;
    }

    void dfs(TreeNode root, int val) {
        if (map.containsKey(val - t)) ans += map.get(val - t);
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) dfs(root.left, val + root.left.val);
        if (root.right != null) dfs(root.right, val + root.right.val);
        map.put(val, map.getOrDefault(val, 0) - 1);
    }
}
