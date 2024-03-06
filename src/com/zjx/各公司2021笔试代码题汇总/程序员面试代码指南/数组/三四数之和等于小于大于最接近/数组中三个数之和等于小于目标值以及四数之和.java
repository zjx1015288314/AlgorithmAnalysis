package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.三四数之和等于小于大于最接近;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * https://leetcode.cn/problems/3sum/description/
 * 注1: 这里要求 i!=j!=k，即同一个数不能选三次
 * 注2：返回的结果中不能有重复的三元组，即nums[i], nums[j], nums[k] 不能重复出现
 * 思路: 排序+三指针
 */
public class 数组中三个数之和等于小于目标值以及四数之和 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int target = Integer.parseInt(str[1]);  //方法二参数
        int[] arr = new int[N];
        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        ArrayList<ArrayList<Integer>> res;
        res = threeSum(arr);
        for (ArrayList<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        threeSum(arr,target);
    }

    /**
     * 排序+三指针，排序防止重复组合
     * @param num
     * @return
     */
    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
             if (i > 0 && num[i] == num[i - 1]) {
                 continue;
             }
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (num[i] + num[j] + num[k] == 0) {
                    ArrayList<Integer> list = new ArrayList< >();
                    list.add(num[i]);
                    list.add(num[j]);
                    list.add(num[k]);
                    result.add(list);
                    j++;
                    k--;
                    while (j < k && num[j] == num[j - 1]) {
                        j++;
                    }
                    while (j < k && num[k] == num[k + 1]) {
                        k--;
                    }
                } else if (num[i] + num[j] + num[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

    /**
     * 三数之和小于Target的组合总数目
     * LintCode: https://www.lintcode.com/problem/3sum-smaller/description
     * 题目描述：给定一个n个整数的数组和一个目标整数target，找到下标为i、j、k的
     * 数组元素0 <= i < j < k < n，满足条件nums[i] + nums[j] + nums[k] < target。
     * 输出满足上述条件的组合的总数。
     * @param num
     * @param target
     * @return
     */
    public static int threeSum(int[] num,int target) {
        Arrays.sort(num);
        //统计重复数字
        int[] dump = getDumpArr(num);
        int cnt = 0;
        for (int i = 0; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = num.length - 1;
            //确定首位数字后，双指针遍历
            while(j < k){
                //当和小于target时：保持当前left不变，right指针从此处前移到left+1，所有的和都将小于target
                //所以此时满足条件的情况的数目等于 right - (left + 1) + 1 = right - left
                if(num[i] + num[j] + num[k] < target){
                    cnt += k - j - dump[k] + 1;
                    j++;
                    while (j < k && num[j] == num[j - 1]) j++;
                }else{
                    k--;
                }
            }
        }
        System.out.println(cnt);
        return cnt;
    }

    /**
     * 统计重复次数
     * @param num
     * @return
     */
    private static int[] getDumpArr(int[] num){
        if(num == null || num.length == 0)  return new int[]{};
        Map<Integer,Integer> map = new HashMap<>();
        int[] res = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            if (!map.containsKey(num[i])){
                map.put(num[i],1);
            }else{
                map.put(num[i],map.get(num[i]) + 1);
            }
        }
        for (int i = 0; i < num.length; i++) {
            res[i] = map.get(num[i]);
        }
        return res;
    }

    /**
     * LintCode:https://www.lintcode.com/problem/4sum/description
     *
     * 题目描述：给一个包含n个数的整数数组S，在S中找到所有使得和为给定
     * 整数tar get的四元组(a, b, c, d)。四元组(a, b, c, d)中，
     * 需要满足a <= b <= c <= d。答案中不可以包含重复的四元组。
     * @param numbers
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(numbers == null || numbers.length == 0)
            return res;
        Arrays.sort(numbers);
        //a元素遍历
        for(int i = 0; i < numbers.length - 3; i++){
            //a元素去重
            if(i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            //b元素遍历
            for(int j = i + 1; j < numbers.length - 2; j++){
                //b元素去重
                if(j > i + 1 && numbers[j] == numbers[j - 1]) {
                    continue;
                }

                //双指针
                int left = j + 1;
                int right  = numbers.length - 1;
                while(left < right){
                    if(numbers[left] + numbers[right] + numbers[i] + numbers[j] == target){
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(numbers[i]);
                        tmp.add(numbers[j]);
                        tmp.add(numbers[left]);
                        tmp.add(numbers[right]);
                        res.add(tmp);
                        left++;
                        right--;
                        //第三个元素去重
                        while(left < right && numbers[left] == numbers[left - 1])
                            left++;
                        //第四个元素去重
                        while(left < right && numbers[right] == numbers[right + 1])
                            right--;
                    }else if(numbers[left] + numbers[right] + numbers[i] + numbers[j] < target) {
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
