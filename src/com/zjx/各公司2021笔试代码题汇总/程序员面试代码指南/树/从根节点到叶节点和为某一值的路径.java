package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import java.util.*;

/**
 * @author zhaojiexiong
 * @create 2020/7/18
 * @since 1.0.0
 */
public class 从根节点到叶节点和为某一值的路径 {

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, new ArrayList<>());
        return res;
    }

    public void dfs(TreeNode root, int target, ArrayList<Integer> list){
        if(root == null) return;
        list.add(root.val);
        dfs(root.left,target - root.val, list);
        dfs(root.right,target - root.val, list);
        //如果题目要求是从根节点到任意节点的路径和  这里的root.left == null && root.right == null可以省略不要
        if(root.left == null && root.right == null && target == root.val){
            res.add(new ArrayList<>(list));  //这里必须使用new ArrayList<>(list)  如果是add(list),则list的变化会关联到最终结果
        }
        //不能使用remove(root.val) root.val会被识别为index发生越界异常,
        //如果非要使用remove(Object o)方法则通过pathList.remove((Integer) root.val);
        list.remove(list.size() - 1);
    }

    /**
     * 利用一个栈的后序遍历解法，不用先序的原因是，先序遍历会先弹出根结点，再遍历右孩子
     * 如果使用递归的话，也是后序遍历
     * @return
     */
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<Integer> list = new LinkedList<>(); //便于添加和删除
        if(root == null)  return res;

        //后序遍历
        list.add(root.val);
        int target = root.val;  //与sum比较

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = new TreeNode(-1); //上一次栈中弹出的节点,这里将避免pre != cur.right 判断false，导致左孩子无法加入
        while(!stack.isEmpty()){
            TreeNode cur = stack.peek();
            if(cur.left != null && pre != cur.left && pre != cur.right){
                stack.push(cur.left);
                list.add(cur.left.val);
                target += cur.left.val;
            }else if(cur.right != null && pre != cur.right){
                stack.push(cur.right);
                list.add(cur.right.val);
                target += cur.right.val;
            }else{
                if(cur.left == null && cur.right == null && target == sum){
                    res.add(new LinkedList<>(list));
                }
                pre = stack.pop();
                list.pollLast();
                target -= pre.val;
            }
        }
        return res;
    }
}


