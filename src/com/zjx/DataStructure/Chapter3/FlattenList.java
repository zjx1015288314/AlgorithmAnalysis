package com.zjx.DataStructure.Chapter3;

/**
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。
 * 这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 * 示例:
 * 输入:
 * 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL
 * <p>
 * 输出:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 */
public class FlattenList {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    /**
     * 方法一：自己思路是深度优先搜索/先序遍历，把链表的child节点看作是树节点的左孩子，next节点看做是右孩子
     * process()处理后返回作为输入的head头节点，并将类变量end指向此次处理的链表的尾结点以方便对接
     * 本题难点在于：递归与迭代的结合，对于左孩子的处理是通过递归进入的，对于右孩子的处理则是迭代(while循环)完成,
     * 其中对于end节点的两次赋值需要注意：第一次赋值是在进入递归前赋值为父亲节点，在递归处理完城后赋值为左子树(child节点连)的尾结点
     *
     * @param head
     * @return
     */
    Node end;

    public Node flatten1(Node head) {
        process(head);
        return head;
    }

    public Node process(Node head) {
        if (head == null) return null;
        Node start = head;
        while (head != null) {
            end = head;     //此处需注意，缺少这步无法完成
            Node succ = head.next;
            Node tmp = process(head.child);
            head.child = null;  //处理完左子树后将child置为null
            //如果存在左孩子，则进行链接
            if (tmp != null) {
                head.next = tmp;
                tmp.prev = head;
                end.next = succ;
                //如果存在右孩子，则进行链接并更新end节点
                if (succ != null) {
                    succ.prev = end;
                    end = succ;
                }
            }
            head = succ;
        }
        return start;
    }

    /**
     * 方法二：由方法一中的迭代+递归改为分别递归左右子树，递归方法返回尾结点。结构更加清晰。
     * 本方法最经典的是对尾结点返回时的三种情况的处理。值得学习
     */
    Node flatten2(Node head) {
        flattenTail(head);
        return head;
    }

    private Node flattenTail(Node root) {
        if (root == null) return null;
        Node leftTail = flattenTail(root.child);
        Node rightTail = flattenTail(root.next);
        if (root.child != null) {//只有当左子树存在时才将它插入右子树中
            Node temp = root.next;
            root.next = root.child;
            root.child.prev = root;
            root.child = null;
            leftTail.next = temp;
            if (temp != null) //注意root的next可能是null, 这里面要剔除这种情况
                temp.prev = leftTail;
        }

        //返回尾部元素时，需要特殊处理
        // (1) 有右子树的情况
        if (rightTail != null) return rightTail;
        // (2) 无右子树但有左子树的情况
        if (leftTail != null) return leftTail;
        // (3)左右子树均不存在的情况。
        return root;
    }
}
