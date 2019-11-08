package com.zjx.DataStructure.Charpter4;

/**
 * LeetCode链接:https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode/
 * 反转从位置 m 到 n 的链表,请使用一趟扫描完成反转
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 思路:该题有递归与迭代两种方法,迭代方法需要dummy,con,(prev,curr,succ,这三个节点是为了反转链表,只靠前两个则迭代无法继续)
 * 需要注意的是,被反转的子链表的头部就是con.next,为了就是迭代结束时的curr
 */
public class ReverBetween {

    /**
     * 迭代
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween1(ListNode head, int m, int n) {
        if(head == null) return head;
        ListNode dummy,con,succ;
        dummy = new ListNode(0);
        dummy.next = head;
        con = dummy;
        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode curr = head, prev = dummy;
        while (m > 1) {
            con = prev = curr;
            curr = curr.next;
            m--;
            n--;
        }

        for(int i = m; i < n; i++){
            succ = curr.next;
            curr.next = prev;
            prev = curr;
            curr = succ;
        }
        if(m < n){
            con.next.next = curr.next;
            curr.next = prev;
            con.next = curr;
        }
        return dummy.next;
    }

    /**
     * 使用递归反转链表的思路来源于反转字符串时使用的类似方法,反转字符串的一个巨大优势是可以使用下标信息.我们可以创建两个指针,一个开头,一个结尾.不断地交换这两个指针指向的元素,
     * 并将两个指针向中间移动.反转给定链表的一部分的思路基于上述方法.一个指向第 m 个结点，另一个指向第 n 个结点
     * 一旦有了这两个指针,我们就可以不断地交换这两个指针指向结点的数据,并将两个指针相向移动,就像字符串的情况那样,然而,
     * 链表中没有向后指针,也没有下标.因此,我们需要使用递归来模拟向后指针.递归中的回溯可以帮助我们模拟一个指针从第 n 个结点向中心移动的移动过程
     * 算法:
     *      1.我们定义一个递归函数用于反转给定链表的一部分
     *      2.将函数记为 recurse.该函数使用三个参数: m 为反转的起点, n 为反转的终点, 以及从第 n 个结点开始，随着递归回溯过程向后移动的指针 right
     *      3.此外，我们还有一个指针 left，它从第 m 个结点开始向前移动.我们需要一个全局变量，值随着递归的进行而改变.在其他函数调用造
     *      成的变化可以持续的编程语言中，可以考虑将该指针加为函数recurse()的一个变量
     *      4.在递归调用中，给定 m,n,和 right, 首先判断 n = 1.若判断为真, 则结束
     *      5.于是,当 n 的值达到 1 时,我们便回溯.这时,right 指针在我们要反转的子链表结尾,left 到达了字列表的开头.于是,我们置换数据,
     *      并将 left 指针前移:left = left.next.我们需要此变化在回溯过程中保持.
     *      6.自此，每当我们回溯时,right 指针向后移一位.这就是前文所说的模拟.通过回溯模拟向后移动.
     *      当 right == left 或者 right.next == left 时停止交换。当子链表的长度为奇数时，情况为前者;当子链表长度为偶数时为后者
     *      我们使用一个全局 boolean 变量 flag 来停止交换
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween2(ListNode head, int m, int n) {
        left = head;
        stop = false;
        recurseAndReverse(head, m, n);
        return head;
    }

    private static boolean stop;
    private static ListNode left;   //这里left与right的处理不一样,两指针向中间靠拢的时候,left是正向移动,right无法反向移动
                                    //需要通过回溯回到上一层的right指针位置,

    public static void recurseAndReverse(ListNode right, int m, int n) {

        // base case. Don't proceed any further
        if (n == 1) {
            return;
        }

        // Keep moving the right pointer one step forward until (n == 1)
        right = right.next;

        // Keep moving left pointer to the right until we reach the proper node
        // from where the reversal is to start.
        if (m > 1) {
            left = left.next;
        }

        // Recurse with m and n reduced.
        recurseAndReverse(right, m - 1, n - 1);

        // In case both the pointers cross each other or become equal, we
        // stop i.e. don't swap data any further. We are done reversing at this
        // point.
        if (left == right || right.next == left) {
            stop = true;
        }

        // Until the boolean stop is false, swap data between the two pointers
        if (!stop) {
            int t = left.val;
            left.val = right.val;
            right.val = t;

            // Move left one step to the right.
            // The right pointer moves one step back via backtracking.
            left = left.next;    //left右移
        }
    }


    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode test = new ListNode(1);
        for (int i = 2; i < 6; i++) {
            test.next = new ListNode(i);
            test = test.next;
        }
        test.next = null;
        reverseBetween1(test,2,4);
    }
}
