package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.树深度;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.*;

public class 二叉树右视图 {
    /**
     * 双栈+DFS
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;
        Stack<TreeNode> node_stack = new Stack<>();
        Stack<Integer> depth_stack = new Stack<>();
        node_stack.push(root);
        depth_stack.push(0);
        while(!node_stack.isEmpty()){
            TreeNode node = node_stack.pop();
            int depth = depth_stack.pop();
            if(depth >= res.size()) res.add(node.val);  //根节点深度为1时，考虑depth > res.size()
            if(node.left != null){
                node_stack.push(node.left);
                depth_stack.push(depth+1);
            }
            if(node.right != null){
                node_stack.push(node.right);
                depth_stack.push(depth+1);
            }
        }
        return res;
    }

    /**
     * 队列+BFS
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //List中可以有null值!!!如果这里不返回，则依然会进去while循环并抛出NullPointerException
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                if(i == length - 1) {
                    result.add(node.val);
                }
                //为了出栈时先右后左
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    /**
     * 递归版DFS    所有DFS需要注意的是：右视图的场景下是先右后左，左视图则正好相反
     */
    public List<Integer> rightSideView3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        dfs(root,res,0);
        return res;
    }

    public void dfs(TreeNode root,List<Integer> res,int level){
        if(root == null) return;
        if(level >= res.size()) { //只有当深度deep 不小于res的大小(即已经看到的层数)时，我们认为这个是新一层的最右边
            res.add(root.val);
        }
        if(root.right != null) dfs(root.right, res, level + 1);//先右后左
        if(root.left != null) dfs(root.left, res, level + 1);
    }
}
