package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import com.zjx.DataStructure.Charpter4.TreeNode;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * @link https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 是否是对称二叉树 {
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) return true;
        return judge(pRoot.left, pRoot.right);
    }

    public boolean judge(TreeNode node1, TreeNode node2) {
        if(node1 == null || node2 == null) {
            return node1 == node2 ? true : false;
        }
        if(node1.val != node2.val) {
            return false;
        }
        return judge(node1.left, node2.right) && judge(node1.right, node2.left);
    }
}
