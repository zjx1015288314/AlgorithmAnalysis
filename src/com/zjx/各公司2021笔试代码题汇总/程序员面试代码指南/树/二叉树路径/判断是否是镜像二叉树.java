package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树路径;

import com.zjx.DataStructure.Charpter4.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 镜像二叉树与对称二叉树的区别是中轴线不同，镜像二叉树的中轴线是根节点，而对称二叉树的中轴线是树的左右侧
 */
public class 判断是否是镜像二叉树 {
    public TreeNode Mirror (TreeNode pRoot) {
        //递归：DFS
//         if(pRoot == null) return null;
//         TreeNode right = pRoot.right;
//         pRoot.right = Mirror(pRoot.left);
//         pRoot.left = Mirror(right);
//         return pRoot;

        //队列循环：BFS
        if(pRoot == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(pRoot);
        while(!queue.isEmpty()) {
            TreeNode parent = queue.poll();

            if(parent.left != null) queue.offer(parent.left);
            if(parent.right != null) queue.offer(parent.right);

            TreeNode tmpRight = parent.right;
            parent.right = parent.left;
            parent.left = tmpRight;
        }
        return pRoot;
    }
}
