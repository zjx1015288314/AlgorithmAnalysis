package com.zjx.DataStructure.Charpter4;

import java.util.Stack;

public class SumNumbers {
    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int tmp = cur.val;
        while (cur != null || !stack.isEmpty()) {
            while (cur.left != null) {
                cur = cur.left;
                stack.push(cur);
                tmp = tmp * 10 + cur.val;
            }
            cur = stack.peek();
            if (cur.right == null) {
                cur = stack.pop();
                sum += tmp;
                tmp /= 10;
            } else {
                cur = cur.right;
                tmp = tmp * 10 + cur.val;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        sumNumbers(root);
    }
}

