package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;


/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class LC26删除排序数组中的重复项之保留一项 {
    public int removeDuplicates(int[] nums) {
        //和删除排序数组中重复项(最多保留两项)的解法一样
        int j = 0, count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]){   //由于i要与i - 1作比较，所以直接从1开始，也说明nums[0]已经处理好,所以j
                count++;
            }else{
                count = 1;
            }
            if(count <= 1){ //如果最多保留两项的话，count<=2
                j++;
                nums[j] = nums[i];
            }
        }
        return j;
    }

    //和删除链表中重复的节点思路一样  遇到两处不同的值，就nums[++i] = nums[j]; 若相同的话就略过
    public int removeDuplicates1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int i = 0;  //i表示符合规则的区域的末尾，即pre   也可以指向1,表示可以覆盖的位置
        for (int j = 1; j < nums.length; j++) {  //j==0/1均可，等于0的情况下也会跳过if
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
