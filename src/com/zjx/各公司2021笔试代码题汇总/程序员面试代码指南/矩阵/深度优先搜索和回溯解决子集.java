package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 牛客链接：https://www.nowcoder.com/practice/c333d551eb6243e0b4d92e37a06fbfc9?tpId=117&&tqId=34948&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
 * 和深度优先搜索和回溯解决IP地址划分类似
 * 现在有一个没有重复元素的整数集合S，求S的所有子集
 * 注意：
 * 你给出的子集中的元素必须按升序排列
 * 给出的解集中不能出现重复的元素
 * 例如：
 * 如果S = [1,2,3], 给出的解集应为：
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class 深度优先搜索和回溯解决子集 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = bf.readLine().split(" ");
        int N = Integer.valueOf(ss[0]);

        int[] arr = new int[N];
        ss = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }
        List<List<Integer>> res = subsets(arr);
        for (List<Integer> list : res){
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }



    public static List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> res = new ArrayList<>();
        if(S == null || S.length == 0) return res;
        Arrays.sort(S); // 保证S升序
        dfs(S,0, new ArrayList<>(), res);
        //如果结果需要升序排列, 则需要加入以下排序代码
        res.sort((o1, o2) -> {
            if (o1.size() != o2.size()) {
                return o1.size() - o2.size();
            }
            return 0;
        });
        return res;
    }

    public static void dfs(int[] s, int start, List<Integer> list, List<List<Integer>> res){
        if(start == s.length){
            res.add(new ArrayList<>(list));
            return;
        }
        //每个位置都有选和不选两种决策
        dfs(s,start + 1, list, res);  //不选

        list.add(s[start]);
        dfs(s,start + 1, list, res);
        list.remove(list.size()-1);
    }
    //错误做法
//    public static void dfs1(int[] s, int start, ArrayList<int[]> list){
//        if(start == s.length){
//            for(int[] tmp : list){
//                ArrayList<Integer> l = new ArrayList<>();
//                for(int i : tmp){
//                    l.add(i);
//                }
//                res.add(l);
//            }
//        }
//
//        for(int i = start; i < s.length; i++){
//            list.add(Arrays.copyOfRange(s,start,i + 1));
//            dfs(s,i + 1,list);
//            list.remove(list.size() - 1);
//        }
//    }
}
