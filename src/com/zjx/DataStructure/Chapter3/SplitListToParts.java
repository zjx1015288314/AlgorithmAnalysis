package com.zjx.DataStructure.Chapter3;

/**
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * 返回一个符合上述规则的链表的列表。
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 * null, k = 3    //结果 [[],[],[]]
 * 提示:
 * root 的长度范围： [0, 1000].
 * 输入的每个节点的大小范围：[0, 999].
 * k 的取值范围： [1, 50].
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 */
public class SplitListToParts {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 这道题思路是先利用length/k,length%k判断每组有多少个节点，以及有多少剩余节点
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts1(ListNode root, int k) {
        ListNode[] result = new ListNode[k];
        //先统计链表长度
        int length = 0;
        ListNode curr = root;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        //通过商remainder得到每组大概有多少节点，通过余数quotient得到有多少节点剩余并将剩余节点一个一个分配到前几个组
        int remainder = length % k;
        int quotient = length / k;
        curr = root;
        for (int i = 0; i < k; i++) {
            //处理分到0个节点的情况;官方题解在这里不一样，创建了一个哑结点，则此处不需要if判断，而是放在for循环里：
            //for (int i = 0; i < k; ++i) {
            //     ListNode head = cur;
            //     for (int j = 0; j < width + (i < rem ? 1 : 0) - 1; ++j) {
            //          if (cur != null) cur = cur.next;
            //     }
            //     if (cur != null) {
            //         ListNode prev = cur;
            //         cur = cur.next;
            //         prev.next = null;
            //     }
            //     ans[i] = head;
            //}
            if (curr == null) {
                result[i] = null;
                continue;
            }
            //根据余数在分组分为两种情况：前面的分组每组会多获得一个节点；后面的分组获得正常的节点数
            for (int j = 0; j < (i < remainder ? quotient : quotient - 1); j++)
                curr = curr.next;
            //这里没有if判断也可以,但处理速度会下降
            if (curr != null) {
                ListNode tmp = curr.next;
                curr.next = null;
                result[i] = root;
                root = curr = tmp;
            }
        }
        return result;
    }
}
