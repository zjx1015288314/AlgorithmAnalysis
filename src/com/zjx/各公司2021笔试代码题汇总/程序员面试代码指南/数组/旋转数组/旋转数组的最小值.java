package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.旋转数组;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/24
 * @since 1.0.0
 */
public class 旋转数组的最小值 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numLen = Integer.valueOf(bf.readLine());
        String[] ss = bf.readLine().split(" ");
        int[] nums= new int[numLen];
        for(int i= 0; i< numLen; i++){
            nums[i] = Integer.valueOf(ss[i]);
        }
        getMinNumberInRotateArray1(nums);
        getMinNumberInRotateArray2(nums);
    }

    private static void getMinNumberInRotateArray1(int[] nums) {
        int start = 0, end = nums.length - 1;
        int res = Integer.MAX_VALUE;
        while(start <= end){  //二分法的经典循环条件
            int mid = start + (end - start)/2;

            //思想：将数组旋转后此数组会分成左右两个有序区间，所以在nums[mid]!= target后只有三种情况：
            //nums[mid] == nums[start],这种情况可能的情况是：
            //  1.左区间比右区间大，且某一元素有很多重复值； 12222222222223 -> 22222222222312
            //  2.右区间比左区间大，例如：12333333345 -> 34512333333
            //  3.区间长度小于等于2， 如 [2 1] 或者 [1]
            //nums[mid] > nums[start]
            //nums[mid] < nums[start]

            //此步非常重要，mid的值可能就是最小值
            res = Math.min(res, nums[mid]);
            if(nums[mid] == nums[start]){
                //这里的意思是除去两个重复值中的一个不影响结果,
                //如果最小值是start,那么我们最后依然可以锁定mid得到最小值
                start++;
            }else if(nums[mid] > nums[start]){ //说明mid在左边连续区间
                res = Math.min(res, nums[start]);
                //这里不用考虑target=nums[mid],因为第一个if已经排除了这种情况
                start = mid + 1;
            }else{   //说明mid在右边连续区间
                end = mid - 1;
            }
        }
        System.out.println(res);
    }

    public static int getMinNumberInRotateArray2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int i = 0, j = array.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if(array[i] < array[j]){  //当缩小区间时，旋转数组可能变为有序数组，所以直接排除此种情况
                System.out.println(array[i]);
                return array[i];
            }else if (array[m] == array[i]) {
                i++;
            } else if (array[i] > array[m]) {
                j = m;    //arrar[m]可能是最小值,不可以略过
            } else {
                i = m + 1;
            }
        }
        System.out.println(array[i]);
        return array[i];
    }

    public int compare (String version1, String version2) {
        // write code here
        String[] versionArr1 = version1.split("\\.");
        String[] versionArr2 = version2.split(".");
        int res = 0;
        for (int i = 0, j = 0; i < versionArr1.length || j < versionArr2.length; i++, j++) {
            String v1Str = i < versionArr1.length ? versionArr1[i] : "0";
            String v2Str = j < versionArr2.length ? versionArr2[j] : "0";
            int v1 = Integer.parseInt(v1Str);
            int v2 = Integer.parseInt(v2Str);
            if (v1 != v2) {
                res = v1 > v2 ? 1 : -1;
                break;
            }
        }
        return res;
    }
}
