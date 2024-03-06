package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

import java.util.*;

/**
 * 给出一组可能包含重复项的数字，返回该组数字的所有排列。结果以字典序升序排列。
 * 数据范围：0<n≤8 ，数组中的值满足−1≤val≤5
 * 要求：空间复杂度O(n!)，时间复杂度O(n!)
 *
 * 输入：[1,1,2]   返回值：[[1,1,2],[1,2,1],[2,1,1]]
 * 输入：[0,1]     返回值：[[0,1],[1,0]]
 */
public class 有重复数字的全排序 {

    private boolean[] visited;
    private final Set<String> pathSet = new HashSet<>();
    private final ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> permuteUnique (int[] num) {
        // write code here
        if (num == null || num.length == 0) {
            return res;
        }
        LinkedList<Integer> path = new LinkedList<>();
        visited = new boolean[num.length];
        // 可以先将数据排序， 再全排序 这样就不需要res.sort((o1,o2) -> toStr(o1).compareTo(toStr(o2)));
        // Arrays.sort(num);
        backTrack(num, path);
        res.sort((o1,o2) -> toStr(o1).compareTo(toStr(o2)));
        return res;
    }

    private void backTrack(int[] num, LinkedList<Integer> path) {
        if (path.size() == num.length) {
            String pathStr = toStr(path);
            if (!pathSet.contains(pathStr)) {
                pathSet.add(pathStr);
                res.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.add(num[i]);

                backTrack(num, path);
                visited[i] = false;
                path.removeLast();
            }
        }
    }

    private String toStr(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
