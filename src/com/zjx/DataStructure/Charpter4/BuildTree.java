package com.zjx.DataStructure.Charpter4;

import java.util.*;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * 3
 * / \
 * 9  20
 * /    \
 * 15     7
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 */
public class BuildTree {
    /**
     * 方法一：来自大佬的思路，链接：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34543/Simple-O(n)-without-map
     * 思路：考虑以下输入：
     * 预购：[1、2、4、5、3、6]
     * 顺序：[4、2、5、1、6、3]
     * 构造树的明显方法是：
     * 将preorder的第一个元素1用作根,并在inorder中搜索它，并以其为界限将inorder拆分，分成[4，2，5]和[6，3]。
     * 将其余的预购分为两部分将有序部分分为[2，4，5]和[3，6]。使用preorder = [2，4，5]和inorder = [4，2，5]添加左子树。
     * 使用preorder = [3，6] andinorder = [6，3]添加右子树。
     * 但是，请考虑以下最坏的情况：一棵不平衡的树，只是左侧的一条直线。那么inorder是preorder的逆序，在inorder中搜索的开销，总体上为O(n^2)
     * 另外，根据您如何"拆分"数组，可能获得O(n^2)时间复杂度以及可能到O(n^2)空间复杂度。
     * 在开始主要工作之前，可以通过建立从值到索引的映射来将搜索的运行时间降低到O（n），我已经看到了几种解决方案。
     * 但是，这是O（n）的额外空间，并且拆分问题仍然存在。要解决这些问题，您可以将指针分为preorder和inorder，而不是拆分它们。
     * 而且，当您执行此操作时，也不需要值与索引的映射。再次考虑该示例。不必按顺序找到1，再将数组拆分为多个部分并对其进行递归，
     * 而只需对剩余的全部数组进行递归，然后在inorder中遇到1时就停止。这就是我上面的解决方案所做的。每个递归调用都被告知要在哪里停止，
     * 并告诉子调用要在哪里停止。它将自己的根值作为其左子调用的停止符，将其父级的子作为其右子调用的停止符。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, (long) Integer.MAX_VALUE + 1);
    }

    int pre = 0;
    int in = 0;

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
        //到达末尾返回 null
        if (pre == preorder.length) {
            return null;
        }
        //到达停止点返回 null
        //当前停止点已经用了，in 后移
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        int root_val = preorder[pre++];
        TreeNode root = new TreeNode(root_val);
        //左子树的停止点是当前的根节点
        root.left = buildTreeHelper(preorder, inorder, root_val);
        //右子树的停止点是当前树的停止点
        root.right = buildTreeHelper(preorder, inorder, stop);
        return root;
    }

    /**
     * 方法二：思路:这道题是以inorder为基础展开的，如果能找到根节点，则可以将数组inorder"一分为二",并利用递归求解；
     * 关键在于如何找到根节点，此时就要用到preorder,preorder的第一个节点定义为根节点,再依此节点一分为二，并将preidx++，
     * preidx要遍历preorder中的每个元素并判断是否为树根节点，若是根节点，则preidx++;否则直接进入右子树。
     * 这里有两个重点：一个是利用inorder采取"二分法";另一个是设preidx为全局变量,并遍历preorder中每一个元素；
     * 利用HashMap存储inorder元素对应的索引
     */
    // start from first preorder element
    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper2(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right)
            return null;

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper2(in_left, index); 
        // build right subtree
        root.right = helper2(index + 1, in_right);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper2(0, inorder.length);
    }


    /**
     * 方法三：对方法二的小改动；主要改动地方有：
     * 1) if判断等于改为大于
     * 2) helper形参由左闭右开，改为左闭右闭，所以在主函数传参时传入(0,length-1)；即三个helper1()要么都是由左闭右开，要么是左闭右闭
     * 注意：左边都是闭区间，所以第一个参数不用动，即分区间时根节点不计入左右区间
     */
    // start from first preorder element
    int pre_idx1 = 0;
    int[] preorder1;
    int[] inorder1;
    HashMap<Integer, Integer> idx_map1 = new HashMap<Integer, Integer>();

    public TreeNode helper3(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right)
            return null;

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper3(in_left, index - 1);
        // build right subtree
        root.right = helper3(index + 1, in_right);
        return root;
    }

    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper3(0, inorder.length - 1);
    }

    /**
     * 方法四：上面的递归方法聚焦于inorder数组，执行分支思想；如果我们聚焦于preorder数组，则可通过迭代来完成。
     * 具体为：preorder开始的连续m个元素是树的左侧链节点，将其依次入栈，第m+1个元素(存在的话)必然是某个节点的右孩子节点，但具体是
     * 哪个节点暂且不知道，我们可以从inorder数组中找出来，inorder数组的第一个元素是树的最左边的节点，第二个节点是第一个节点的
     * 父节点，但第三个节点可能是父节点的父节点(即第三个节点是preorder数组的第m-1个元素),也可能是父节点的右孩子节点(即第三个节点
     * 是preorder数组的第m+1个元素)。如果是第一种情况则从将栈顶元素弹出，并进入下一次迭代；否则就第三个节点为右孩子构建右子树再入栈、迭代
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree4(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Stack<TreeNode> roots = new Stack<TreeNode>();
        int pre = 0;
        int in = 0;
        //先序遍历第一个值作为根节点
        TreeNode curRoot = new TreeNode(preorder[pre]);
        TreeNode root = curRoot;  //这是树的根节点，需要保留并返回
        roots.push(curRoot);
        pre++;
        //遍历前序遍历的数组
        while (pre < preorder.length) {
            //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
            if (curRoot.val == inorder[in]) {
                //每次进行出栈，实现倒着遍历
                while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                    curRoot = roots.peek();
                    roots.pop();
                    in++;
                }
                //设为当前的右孩子
                curRoot.right = new TreeNode(preorder[pre]);
                //更新 curRoot
                curRoot = curRoot.right;
                roots.push(curRoot);
                pre++;
            } else {
                //否则的话就一直作为左子树
                curRoot.left = new TreeNode(preorder[pre]);
                curRoot = curRoot.left;
                roots.push(curRoot);
                pre++;
            }
        }
        return root;
    }


    /**
     * 方法五：耗时最长，最容易想到的方法  但时间复杂度为O(NlogN)  空间复杂度为O(N)
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree5(int[] preorder, int[] inorder) {
        if (preorder.length > 0 && inorder.length > 0) {
            TreeNode root = new TreeNode(preorder[0]);
            //root.val=preorder[0];
            int num = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (inorder[i] == root.val) {
                    num = i;
                    break;
                }
            }
            //这里如果不熟悉API则不好想到
            int[] preLeft = Arrays.copyOfRange(preorder, 1, num + 1);
            int[] preRight = Arrays.copyOfRange(preorder, num + 1, preorder.length);

            int[] inoLeft = Arrays.copyOfRange(inorder, 0, num);
            int[] inoRight = Arrays.copyOfRange(inorder, num + 1, inorder.length);
            root.left = buildTree4(preLeft, inoLeft);
            root.right = buildTree4(preRight, inoRight);
            return root;
        } else {
            return null;
        }

    }
}
