package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.链表;

import java.util.Stack;

/**
 * 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ...
 * 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 
 * 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。
 * 如果不存在这样的 j，那么下一个更大值为 0 。
 * 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
 *
 * 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示
 * 示例：
 *
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list
 */
public class 链表中下一个更大的节点 {
    /**
     * 因为是要得到比arr[i]靠后且第一个比arr[i]大的，所以需要维护
     * 一个从右边看过去单调递增的数组，即单调栈
     */
    public int[] nextLargerNodes(ListNode head) {
        if(head == null) return null;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode cur = head;
        int length = 0;

        while(cur != null){
            length++;
            stack1.push(cur.val);
            cur = cur.next;
        }
        
        int[] result = new int[length];
        while(!stack1.empty()){
            int prev = stack1.pop();
            while(!stack2.empty() && prev >= stack2.peek()) {
                stack2.pop();
            }
            result[--length] = !stack2.empty()? stack2.peek() : 0;
            stack2.push(prev);
        }
        return result;
    }
}
