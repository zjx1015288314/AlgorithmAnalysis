package com.zjx.各公司2021笔试代码题汇总.华为.面试;

import java.io.IOException;
import java.util.Arrays;

public class Test1 {
    //{3 ,  1 ,  6 ,  26 , 10}      18
    //输出 17
    public static void main(String[] args) throws IOException {
        int[] nums = {3 ,  1 ,  6 ,  26 , 10};
        int target = 18;
        System.out.println(threeNumSum(nums, target));
    }

    private static int threeNumSum(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new RuntimeException("nums length less than 3");
        }

        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int curNum = nums[i] + nums[j] + nums[k];
                if (curNum == target) {
                    return curNum;
                }
                if (Math.abs(curNum - target) < Math.abs(res - target)) {
                    res = curNum;
                }
                if (curNum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }

}
