package com.zjx.codingInterviewGuide.拼多多;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 拼多多王国的城市和道路的拓扑结构比较特别，是一个树状结构：
 * 1. 每个城市是树的一个节点；
 * 2. 城市之间的道路是树的一条边；
 * 3. 树的根节点是首都。
 * 拼多多周年庆马上就要到了，这是拼多多王国的一个大日子。为了活跃气氛，国王想在道路上布置花灯。
 * 花灯可是很贵的东西，尽管国王想要在所有道路上都布置花灯，国王把这个计划告诉财政大臣，最后他们商讨出来这么一个方案：
 * 1. 一条道路要么不布置花灯，要么整条布置花灯，不能选择其中的某一段布置；
 * 2. 除非没有道路通向首都，否则至少为一条通向首都的道路布置花灯；
 * 3. 所有布置花灯的道路构成的子图是连通的，这保证国王从首都出发，能通过只走布置了花灯的道路，把所有的花灯游览完；
 * 4. 如果某个城市（包括首都）有大于等于2条道路通向子城市，为了防止铺张浪费，最多只能选择其中的两条路布置花灯；
 * 5. 布置花灯的道路的总长度设定一个上限。
 * 在上述方案下，国王想要使得布置花灯的道路长度越长越好，你帮国王想想办法。
 * 输入描述:
 * 每个测试输入包含1个测试用例。
 * 输入的第一行是一个正整数m，0<m<=9900，表示布置花灯的道路的总长度的上限。
 * 输入的第二行是一个正整数n，n<=100，表示城市的个数。
 * 紧接着是n-1行输入，每行三个正整数u、v、d，表示下标为u的城市有一条长度为d的道路通向它的一个子城市v，
 * 其中0<=u<n，0<=v<n，0<d<=100。
 */
public class PDD2018Test花灯 {
    static class TreeNode {
        int val;
        int parent = -1;
        //把子节点和权值分开存储
        ArrayList<TreeNode> child = new ArrayList<>();
        ArrayList<Integer> dist = new ArrayList<>();
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int limit = scanner.nextInt();
        int n = scanner.nextInt();
        TreeNode[] treeNodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            treeNodes[i] = new TreeNode(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();
            int dist = scanner.nextInt();

            treeNodes[parent].child.add(treeNodes[child]);
            treeNodes[child].parent = parent;
            treeNodes[parent].dist.add(dist);
        }
        //找到根节点
        TreeNode root = null;
        for (int i = 0; i < n; i++) {
            if (treeNodes[i].parent == -1) {
                root = treeNodes[i];
                break;
            }
        }

        HashSet<Integer> set = getPath(root, limit);
        int max = Integer.MIN_VALUE;
        for (int len : set) {
            max = Math.max(max, len);
        }
        System.out.println(max);
    }

    private static HashSet<Integer> getPath(TreeNode root, int limit) { //limit不变
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        if (root == null) {
            return set;
        }
        //保持连通是通过从子树往上递归
        ArrayList<HashSet<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < root.child.size(); i++) {
            HashSet<Integer> childSet = getPath(root.child.get(i), limit); //以第一个参数为根节点的处理方法
            arr.add(childSet);
        }

        // one path
        for (int i = 0; i < arr.size(); i++) {
            int d = root.dist.get(i);
            //最少选一条路
            for (int path : arr.get(i)) {
                if (path + d <= limit) set.add(path + d);
            }
            //最多选两条
            for (int j = i + 1; j < arr.size(); j++) {
                int d2 = root.dist.get(j);
                for (int path1 : arr.get(i)) {
                    for (int path2 : arr.get(j)) {
                        if (path1 + path2 + d + d2 <= limit) set.add(path1 + path2 + d + d2);
                    }
                }
            }
        }
        return set;
    }
}
