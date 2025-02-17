package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.后序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class 二叉树后序遍历 {

    /**
     * 莫里斯遍历，O(1)空间复杂度, 是莫里斯先序遍历的镜像版
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null){
            if(cur.right == null){
                result.addFirst(cur.val);
                cur = cur.left;
            }else{
                TreeNode prev = cur.right;
                while(prev.left != null && prev.left != cur){
                    prev = prev.left;
                }
                if(prev.left == null){
                    prev.left = cur;
                    //do something
                    result.addFirst(cur.val);
                    cur = cur.right;
                }
                if(prev.left == cur){
                    prev.left = null;
                    //do something
                    cur = cur.left;
                }
            }
        }
        return result;
    }

    /**
     * 根-右-左的翻转
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if(root == null) return res;

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            res.addFirst(cur.val);  //!!!!!!
            if(cur.left != null) stack.add(cur.left);
            if(cur.right != null) stack.add(cur.right);
        }
        return res;
    }

    /**
     * 栈   pre的使用    stack.peek()
     * @return
     */
    public List<Integer> pathSum3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)  return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = new TreeNode(-1); //上一次栈中弹出的节点,这里将避免pre != cur.right 判断false，导致左孩子无法加入
        while(!stack.isEmpty()){
            TreeNode cur = stack.peek();  //!!!!
            if(cur.left != null && pre != cur.left && pre != cur.right){
                stack.push(cur.left);
            }else if(cur.right != null && pre != cur.right){
                stack.push(cur.right);
            }else{
                pre = stack.pop();
                res.add(pre.val);
            }
        }
        return res;
    }
}
