package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化(Serialize)是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树
 * 等遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）
 *
 * 二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 *
 * @link https://www.nowcoder.com/profile/818083792/codeBookDetail?submissionId=79064544
 *       https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/submissions/
 *
 * 思路：null节点用"#"来标记，并且每个节点末尾加"!"用以区别两个节点界限
 */
public class  序列化与反序列化二叉树_困难 {

    static String Serialize(TreeNode root) {
        return serialPreByRecursive(root); //递归先序 通过
//         return serialByPre(root);    //莫里斯迭代先序 通过
    }
    static TreeNode Deserialize(String str) {

        //1.递归解先序 通过
//        if(str == "") return null;

//         String[] arr = str.split("!");
//         Queue<String> dataQueue = new LinkedList<>(Arrays.asList(arr));
//         return deserializeHelper(dataQueue);

        //2.栈迭代解先序
        return deserializePreOrderByStack(str);
    }

    /**
     *********************************序列化************************************
     */

    //1.递归完成先序
    public static String serialPreByRecursive(TreeNode root){
        StringBuffer sb = new StringBuffer();
        if(root == null) {
            sb.append("#!");
            return sb.toString();
        }
        sb.append(root.val+"!");
        String s1 = serialPreByRecursive(root.left);
        String s2 = serialPreByRecursive(root.right);
        sb.append(s1).append(s2);
        return sb.toString();
    }

    //2.莫里斯迭代完成先序  如果要反序列化则应该通过 ”队列+递归“ 或者 “栈+迭代” 方式完成
    public static String serialPre(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if(root == null){
            sb.append("#!");
            return sb.toString();
        }

        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                sb.append(cur.val + "!" + "#!");
                cur = cur.right;
            }else{
                TreeNode pre = cur.left;
                while(pre.right != null && pre.right != cur){
                    pre = pre.right;
                }
                if(pre.right == null){
                    pre.right = cur;
                    sb.append(cur.val + "!");
                    cur = cur.left;
                }
                if(pre.right == cur){
                    pre.right = null;
                    sb.append("#!");  //因为mirrors遍历利用了左子树最右边节点的右孩子为空这个性质，所以需要在左子树最后加上null节点对应的"#!"
                    cur = cur.right;
                }
            }
        }
        sb.append("#!");   //因为最后退出while循环的时候右节点为null,所以需要补上
        return sb.toString();
    }

    //3.栈迭代完成先序
    public String serialPre2(TreeNode root){
        StringBuffer sb = new StringBuffer();
        if(root == null){
            sb.append("#!");
            return sb.toString();
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.push(cur);
        while(!stack.isEmpty()){
            cur = stack.pop();
            if(cur == null){
                sb.append("#!");
            }else{
                sb.append(cur.val + "!");
                stack.push(cur.right);
                stack.push(cur.left);
            }
        }
        return sb.toString();
    }

    //4.层次遍历完成序列化   与3相似
    public static String serialByLevel(TreeNode root){
        StringBuffer sb = new StringBuffer();
        if(root == null){
            sb.append("#!");
            return sb.toString();
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root == null){
                sb.append("#!");
            }else{
                sb.append(root.val + "!");
                queue.offer(root.left);
                queue.offer(root.right);
            }
        }
        return sb.toString();
    }

    /**
     *********************************反序列化************************************
     */

    //递归解先序  序列化字符串data需要先分解为List
    private TreeNode deserializeHelper(Queue<String> queue){
        //这里能够保证list不为空，因为进入之前会判断是否为#，不是的话直接返回
        String str = queue.poll();
        if(str.equals("#")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }

    //栈+迭代 解先序
    private static TreeNode deserializePreOrderByStack(String data){
        if(data == "" || "#!".equals(data)) return null;  //记得处理#!的情况

        String[] arr = data.split("!");
        LinkedList<String> dataQueue = new LinkedList<>(Arrays.asList(arr));

        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(Integer.parseInt(dataQueue.remove()));  //poll也可
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                if(!"#".equals(dataQueue.peek())) {
                    cur.left = new TreeNode(Integer.parseInt(dataQueue.remove()));
                } else {
                    dataQueue.remove();
                    cur.left = null;
                }
                cur = cur.left;
            }

            cur = stack.pop();

            if(!"#".equals(dataQueue.peek())) {
                cur.right = new TreeNode(Integer.parseInt(dataQueue.remove()));
            } else {
                dataQueue.remove();
                cur.right = null;
            }

            cur = cur.right;
        }
        return root;
    }

    //队列迭代的方式解层次序列化,将存储好的String类型数据去掉分割符号
    //将此时数据二叉树的先序遍历结果依次压入队列
    public TreeNode deserializeLevelTraversalByQueue(String data) {
        if(data == "" || "#!".equals(data)) return null;  //记得处理#!的情况

        String[] arr=data.split("!");
        Queue<String> data_queue = new LinkedList<>(Arrays.asList(arr));

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(data_queue.remove()));
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode cur = queue.remove();
            if("#".equals(data_queue.peek())){
                data_queue.remove();
                cur.left = null;
            }else{
                cur.left = new TreeNode(Integer.parseInt(data_queue.remove()));
                queue.add(cur.left);
            }

            if("#".equals(data_queue.peek())){
                data_queue.remove();
                cur.right = null;
            }else{
                cur.right = new TreeNode(Integer.parseInt(data_queue.remove()));
                queue.add(cur.right);
            }
        }
        return root;
    }
}
