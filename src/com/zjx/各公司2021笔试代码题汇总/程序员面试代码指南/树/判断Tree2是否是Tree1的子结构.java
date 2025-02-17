package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * （ps：我们约定空树不是任意一个树的子结构）
 *
 * @link https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 判断Tree2是否是Tree1的子结构 {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null) return false;

        boolean isSubtree = isSameTree(root1, root2);

        boolean isLeftSubTree = HasSubtree(root1.left, root2);
        boolean isRightSubTree = HasSubtree(root1.right, root2);

        return isSubtree || isLeftSubTree || isRightSubTree;
    }

    /**
     * @see com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.一棵树是否包含另一颗树#isSubtreeHelper(TreeNode, TreeNode)
     * 这两道题类似，但是这道题是判断是否是子结构，而上面那道题是判断是否包含子树。
     * 子树是指一个节点下的所有子节点，而子结构是指一个节点下的部分子节点。
     * 区别在于isSameTree递归方法的退出条件。如果root2为空了，则表示上一层的递归中的root2已经匹配完了，则就是子结构。
     * 如果root1为空了，则表示root1已经匹配完了，但是root2还没有匹配完，则不是子结构。
     * @param root1
     * @param root2
     * @return
     */
    public boolean isSameTree(TreeNode root1,TreeNode root2) {
        //此处为root2 == null 是匹配完成的条件
        //最开始root2肯定不为NULL,这是在主程序HasSubtree中判断过的。
        //递归中，如果root2为空了，则表示上一层的递归中的root2已经匹配完了
        if(root2 == null) return true;
        if(root1 == null) return false;

        if(root1.val != root2.val) return false;

        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }
}
