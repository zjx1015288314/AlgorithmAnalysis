package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/14
 * @since 1.0.0
 */
public class MaxSearchBinaryTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    static int max = 0;
    public static int maxSearchBinaryTree(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxSearchBinaryTree(root.left);
        int right = maxSearchBinaryTree(root.right);
        if(root.left == null && root.right == null){
            return 1;
        }
        if(left == 0 || right == 0){
            return 0;
        }
        if((root.left != null && root.val < root.left.val) ||
                (root.right != null && root.val > root.right.val)){
            return 0;
        }
        int res = left+right+1;
        max = Math.max(max,res);
        return res;
    }
    public static TreeNode createTree(BufferedReader br){
        try{
            //输入是以先序遍历的顺序输入的，所以这里要按先序来接受
            String[] arr = br.readLine().trim().split(" ");
            int data = Integer.parseInt(arr[0]);
            int left = Integer.parseInt(arr[1]);
            int right = Integer.parseInt(arr[2]);
            TreeNode root = new TreeNode(data);
            if(left != 0){
                root.left = createTree(br);
            }
            if(right != 0){
                root.right = createTree(br);
            }
            return root;
        }catch(IOException e){
            return null;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        TreeNode root = createTree(br);
        maxSearchBinaryTree(root);
        System.out.print(max);

    }
}
