package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.最长子序列;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * @ref{最长递增子序列_困难},两者的区别是最长递增子序列_困难在原数组的子序列中找最长递增(元素s顺序虽然不要求连续，但相对顺序不能变
 * 且元素递增即可，不一定要连续)
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 */
public class 最长连续序列_困难 {
    /**
     * Arrays.sort(nums)使用快排O(nlogn)
     */
    public int longestConsecutive1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int maxLength = 1;
        int currentLength = 1;
        Arrays.sort(nums);
        for(int end = 1; end < nums.length; end++){
            if(nums[end] > nums[end - 1] + 1){
                currentLength = 1;
            }else if(nums[end] == nums[end - 1] + 1){
                currentLength++;
            }
            //nums[end] == nums[end - 1]即重复的情况不做考虑
            maxLength = Math.max(maxLength,currentLength);
        }
        return maxLength;
    }

    /**
     * 方法二：优化了方法一的时间复杂度为O(N)
     * 使用 hashmap 来保存数组中已经遍历过的元素，key对应元素的值，value表示该元素所在的
     * 连续子数组的长度。当遍历到数组的一个元素时有以下四种情况：
     *    1.如果hashmap中存在此元素，则遍历下一个元素。
     *    2.如果hashmap中不存在元素，则看hashmap中是否存在此元素的前一个元素，
     *      比如说如果遍历到5时，看看hashmap中是否存在 4，如果存在则取该连续子数组的第一个元素，
     *      将它value值+1，并将该元素放到hashmap中，value值表示该连续子数组的长度。
     *    3.如果hashmap中存在的该元素的后一个元素，遍历到5时，hashmap中是否存在 6，
     *      将次元素加入到后一个连续的子数组中，并且和2中一样，找到子数组的第一个元素和最后一个元素，
     *      将它们的value值更新为子数组的长度。
     */
    public int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int longest = 1;
        Map<Integer, Integer> map = new HashMap<>();  //key所在连续序列的长度value ，key可能为序列头，也可能为序列尾
        for(int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], 1);

                if(map.containsKey(nums[i] - 1)) {
                    longest = Math.max(longest, merge(map, nums[i] - 1, nums[i]));
                }

                if(map.containsKey(nums[i] + 1)) {
                    longest = Math.max(longest, merge(map, nums[i], nums[i] + 1));
                }
            }
        }
        return longest;
    }

    //合并连续数组，并且更新数组的第一个元素和最后一个元素的value值为合并后的数组的长度。
    private int merge(Map<Integer, Integer> map, int less, int more) {
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int len = right - left + 1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    /**
     * 通过并查集，将前后都存在的两个值nums[i]， nums[i]+1的索引通过并查集联合成一个大集合，集合的头部是序列
     * 的尾(即序列中最大的元素的索引,unionfind.union(i, index)),最后执行一遍nums[unionfind.find(i)]-nums[i]即可
     */
    public int longestConsecutive3(int[] nums) {
        int len = nums.length,res = 0;
        if(len == 0) return res;

        UnionFind unionfind = new UnionFind(len);
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            map.put(nums[i], i);
        }
        for(int i = 0; i < len; i++){
            if(map.containsKey(nums[i] + 1)){
                int index = map.get(nums[i] + 1);
                //小的索引并向大的,union会压缩头部，可能会增加复杂度，必要时用count[]代替
                unionfind.union(i, index);
            }
        }
        for(int i = 0; i < len; i++){
            res = Math.max(res,nums[unionfind.find(i)]-nums[i]);
        }
        return res + 1;
    }

    /**
     * 并查集类定义，@ref {com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南
     * .并查集.左神并查集实现}相似
     */
    class UnionFind{
        int[] parent;   //每个并查集必须要有
        int[] count;   //该题特有，count[i]表示i所在的集合中有多少元素


        public UnionFind(int n){
            parent = new int[n];
//            count = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
//            Arrays.fill(count, 1);
        }

        public void union(int i,int j){
            int parentI = find(i);
            int parentJ = find(j);
            if(parentI == parentJ) return;
            parent[parentI] = parentJ;
        }

        public boolean isConnected(int i,int j){
            return find(i) == find(j);
        }

        public int find(int i){
            while(parent[i] != i){
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return parent[i];
        }
    }
}
