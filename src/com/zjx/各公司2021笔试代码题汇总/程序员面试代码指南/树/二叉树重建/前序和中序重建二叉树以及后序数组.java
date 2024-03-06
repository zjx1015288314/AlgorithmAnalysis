package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树重建;

import com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class 前序和中序重建二叉树以及后序数组 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len1 = Integer.parseInt(br.readLine());
        String[] ss = br.readLine().split(" ");

        int arr1[] = new int[len1];
        for (int i = 0; i < len1; i++) {
            arr1[i] = Integer.parseInt(ss[i]);
        }
        int len2 = Integer.parseInt(br.readLine());
        ss = br.readLine().split(" ");
        int arr2[] = new int[len2];
        for (int i = 0; i < len2; i++) {
            arr2[i] = Integer.parseInt(ss[i]);
        }

        StringBuffer sb = new StringBuffer();
        TreeNode cur = reConstructBinaryTree(arr1,arr2); //前 + 中 -> 重建
        while (cur != null){
            if (cur.right == null){
                sb.append(cur.val + " ");
                cur = cur.left;
            }else{
                TreeNode mostLeft = cur.right;
                while(mostLeft.left != null && mostLeft.left != cur){
                    mostLeft = mostLeft.left;
                }
                if(mostLeft.left == null){
                    mostLeft.left = cur;
                    sb.append(cur.val + " ");
                    cur = cur.right;
                }
                if(mostLeft.left == cur){
                    mostLeft.left = null;
                    cur = cur.left;
                }
            }
        }
        System.out.println(sb.reverse());
    }

    static HashMap<Integer, Integer> indexForInOrders = new HashMap<>();

    /**
     * 前序和中序重建二叉树
     * 8
     * 1 2 4 7 3 5 6 8
     * 8
     * 4 7 2 1 5 3 8 6
     */
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++)
            indexForInOrders.put(in[i], i);
        //重建二叉树
        TreeNode root = reConstructBinaryTree(pre, 0, pre.length - 1, 0);
        //重建后序数组
        int[] post = new int[pre.length];
        reConstructPostOrderArr(pre, 0, pre.length - 1, 0, post, post.length - 1);
        System.out.println(Arrays.toString(post));

        return root;
    }

    private static TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;  //左子树节点个数
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inIndex + 1);
        return root;
    }

    /**
     * 前序和中序重建后序数组
     */
    private static int reConstructPostOrderArr(int[] pre, int preL, int preR, int inL, int[] post, int postIdx) {
        if (preL > preR) {
            return postIdx;
        }
        post[postIdx--] = pre[preL];

        int rootIdx = indexForInOrders.get(pre[preL]);
        int leftSize = rootIdx - inL;
        postIdx = reConstructPostOrderArr(pre, preL + leftSize + 1, preR, rootIdx + 1, post, postIdx);
        postIdx = reConstructPostOrderArr(pre, preL + 1, preL + leftSize, inL, post, postIdx);
        return postIdx;
    }
}
