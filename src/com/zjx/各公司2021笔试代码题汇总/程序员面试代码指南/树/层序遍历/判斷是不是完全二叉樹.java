package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，确定他是否是一个完全二叉树。
 * 完全二叉树的定义：若二叉树的深度为 h，除第 h 层外，其它各层的结点数都达到最大个数，第 h 层所有的叶子结点都连续集中在最左边，
 * 这就是完全二叉树。（第 h 层可能包含 [1~2h] 个节点）
 * 数据范围：节点数满足1≤n≤100
 * 链接: https://www.nowcoder.com/practice/8daa4dff9e36409abba2adbe413d6fae?tpId=295&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Fcompany
 * 思路：层序遍历，遇到空节点后，后续节点都是叶子节点
 * 注意: queue可以放入null值
 */
public class 判斷是不是完全二叉樹 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isCompleteTree (TreeNode root) {
        // write code here
        //空树一定是完全二叉树
        if (root == null) {return true;}
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //定义一个首次出现的标记位
        boolean notComplete = false;
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                notComplete = true;
                continue;
            } else if (notComplete) {
                //后续访问已经遇到空节点了，说明经过了叶子
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }
}
