package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.中序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.Stack;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：中序遍历，但找节点时需要小心 如果<1, 2, 3>中1,3调换顺序，则存在<3, 2> <2, 1>两组逆序对
 * 找逆序时，需要保留第一个逆序对中左边的数left和最后一个逆序对中右边的数right
 */
public class 恢复二叉搜索树 {
    //1
    public void recoverTree1(TreeNode root) {
        if(root == null) return;

        TreeNode left = null;
        TreeNode right = null;
        //中序遍历
        TreeNode prev = null;
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();

            if(prev != null && prev.val > curr.val){
                if(left == null){
                    left = prev;
                }
                right = curr;
            }
            prev = curr;
            curr = curr.right;
        }
        swap(left, right);
    }

    //2
    public void recoverTree2(TreeNode root) {
        if(root == null) return;

        TreeNode left = null;
        TreeNode right = null;

        TreeNode newPrev = null;
        TreeNode cur = root;
        //Morris遍历 O(1)空间复杂度, 将Morris遍历中涉及到保存cur的地方都更改为寻找两个节点操作，即情况1和4都要更改
        while(cur != null){
            if(cur.left == null){
                //do something
                if(newPrev != null && newPrev.val > cur.val){
                    if(left == null){
                        left = newPrev;
                    }
                    right = cur;
                }
                newPrev = cur;
                cur = cur.right;
            }else{
                TreeNode prev = cur.left;
                while(prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if(prev.right == null){
                    prev.right = cur;
                    cur = cur.left;
                }
                if(prev.right == cur){
                    prev.right = null;
                    //do something
                    if(newPrev != null && newPrev.val > cur.val){
                        if(left == null){
                            left = newPrev;
                        }
                        right = cur;
                    }
                    newPrev = cur;

                    cur = cur.right;
                }
            }
        }
        swap(left, right);
    }

    private static void swap(TreeNode left, TreeNode right){
        if(left != null && right != null){
            left.val = left.val ^ right.val;
            right.val = left.val ^ right.val;
            left.val = left.val ^ right.val;
        }
    }
}
