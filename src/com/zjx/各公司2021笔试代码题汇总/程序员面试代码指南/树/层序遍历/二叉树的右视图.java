package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，
 * 返回从右侧所能看到的节点值。
 * 示例 1:
 * 输入:[1,2,3,null,5,null,4]
 * 输出:[1,3,4]
 * 示例 2:
 * 输入:[1,null,3]
 * 输出:[1,3]
 * 示例 3:
 * 输入:[]
 * 输出:[]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树的右视图 {
    /**
     * 层次遍历，记录每一层最后一个
     */
    public List<Integer> rightSideView(TreeNode root) {
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
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }
}
