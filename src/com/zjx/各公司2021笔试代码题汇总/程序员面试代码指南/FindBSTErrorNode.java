package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author zhaojiexiong
 * @create 2020/6/15
 * @since 1.0.0
 */
public class FindBSTErrorNode {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    //中序遍历，迭代
    public static void inOrder(TreeNode root){
        if(root==null) return ;
        TreeNode[] res = new TreeNode[2];
        TreeNode pre = null;
        TreeNode p = root;
        Stack<TreeNode> s = new Stack<>();
        while(p != null || !s.isEmpty()){
            while(p != null){
                s.push(p);
                p = p.left;
            }
            p = s.pop();
            if(pre != null && pre.val > p.val){
                res[0] = res[0] == null ? pre : res[0];
                res[1] = p;
            }
            pre = p;
            p = p.right;
        }
        System.out.println(res[1].val+" "+res[0].val);
    }
    //中序遍历，递归
    public static TreeNode inOrderByRecursive(TreeNode cur,TreeNode[] error,TreeNode pre){
        if(cur == null){
            return pre;
        }
        pre = inOrderByRecursive(cur.left,error,pre);
        if(pre != null && pre.val > cur.val){
            error[0] = error[0] == null ? pre : error[0];
            error[1] = cur;
        }
        pre = inOrderByRecursive(cur.right,error,cur);
        return pre;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        TreeNode root = createTree(br);

        //inOrder(root);   //栈迭代

        TreeNode[] error = new TreeNode[2];
        inOrderByRecursive(root,error,null);  //中序递归
        System.out.print(error[1].val + " " + error[0].val);
    }

    //递归建树
    public static TreeNode createTree(BufferedReader br){
        try{
            String[] ss = br.readLine().trim().split(" ");
            int data = Integer.parseInt(ss[0]);
            int left = Integer.parseInt(ss[1]);
            int right = Integer.parseInt(ss[2]);
            TreeNode root = new TreeNode(data);
            if(left!=0){
                root.left = createTree(br);
            }
            if(right!=0){
                root.right = createTree(br);
            }
            return root;
        }catch(Exception e){
            return null;
        }
    }
}
