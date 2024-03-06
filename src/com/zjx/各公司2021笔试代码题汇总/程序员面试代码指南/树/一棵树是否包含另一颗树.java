package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 一棵树是否包含另一颗树 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null) {//root为空树时认为不包含subRoot(不管subRoot是否为空)
            return false;
        }
        //注意是||不是&&
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot) || isSubtreeHelper(root, subRoot);
    }

    public boolean isSubtreeHelper(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null) {
            return root == subRoot;
        }
        if(root.val != subRoot.val) {
            return false;
        }
        return isSubtreeHelper(root.left, subRoot.left) && isSubtreeHelper(root.right, subRoot.right);
    }
}
