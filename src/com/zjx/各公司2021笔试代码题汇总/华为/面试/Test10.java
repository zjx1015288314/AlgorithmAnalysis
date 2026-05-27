package com.zjx.各公司2021笔试代码题汇总.华为.面试;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入:
 * [[1, 3], [2], [-1], [3], [4]]
 * 输出:
 * boolean true/false
 */
public class Test10 {

    public static void main(String[] args) {
        int[][] threads = {{1}, {0}};

        int[][] threads1 = {{}, {-1}, {-1}}; // 1,2,0

        int[][] threads2 = {{1, 2}, {2}, {0}};
        int[][] threads3 = {{1}, {2}, {0}}; //
        System.out.println(hasThreadDeadLock(threads3));
    }

    private static boolean hasThreadDeadLock(int[][] threads) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < threads.length; i++) {
            int[] thread = threads[i];
            for (int threadIdx : thread) {
                if (map.containsKey(threadIdx)) {
                    List<Integer> waitThreads = map.get(threadIdx);
                    if (waitThreads.contains(i)) {
                        return true;
                    }
                }
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }
                List<Integer> list = map.get(i);
                list.add(threadIdx);
            }
        }
        return false;
    }



}
