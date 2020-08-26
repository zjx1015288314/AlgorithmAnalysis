package com.zjx.DataStructure.Charpter4;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class IsValidBST {

    /**
     * 最容易想到的
     * 方法一：中序遍历迭代版。利用BST的全局特性：单调非降性 这道题是严格的BST，不包含等于的情况，所以中序遍历是单调递增
     * 如果出现逆序对的情况，则说明不是BST。二叉树的中序遍历使用栈Stack来完成。
     * 这里small的初始值使用Long.MIN_VALUE来使程序启动，而不是Integer.MIN_VALUE，如果当树根节点val值为Integer.MIN_VALUE时
     * 也会认为是true而不至失败
     * 时间复杂度 : 最坏情况下(树为二叉搜索树或破坏条件的元素是最右叶结点)为O(N)。
     * 空间复杂度 : 最坏情况下为O(N)，用于存储 stack。
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        long small = Long.MIN_VALUE;
        //当root.right为null但stack非空时也要循环
        while (root != null || !stack.isEmpty()) {
            //关注树的左侧链，每当一个新节点入栈时，新节点的左侧链会一并入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //每个节点出栈后，其右孩子入栈
            root = stack.pop();
            //比中序遍历多了一步，判断是否单调递增
            if (root.val <= small) return false;
            small = root.val;
            root = root.right;
        }
        return true;

    }

    /**
     * 所有方法中最好的方法
     * 方法二：中序遍历递归版。利用一个全局变量prev作为当前节点的前一个节点并比较两节点值
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)?
     */
    TreeNode prev = null;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST2(root.left)) return false;
        if (prev != null && prev.val >= root.val) return false;
        prev = root;
        return isValidBST2(root.right);
    }

    /**
     * 方法三：递归思路：BST每个节点都有一个上界和一个下界，它不会超过上下界，上下界完全由该节点的父节点和一系列祖父节点决定
     * root节点无上下界，从root节点开始递归
     * 时间复杂度 : O(N) 每个结点访问一次
     * 空间复杂度 : 树高，最坏情况是O(N)，当BST为BBST时，树高是O(logN)
     *
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;
        int val = node.val;
        //这里体现了为什么使用Integer而不是int
        //先判断子树根节点，再判断左子树，最后判断右子树
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.left, lower, val)) return false;
        if (!helper(node.right, val, upper)) return false;
        return true;
    }

    /**
     * 方法四：将递归改为迭代，但是为了和递归一样保存每个节点的上下界，新建一个类MyNode保存，整体思路较为清晰
     * 迭代新增了while循环，判断条件为栈不为空，每个节点出栈后将其左右孩子入栈(为了保证不是null节点,入栈前要判非null)
     * 时间复杂度 : O(N)。每个结点访问一次。
     * 空间复杂度 : 树高，最坏情况是O(N)，当BST为BBST时，树高是O(logN)
     *
     * @param root
     * @return
     */
    public boolean isValidBST4(TreeNode root) {
        if (root == null) return true;
        Stack<MyNode> stack = new Stack<>();
        stack.push(new MyNode(root, null, null));
        while (!stack.isEmpty()) {
            MyNode myNode = stack.pop();
            TreeNode rNode = myNode.node;
            Integer lower = myNode.lower;
            Integer upper = myNode.upper;
            int val = myNode.node.val;
            //先判断子树根节点，再判断左子树，最后判断右子树
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            if (rNode.right != null) stack.push(new MyNode(rNode.right, val, upper));
            if (rNode.left != null) stack.push(new MyNode(rNode.left, lower, val));
        }
        return true;
    }

    class MyNode {
        TreeNode node;
        Integer lower;
        Integer upper;

        MyNode(TreeNode node, Integer lower, Integer upper) {
            this.node = node;
            this.lower = lower;
            this.upper = upper;
        }
    }

    /**
     * 方法五：思路与方法三类似，但采用了三个LinkedList实例而不是新建类，且不同于方法三，方法四属于广度优先搜索
     * 时间复杂度与方法三一样
     * 空间复杂度为层次上节点的最大个数,如第一层有1个节点，如果第二层有2个节点则最大个数为2
     */
    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST5(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    /**
     * 方法六：颜色节点法，与方法二类似，不过该方法是处理深度优先搜索(先序，中序，后序)时的万能模板，虽然比较费时(所有方法中最慢最耗空间)，但不用思考
     * 其中while循环内else判断因题而异，其余是固定模板.
     */
    TreeNode prev1 = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root, "white"));
        while (!stack.empty()) {
            ColorNode cn = stack.pop();
            if (cn.color.equals("white")) {
                if (cn.node.right != null) stack.push(new ColorNode(cn.node.right, "white"));
                stack.push(new ColorNode(cn.node, "gray"));
                if (cn.node.left != null) stack.push(new ColorNode(cn.node.left, "white"));
            } else if (prev1 != null && prev1.val >= cn.node.val) {
                return false;
            } else {
                prev1 = cn.node;
            }
        }
        return true;
    }

    class ColorNode {
        TreeNode node;
        String color;

        ColorNode(TreeNode node, String color) {
            this.node = node;
            this.color = color;
        }
    }


}
