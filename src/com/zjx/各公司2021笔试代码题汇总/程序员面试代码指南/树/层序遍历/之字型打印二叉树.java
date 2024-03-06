package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历;

import com.zjx.DataStructure.Charpter4.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 *
 * 数据范围：15000≤n≤1500,树上每个节点的val满足 ∣val∣<=100
 * 要求：空间复杂度：O(n)，时间复杂度：O(n)
 *
 * @link https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 *
 * 思路：
 * 用一个双端队列维护每一层节点，fromLeftToRight记录当前层遍历顺序，以及下一层节点加入队列的方式
 * 队列中节点的顺序与树中的顺序一致，poll/pollLast决定于fromLeftToRight
 */
public class 之字型打印二叉树 {
    public List<List<Integer> > print(TreeNode pRoot) {
        List<List<Integer>> res= new ArrayList<>();
        if(pRoot == null) return res;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(pRoot);
        boolean fromLeftToRight = true;  //遍历方式
        while(!deque.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int countAtLevel = deque.size();
            for(int i = 0; i < countAtLevel; i++) {
                TreeNode node = fromLeftToRight ? deque.poll() : deque.pollLast();
                list.add(node.val);
                if (fromLeftToRight) {
                    if(node.left != null) deque.addLast(node.left);
                    if(node.right != null) deque.addLast(node.right);
                } else {
                    if(node.right != null) deque.addFirst(node.right);
                    if(node.left != null) deque.addFirst(node.left);
                }
            }
            res.add(list);
            fromLeftToRight = !fromLeftToRight;
        }
        return res;
    }
}
