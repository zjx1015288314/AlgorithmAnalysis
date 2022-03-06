package com.zjx.各公司2021笔试代码题汇总.字节;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 求叶子节点个数
 */
public class BD2020Test1重建二叉树计算叶子节点 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        long K = Long.parseLong(str[2]);   //long类型可表示2^19或者2^18
        //System.out.println(process(N,M,K));
    }

    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int pL, int pR, int iL, int iR) {
        if (pL > pR) {
            return null;
        }
        // 在中序遍历中定位根节点索引
        int inRootIdx = indexMap.get(preorder[pL]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[pL]);
        // 得到左子树中的节点数目
        int leftSize = inRootIdx - iL;

        root.left = myBuildTree(preorder, inorder, pL + 1, pL + leftSize, iL, inRootIdx - 1);
        root.right = myBuildTree(preorder, inorder, pL + leftSize + 1, pR, inRootIdx + 1, iR);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
