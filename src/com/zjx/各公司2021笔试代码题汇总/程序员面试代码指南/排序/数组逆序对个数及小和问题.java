package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.排序;

/**
 * 通过归并排序解决一些问题,如:
 * 1.数组的逆序问题:
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * 输入: [7,5,6,4]
 * 输出: 5
 * 2.数组的小和：
 * 数组小和的定义如下：
 * 例如，数组s = [1, 3, 5, 2, 4, 6]，在s[0]的左边小于或等于s[0]的数的和为0；
 * 在s[1]的左边小于或等于s[1]的数的和为1；在s[2]的左边小于或等于s[2]的数的和为1+3=4；
 * 在s[3]的左边小于或等于s[3]的数的和为1；在s[4]的左边小于或等于s[4]的数的和为1+3+2=6；
 * 在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15。所以s的小和为0+1+4+1+6+15=27
 * 给定一个数组s，实现函数返回s的小和
 * [要求]
 * 时间复杂度为O(nlogn)，空间复杂度为O(n)
 */
public class 数组逆序对个数及小和问题 {
    public static void main(String[] args) {
//        System.out.println(reversePairs(new int[]{7,5,6,4}));
        System.out.println(reversePairs(new int[]{1, 3, 5, 2, 4, 6}));
    }
    public static int reversePairs(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
//        int[] copy = new int[nums.length];
//        for(int i = 0; i < nums.length; i++){
//            copy[i] = nums[i];
//        }
        return mergeSort(nums,0,nums.length - 1);
    }

    private static int mergeSort(int[] arr, int low, int high){
        if(low == high) return 0;  //>=也可

        int mid = (low + high) / 2;
        int leftReturn = mergeSort(arr,low,mid);
        int rightReturn = mergeSort(arr,mid + 1, high);
//        if(arr[mid] <= arr[mid + 1]){
//            return leftReturn + rightReturn;
//        }
        return leftReturn + rightReturn + merge(arr, low, mid, high);
    }

    private static int merge(int[] arr, int left, int mid, int right){
        //注意，这里使用的是刚开始传过来的tmp数组，长度已经固定，因为arr已经重新排过序，这里需要重新拷贝
        int[] tmp = new int[arr.length];
        for(int i = left; i <= right; i++){
            tmp[i] = arr[i];
        }
        int ls = left;
        int rs = mid + 1;
        int index = left;
        int cnt = 0;
        while(ls <= mid && rs <= right){
            if(tmp[ls] <= tmp[rs]){
                //小和在这里处理
                cnt += tmp[ls] * (right - rs + 1);
                arr[index++] = tmp[ls++];
            }else{
                //计算逆序对和计算小和不一样，逆序对是在else中，且统计mid~ls；
                //而小和是统计rs~high，这样统计时在两边最后剩余时就不用做额外处理
//                cnt += mid - ls + 1;
                arr[index++] = tmp[rs++];
            }
        }
        //不管是小和还是逆序，这里剩下的都不用处理，只是单纯地还原数组
        while(ls <= mid){
            arr[index++] = tmp[ls++];
        }
        return cnt;
    }
}
