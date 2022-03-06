package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/7/10
 * @since 1.0.0
 */
public class MaxBSTInBT {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public static class ReturnData {
        public int min;
        public int max;
        public TreeNode maxBSTHead;
        public int maxBSTSize;

        public ReturnData(int min, int max, TreeNode maxBSTHead, int maxBSTSize) {
            this.min = min;
            this.max = max;
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
        }
    }
    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(Integer.MAX_VALUE, Integer.MIN_VALUE, null, 0);
        }
        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);
        int min = Math.min(head.val,Math.min(leftData.min,rightData.min));
        int max = Math.max(head.val,Math.max(leftData.max,rightData.max));
        //只考虑可能性一和二
        int maxBSTSize = Math.max(leftData.maxBSTSize,rightData.maxBSTSize);
        TreeNode maxBSTHead = leftData.maxBSTSize > rightData.maxBSTSize ?
                leftData.maxBSTHead : rightData.maxBSTHead;

        //判断是否存在第三种可能性
        if (leftData.maxBSTHead == head.left && rightData.maxBSTHead == head.right
                && leftData.max < head.val
                && rightData.min > head.val) {
            maxBSTSize = leftData.maxBSTSize + rightData.maxBSTSize + 1;
            maxBSTHead = head;
        }
        return new ReturnData(min,max,maxBSTHead,maxBSTSize);
    }

    //递归中序遍历数，结果写入list
    private static int index = 0;
    public static void recurInorder(TreeNode head,TreeNode[] nodes,int n){
        if(head == null) return;
        recurInorder(head.left,nodes,n);
        nodes[index++] = head;
        recurInorder(head.right,nodes,n);
    }

    public static int process1(TreeNode head,int n){
        if(head == null) return 0;
        TreeNode[] nodes = new TreeNode[n];
        recurInorder(head,nodes,n);
        int maxLen = 0;
        int i = 0;
        while(i < nodes.length){
            int j = i;
            for(; j < nodes.length - 1; j++){
                if(nodes[j].val >= nodes[j + 1].val){
                    break;
                }
            }
            maxLen = Math.max(maxLen,j - i + 1);
            i = j + 1;
        }
        return maxLen;
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
        int n = Integer.parseInt(br.readLine().split(" ")[0]);
        TreeNode root = createTree(br);
        //ReturnData res = process(root);  //通过
        //System.out.print(res.maxBSTSize);

        System.out.print(process1(root,n));
    }
}
