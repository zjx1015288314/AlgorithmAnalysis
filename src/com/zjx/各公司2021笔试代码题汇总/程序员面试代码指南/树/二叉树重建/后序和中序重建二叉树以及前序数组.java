package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树重建;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class 后序和中序重建二叉树以及前序数组 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

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
        TreeNode cur = reConPostBinaryTree(arr1,arr2); //后 + 中 -> 重建
        while (cur != null){
            if (cur.left == null){
                sb.append(cur.val + " ");
                cur = cur.right;
            }else{
                TreeNode mostLeft = cur.left;
                while(mostLeft.right != null && mostLeft.right != cur){
                    mostLeft = mostLeft.right;
                }
                if(mostLeft.right == null){
                    mostLeft.right = cur;
                    sb.append(cur.val + " ");
                    cur = cur.left;
                }
                if(mostLeft.right == cur){
                    mostLeft.right = null;
                    cur = cur.right;
                }
            }
        }
        System.out.println(sb);
    }

    static HashMap<Integer, Integer> indexForInOrders = new HashMap<>();

    /**
     * 后序和中序重建二叉树
     * 8
     * 7 4 2 5 8 6 3 1
     * 8
     * 4 7 2 1 5 3 8 6
     */
    public static TreeNode reConPostBinaryTree(int[] post, int[] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }

        TreeNode root = reConPostBinaryTree(post, 0, post.length - 1, 0);

        int[] pre = new int[post.length];
        reConstrcutPreOrderArr(post, 0, post.length - 1, 0, pre, 0);
        System.out.println(Arrays.toString(pre));

        return root;
    }

    //这里只是重建树，并不要求直接生成后序数组，所以只用到了inL
    private static TreeNode reConPostBinaryTree(int[] post, int postL, int postR, int inL) {
        if (postL > postR) {
            return null;
        }

        TreeNode root = new TreeNode(post[postR]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;  //左子树节点个数
        root.left = reConPostBinaryTree(post, postL, postL + leftTreeSize - 1, inL);
        root.right = reConPostBinaryTree(post, postL + leftTreeSize, postR - 1, inIndex + 1);
        return root;
    }

    /**
     * 后序和中序重建前序数组
     * @param preIdx  从前到后赋值，每次只放一个，把当前根节点即post[postR]放入到pre[preIdx]
     */
    public static int reConstrcutPreOrderArr(int[] post, int postL, int postR, int inL, int[] pre, int preIdx) {
        if (postL > postR) {
            return preIdx;
        }
        pre[preIdx++] = post[postR];

        int rootIdx = indexForInOrders.get(post[postR]);
        int leftSize = rootIdx - inL;
        preIdx = reConstrcutPreOrderArr(post, postL, postL + leftSize - 1, inL, pre, preIdx);
        preIdx = reConstrcutPreOrderArr(post, postL + leftSize, postR - 1, rootIdx + 1, pre, preIdx);
        return preIdx;
    }
}
