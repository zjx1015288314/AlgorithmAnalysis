package com.zjx.DataStructure.Charpter4;

import java.util.Stack;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * 输出: [2,1,4,null,null,3]
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * 进阶:
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 */
public class RecoverTree {

    /**
     * 方法一：利用迭代版中序遍历找到逆序对(left是第一次碰到逆序时左边的值,right是最后一次逆序时右边的值)。当第一次碰到逆序时
     * left确定,而right是指最后一个比left小的节点，这样做是因为left-right间也存在比left小的值
     */
    TreeNode left, right;

    public void recoverTree1(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (left == null || (left.val < curr.val && right == null)) {
                left = curr;
            } else if (left.val > curr.val) {
                right = curr;
            }
            curr = curr.right;
        }
        if (left != null && right != null) {
            left.val = left.val ^ right.val;
            right.val = left.val ^ right.val;
            left.val = left.val ^ right.val;
        }
    }

    /**
     * 方法二：与方法一类似，但在找两个点时思路有差异：交换的位置的话就是两种情况。
     * 1)相邻的两个数字交换
     * [ 1 2 3 4 5 ] 中 2 和 3 进行交换，[ 1 3 2 4 5 ]，这样的话只产生一组逆序的数字（正常情况是从小到大排序，交换后产生了从大到小），3 2。
     * 我们只需要遍历数组，找到后，把这一组的两个数字进行交换即可。
     * 2)不相邻的两个数字交换
     * [ 1 2 3 4 5 ] 中 2 和 5 进行交换，[ 1 5 3 4 2 ]，这样的话其实就是产生了两组逆序的数字对。5 3 和 4 2。
     * 所以我们只需要遍历数组，然后找到这两组逆序对，然后把第一组前一个数字和第二组后一个数字进行交换即完成了还原。
     * 所以在中序遍历中，只需要利用一个 pre 节点和当前节点比较，如果 pre 节点的值大于当前节点的值，那么就是我们要找的逆序的数字。分别用两个指针 first 和 second 保存即可。如果找到第二组逆序的数字，我们就把 second 更新为当前节点。最后把 first 和 second 两个的数字交换即可。
     */


    public void recoverTree2(TreeNode root) {
        if (root == null) return;
        TreeNode left = null;
        TreeNode right = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null && prev.val > curr.val) {
                if (left == null) {
                    left = prev;
                    right = curr;
                } else {
                    right = curr;
                }
            }
            prev = curr;
            curr = curr.right;
        }
        if (left != null && right != null) {
            left.val = left.val ^ right.val;
            right.val = left.val ^ right.val;
            left.val = left.val ^ right.val;
        }
    }


    /**
     * 方法三：递归版中序遍历,递归版的难点也是在prev节点的处理，它也表示当前节点root的前一个节点.
     * 前三种方法的空间复杂度都是在O(h),时间复杂度都在O(N)  h为树高
     */
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree3(TreeNode root) {
        inorderTraversal(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    TreeNode pre = null;

    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left); //中序
        if (pre != null && root.val < pre.val) {
            if (first == null) {  //第一次遇到逆序对
                first = pre;
                second = root;

            } else {   //第二次遇到逆序对
                second = root;
            }
        }
        pre = root;   //Note that prev = root;
        inorderTraversal(root.right);
    }


    /**
     * 方法四：Morris遍历 O(1)空间复杂度, 将Morris遍历中涉及到保存cur的地方都更改为寻找两个节点操作，即情况1和4都要更改
     * 因为寻找两个节点也要用到前一个节点，所以用newPrev来表示寻找两个节点时的前一个节点，与Morris遍历本身用到的prev节点区别
     *
     * @param root
     */
    public void recoverTree4(TreeNode root) {
        if (root == null) return;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode newPrev = null;
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left == null) {
                //do something
                if (newPrev != null && newPrev.val > cur.val) {
                    if (left == null) {
                        left = newPrev;
                        right = cur;
                    } else {
                        right = cur;
                    }
                }
                newPrev = cur;
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                //情况3
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                }
                //情况4，不用担心前一个if会影响后一个，因为cur已经改变了
                if (prev.right == cur) {
                    prev.right = null;
                    //do something
                    if (newPrev != null && newPrev.val > cur.val) {
                        if (left == null) {
                            left = newPrev;
                            right = cur;
                        } else {
                            right = cur;
                        }
                    }
                    newPrev = cur;
                    cur = cur.right;
                }

            }
        }
        if (left != null && right != null) {
            left.val = left.val ^ right.val;
            right.val = left.val ^ right.val;
            left.val = left.val ^ right.val;
        }
    }


}
