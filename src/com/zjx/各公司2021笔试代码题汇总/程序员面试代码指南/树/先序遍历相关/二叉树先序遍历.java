package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.先序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class 二叉树先序遍历 {
    /**
     * 正常写法，与中序遍历有统一的模板
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res  = new ArrayList<>();
        if(root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur != null) {
            while(cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return res;
    }

    /**
     * 根-右-左
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if(root == null) return res;
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            res.add(cur.val);
            if(cur.right != null){
                stack.add(cur.right);
            }
            if(cur.left != null){
                stack.add(cur.left);
            }
        }
        return res;
    }

    /**
     * 莫里斯遍历，O(1)空间复杂度
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        if(root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }else{
                TreeNode pre = cur.left;
                while(pre.right != null && pre.right != cur) pre = pre.right;
                if(pre.right != cur){
                    pre.right = cur;
                    res.add(cur.val);
                    cur = cur.left;
                }else{
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
