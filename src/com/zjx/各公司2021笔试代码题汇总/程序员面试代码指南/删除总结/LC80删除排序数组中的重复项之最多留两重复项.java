package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 */
public class LC80删除排序数组中的重复项之最多留两重复项 {
    public int removeDuplicates(int[] nums) {
        int j = 1, count = 1;  //j指向可以覆盖的位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            //之前想的是复制最后两位，此解法是复制最开始的两位
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
