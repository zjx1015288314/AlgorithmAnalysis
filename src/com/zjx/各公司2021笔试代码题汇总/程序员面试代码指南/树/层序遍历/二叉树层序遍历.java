package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class 二叉树层序遍历 {

    /**
     * 队列迭代
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //List中可以有null值!!!如果这里不返回，则依然会进去while循环并抛出NullPointerException
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelOrdeList = new ArrayList<>();
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                levelOrdeList.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(0, levelOrdeList);
        }
        return result;
    }

    /**
     * DFS
     */
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder2(TreeNode root) {
        helper(root,0);
        return result;
    }
    //DFS
    public void helper(TreeNode root,int level){
        if(root == null) return;
        if(result.size() == level) result.add(new ArrayList<Integer>());
        result.get(level).add(root.val);
        helper(root.left,level+1);
        helper(root.right,level+1);
    }
}
