package com.zjx.DataStructure.Charpter4;


import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 */
public class GenerateTrees {


    /**
     * 对于此问题，递归是最好的解法。在解题之前。需要了解卡特兰数：1，1,2,5,14,42...卡特兰数是组合数学中各种计数问题的数列
     * 设h(n)为数列的第n+1项，catalan数满足递推式：h(n) = h(0)*h(n-1) + h(1)*h(n-2) + ... + h(n-1)*h(0) (n>=2)
     * 得到通项公式：Cn = C(n,2n)/n+1 = C(n,2n)-C(n-1,2n) = (2n)!/[(n+1)!*n!]
     * 具体内容：http://lanqi.org/skills/10939/
     * 可以看出，满足此题的二叉搜索树的个数记为卡特兰数，
     * 那么如何构建这棵树?我们对i从1~n进行迭代，每次迭代都将i作为根节点，比i小的作为i的左子树，比i大的作为右子树，其中左右子树的求解
     * 分别使用递归完成，每次迭代都通过for循环嵌套将左右子树的组合添加到根节点上，并记录此次结果
     * <p>
     * 时间复杂度：nGn，Gn为卡特兰数，Gn以4^n/n^(3/2)速度增长,因此，总的时间复杂度为O(4^n/n^(1/2))
     * 空间复杂度 :Gn棵树，每个有 n 个元素，共计 nGn，也就是O(4^n/n^(1/2))
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
    }

    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }


    /**
     * 另一种思路从小往大生成，新来一数，肯定比现有的节点都大 那么n可以成为现在所有树的父节点，并且他们都是n的左子树
     * 第二步就是沿着现有子树的右侧尝试不断插入。 如果插入以后，n还有子树，那么这些子树都是n的左子树，但第二部实现起来比较
     * 麻烦，因为n的插入方式与右侧链的长度有关
     */


}
