package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.二叉树重建;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *
 */
public class 根据二叉搜索树后序遍历求中序遍历 {
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
     * 1 3 2 5 4 7 9 8 6
     * 单调递增栈     遇到比栈顶自己大的，则弹出栈顶元素 然后自己入栈
     * 思路是先序遍历整体和局部图形都是一个先递增再递减的趋势，根据这个特点排序
     */
    public static int[] process(int[] postorder){
        if(postorder == null || postorder.length <= 1) return postorder;
        int[] inorder = new int[postorder.length];
        int inIdx = postorder.length - 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = postorder.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() > postorder[i]){
                inorder[inIdx--] = stack.pop();
            }
            stack.push(postorder[i]);
        }
        while (!stack.isEmpty()){
            inorder[inIdx--] = stack.pop();
        }
        return inorder;
    }
}
