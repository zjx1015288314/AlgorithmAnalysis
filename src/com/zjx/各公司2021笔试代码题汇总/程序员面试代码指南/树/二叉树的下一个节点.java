package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针。
 * @link https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 *
 */
public class 二叉树的下一个节点 {

    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode getNext(TreeLinkNode pNode) {
        if(pNode == null) return pNode;

        //有右子树
        if(pNode.right != null) {
            TreeLinkNode nextNode = pNode.right;
            while (nextNode != null) {
                pNode = nextNode;
                nextNode = pNode.left;
            }
            return pNode;
        }
        //无右子树则向父亲节点找
        TreeLinkNode parent = pNode.next;
        while (parent != null && parent.right == pNode) {
            pNode = parent;
            parent = pNode.next;
        }
        return parent;
    }
}
