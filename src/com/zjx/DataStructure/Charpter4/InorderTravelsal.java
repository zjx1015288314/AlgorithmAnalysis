package com.zjx.DataStructure.Charpter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序遍历。(注意方法三)
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 */
public class InorderTravelsal {

    /**
     * 方法一:自己的思路是：为了在遍历完左子树节点后能够向上寻找父节点、父节点的右子树，甚至祖父节点，所以使用了堆栈来存储一系列祖父节点
     * 如果节点的左子树不为空则将该节点压入堆栈并succ = succ.left,否则将节点值加入链表并succ = succ.right
     * 如果到达叶节点，则使用flag = true来表明栈中某个节点的左子树已经遍历完成，而不是一个刚入栈且左子树还未遍历的父节点，如果flag = false，则
     * 继续向下遍历左子树。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) return new LinkedList<Integer>();
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode succ = root;
        boolean flag = false;
        while (succ != null) {
            if (flag) {
                result.add(succ.val);
                if (succ.right != null) {
                    succ = succ.right;
                    flag = false;
                } else
                    succ = !stack.empty() ? stack.pop() : null;
                continue;
            }
            if (succ.left != null) {
                stack.add(succ);
                succ = succ.left;
            } else if (succ.right != null) {
                result.add(succ.val);
                succ = succ.right;
            } else {
                result.add(succ.val);
                succ = !stack.empty() ? stack.pop() : null;
                flag = true;
            }
        }
        return result;
    }


    /**
     * 方法二：官方解法比较简洁，舍去了flag变量，因为这个方法在遍历完左子树节点后，立马弹出了栈中父节点，并将右子树节点压入
     * 这样就避免了还要区分栈顶节点的左子树是否已经遍历；并且在外层while循环判断时加入了栈不为空的条件处理右子树节点为空时
     * 无法继续循环的问题.
     * 还有一个最重要的区别是两个while循环判断条件一致都是curr ！= null  而方法是succ != null和succ.next != null
     * 这样就导致了多次迭代无法衔接起来，所以也可以学习方法二更正方法一
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    /**
     * 方法一更正之后，虽然复杂度没变，但是代码更加简洁
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode succ = root;
        while (succ != null || !stack.empty()) {
            if (succ != null) {
                stack.add(succ);
                succ = succ.left;
            } else {
                succ = stack.pop();
                result.add(succ.val);
                succ = succ.right;
            }
        }
        return result;
    }

    /**
     * 方法三：很新颖的思路：
     * 官方题解中介绍了三种方法来完成树的中序遍历，包括：递归、借助栈的迭代方法、莫里斯遍历
     * 在树的深度优先遍历中（包括前序、中序、后序遍历），递归方法最为直观易懂，但考虑到效率，我们通常不推荐使用递归。
     * 栈迭代方法虽然提高了效率，但其嵌套循环却非常烧脑，不易理解，容易造成“一看就懂，一写就废”的窘况。
     * 而且对于不同的遍历顺序（前序、中序、后序），循环结构差异很大，更增加了记忆负担。
     * 因此，我在这里介绍一种“颜色标记法”（瞎起的名字……），兼具栈迭代方法的高效，又像递归方法一样简洁易懂，更重要的是，
     * 这种方法对于前序、中序、后序遍历，能够写出完全一致的代码。
     * 其核心思想如下：
     *  使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
     *  如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
     *  如果遇到的节点为灰色，则将节点的值输出。
     *  使用这种方法实现的中序遍历如下：
     * 作者：hzhu212
     * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
     * 来源：力扣（LeetCode）
     */
    //声明自定义节点类
    class ColorNode {
        TreeNode node;
        String color;

        public ColorNode(TreeNode node, String color) {
            this.node = node;
            this.color = color;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root, "white"));

        while (!stack.empty()) {
            ColorNode cn = stack.pop();

            //该方法中三条语句的顺序可以按照具体的遍历顺序而定，此题要求中序遍历，所以压入堆栈的顺序是：右 根 左
            //如果是先序遍历，则依:右 左 根，压入堆栈
            if (cn.color.equals("white")) {
                if (cn.node.right != null) stack.push(new ColorNode(cn.node.right, "white"));
                stack.push(new ColorNode(cn.node, "gray"));
                if (cn.node.left != null) stack.push(new ColorNode(cn.node.left, "white"));
            } else {
                res.add(cn.node.val);
            }
        }
        return res;
    }
}
