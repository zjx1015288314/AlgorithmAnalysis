package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历;

/**
 * 给定一个[完美二叉树]，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * @see 填充二叉树每个节点的下一个右侧节点指针II
 *
 * 里面的是二叉树,有区别
 * 二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有next 指针都被设置为 NULL。

 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 填充完全二叉树每个节点的下一个右侧节点指针 {
    /**
     * DFS递归， 在进入下一层递归前 先把右孩子节点的next指针指向自己next节点的左孩子(如果存在的话)
     */
    public Node connect1(Node root) {
        if(root == null || root.left == null) return root;  //根据题中定义root.left != null的同时root.right != null

        root.left.next = root.right;
        //!!!!!!!!
        if(root.next != null) {
            root.right.next = root.next.left;
        }
        connect1(root.left);
        connect1(root.right);
        return root;
    }

    /**
     * 每次处理一层,在处理前持有下一层第一个节点
     */
    public Node connect2(Node root) {
        if(root == null) return null;
        Node cur = root;
        while(cur != null){
            Node first = cur.left;
            while(cur != null){
                if(cur.left != null) cur.left.next = cur.right;
                if(cur.right != null && cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            cur = first;
        }
        return root;
    }

    /**
     * 方法三：
     * @see com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.层序遍历.填充二叉树每个节点的下一个右侧节点指针II
     */
    public Node connect3(Node root) {
        Node cur = root;
        while(cur != null){
            Node dummy = new Node();
            Node tail = dummy;
            while(cur != null){
                if(cur.left != null){
                    tail = tail.next = cur.left;
                }
                if(cur.right != null){
                    tail = tail.next = cur.right;
                }
                cur = cur.next;
            }
            cur = dummy.next;
        }
        return root;
    }
}
