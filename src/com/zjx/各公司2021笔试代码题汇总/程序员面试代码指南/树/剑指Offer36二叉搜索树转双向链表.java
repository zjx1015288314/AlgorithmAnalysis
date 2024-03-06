package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 剑指Offer36二叉搜索树转双向链表 {
    //定义Node节点
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        Node head = createSearchTree(br);
        head = convert1(head);
//        Node head2 = convert2(head);
//        Node head3 = convert3(head);
        StringBuilder sb = new StringBuilder();
        while(head != null){
            sb.append(head.value).append(" ");
            head = head.right;
        }
        System.out.println(sb.toString().trim());
        br.close();
    }

    //自己创建二叉树
    public static Node createSearchTree(BufferedReader br){
        try{
            String[] datas = br.readLine().trim().split(" ");
            int value = Integer.parseInt(datas[0]);
            int left = Integer.parseInt(datas[1]);
            int right = Integer.parseInt(datas[2]);
            Node head = new Node(value);
            if(0 != left){
                head.left = createSearchTree(br);
            }
            if(0 != right){
                head.right = createSearchTree(br);
            }
            return head;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 第一种思路，中序遍历+Queue储存结果 -> 转化为链表
     */
    private static Node convert1(Node head){
        Queue<Node> queue = new LinkedList<Node>();
        inOrderToQueue(head, queue);

        head = queue.poll();
        Node pre = head;
        head.left = null;
        Node cur = null;
        while(!queue.isEmpty()){
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }
    //中序递归遍历
    private static void inOrderToQueue(Node head, Queue<Node> queue){
        if(head == null){
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    /**
     * 第二种思路，利用栈实现中序遍历
     * @link{com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.中序遍历相关.中序遍历}
     */
    public static Node convert2(Node pRootOfTree) {
        Stack<Node> stack = new Stack<>();
        Node head = null;
        Node pre = null;
        Node curr = pRootOfTree;
        while(curr != null || !stack.isEmpty()){
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if(head == null) head = curr;

            if(pre != null) pre.right = curr;
            curr.left = pre;
            pre = curr;

            curr = curr.right;
        }
        return head;
    }

    /**
     * 第三种思路，树形DP，自定义返回值ReturnType类
     */
    public static class ReturnType{
        public Node start;
        public Node end;
        public ReturnType(Node start, Node end){
            this.start = start;
            this.end = end;
        }
    }

    public static Node convert3(Node head){
        if(head == null){
            return null;
        }
        return process(head).start;
    }

    public static ReturnType process(Node head){
        if(head == null){
            return new ReturnType(null, null);
        }
        ReturnType leftList = process(head.left);
        ReturnType rightList = process(head.right);
        if(leftList.end != null){
            leftList.end.right = head;
        }
        head.left = leftList.end;
        head.right = rightList.start;
        if(rightList.start != null){
            rightList.start.left = head;
        }
        return new ReturnType(leftList.start != null ? leftList.start :head, rightList.end != null ? rightList.end : head);
    }
}
