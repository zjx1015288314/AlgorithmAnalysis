package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.先序遍历相关;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.util.Stack;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树展开为链表 {
    public void flatten1(TreeNode root) {
        if(root == null) return;

        TreeNode cur = root;
        while(cur != null) {
            if(cur.left != null) {
                TreeNode lastOfLeft = cur.left;
                while(lastOfLeft.right != null) {
                    lastOfLeft = lastOfLeft.right;
                }
                lastOfLeft.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    /**
     * 使用变种的后序遍历,迭代版,空间复杂度O(logN),最坏情况下为O(N)
     */
    public void flatten2(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.peek();
            if(cur.left == null || cur.left == pre){
                stack.pop();
                //do something
                cur.right = pre;
                cur.left = null;  //note that cur.left need to be null
                pre = cur;
                cur = null;
            }else{
                cur = cur.left;
            }
        }
    }

    /**
     * 如果用先序遍历的话,会丢失掉右孩子,所以我们用先序遍历迭代的形式,但是我们模拟先序遍历的递归
     * 形式,在进入左子树之前就保留右孩子节点,空间复杂度O(logN),最坏情况下为O(N)
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;
        while (!s.isEmpty()) {
            TreeNode temp = s.pop();
            /***********修改的地方*************/
            if(pre!=null){
                pre.right = temp;
                pre.left = null;
            }
            /***********这里即为变种先序遍历*************/
            if (temp.right != null){
                s.push(temp.right);
            }
            if (temp.left != null){
                s.push(temp.left);
            }
            /***********修改的地方*************/
            pre = temp;
            /********************************/
        }
    }
}
