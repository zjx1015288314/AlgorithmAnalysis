package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，不保留重复元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 给定 nums = [0,0,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 1,4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class 删除排序数组中所有重复项之不留重复项 {

    public static void main(String[] args) {
        int[] num = {0,0,1,1,1,2,2,3,3,4};
        int len = removeAllDuplicates(num);
        System.out.println("len: " + len);
        int[] res = Arrays.copyOfRange(num, 0, len);
        for (int i : res) {
            System.out.println(i);
        }
    }

    public static int removeAllDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int i = -1;  //i表示符合规则的区域的末尾，即pre   也可以指向1,表示可以覆盖的位置
        int j = 0;
        while(j < nums.length) {  // j==0/1均可，等于0的情况下也会跳过if
            if (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                while(j < nums.length - 1 && nums[j] == nums[j + 1]) {
                    j++;
                }
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;
    }
}
