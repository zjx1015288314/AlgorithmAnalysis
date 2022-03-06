package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * （ps：我们约定空树不是任意一个树的子结构）
 *
 * @link https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 判断Tree2是否是Tree1的子树 {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null) return false;

        boolean isSubtree = isSameTree(root1, root2);

        boolean isLeftSubTree = HasSubtree(root1.left, root2);
        boolean isRightSubTree = HasSubtree(root1.right, root2);

        return isSubtree || isLeftSubTree || isRightSubTree;
    }

    public boolean isSameTree(TreeNode root1,TreeNode root2) {
        if(root2 == null) return true;
        if(root1 == null) return false;

        if(root1.val != root2.val) return false;

        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }
}
