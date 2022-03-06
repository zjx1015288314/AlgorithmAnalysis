package com.zjx.各公司2021笔试代码题汇总.华为.面试;

public class HW20210915 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    static class ReturnData{
        boolean isBalanced;
        int height;
        public ReturnData(boolean isBalanced, int height){
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node = root;
        node.left = new TreeNode(1);
        node.right = new TreeNode(2);
        node = node.left;
        node.left = new TreeNode(3);
        node = node.left;
        node.left = new TreeNode(4);
        System.out.println(isBalanced(root).isBalanced);
    }


    public static ReturnData isBalanced(TreeNode root){
        if(root == null) return new ReturnData(true,0);

        ReturnData leftData = isBalanced(root.left);
        ReturnData rightData = isBalanced(root.right);

        int maxHeight = Math.max(leftData.height,rightData.height) + 1;
        boolean flag = leftData.isBalanced && rightData.isBalanced &&
                Math.abs(leftData.height - rightData.height) <= 1;
        return new ReturnData(flag,maxHeight);
    }
}
