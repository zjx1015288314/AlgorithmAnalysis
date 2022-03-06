package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树重建;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 后序转中序是同样的道理 只不过维护单调递增序列
 */
public class YFD根据二叉搜索树先序遍历求中序遍历 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] arr = new int[N];
        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        int[] res = process(arr);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    /**
     * 9
     * 6 4 2 1 3 5 8 7 9
     * 单调递减栈     遇到比栈顶自己小的，则弹出栈顶元素 然后自己入栈
     * 思路是先序遍历整体和局部图形都是一个先递减再递增的趋势，根据这个特点排序
     */
    public static int[] process(int[] preorder){
        if(preorder == null || preorder.length <= 1) return preorder;
        int[] inorder = new int[preorder.length];
        int inIdx = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < preorder.length; i++) {
            while(!stack.isEmpty() && stack.peek() < preorder[i]){
                inorder[inIdx++] = stack.pop();
            }
            stack.push(preorder[i]);
        }
        while (!stack.isEmpty()){
            inorder[inIdx++] = stack.pop();
        }
        return inorder;
    }
}
