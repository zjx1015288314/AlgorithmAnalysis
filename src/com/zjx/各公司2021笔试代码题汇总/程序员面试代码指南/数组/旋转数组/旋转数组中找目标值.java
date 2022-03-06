package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.旋转数组;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 有序数组arr可能经过一次旋转处理，也可能没有，且arr可能存在重复的数。例如，有序数组[1, 2, 3, 4, 5, 6, 7]，
 * 可以旋转处理成[4, 5, 6, 7, 1, 2, 3]等。给定一个可能旋转过的有序数组arr，再给定一个数num，返回arr中是否含有num
 * 关于旋转操作：可以简单的理解为把序列从某个位置切成两段然后交换位置
 * [要求]
 * 期望复杂度为O(logn)
 */
public class 旋转数组中找目标值 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = bf.readLine().split(" ");
        int numLen = Integer.valueOf(ss[0]);
        int target = Integer.valueOf(ss[1]);
        ss = bf.readLine().split(" ");
        int[] nums= new int[numLen];
        for(int i = 0; i < numLen; i++){
            nums[i] = Integer.valueOf(ss[i]);
        }
//        System.out.println(isContains(nums,target) ? "Yes" : "No");
        System.out.println(isContains2(nums,target) ? "Yes" : "No");  //易想到
        //System.out.println(isContains3(nums,target) ? "Yes" : "No");
    }

    /**
     * 解法与旋转数组找最小值一样，都是根据mid分区间，然后再决定target在哪个区间
     * @param arr
     * @param target
     * @return
     */
    public static boolean isContains(int[] arr,int target){
        if(arr == null || arr.length == 0) return false;
        int low = 0;
        int high = arr.length - 1;
        while (low < high){
            int mid = low + ((high - low) >> 1);
            //if(arr[low] < arr[high]) return arr[low]; //一定要加这句，不然只能过90%
            if(arr[mid] == target){
                return true;
            }

            if(arr[mid] == arr[high]){
                high--;
            } else if(arr[mid] > arr[high]){
                if(target >= arr[low] && target < arr[mid]){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            } else {
                if(target > arr[mid] && target <= arr[high]){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
        }
        return arr[low] == target;
    }

    //题目与在有序数组中找到最小值中解法二对应，改动的地方有最后两个判断，将区间分为一段有序的区间和另一段，
    //有序区间由mid和start/end组成，其余部分自成另一区间
    private static boolean isContains2(int[] arr, int target){
        if(arr == null || arr.length == 0 || target < 1) return false;
        int start = 0, end = arr.length - 1;
        while(start <= end){  //二分法的经典循环条件
            int mid = start + (end - start)/2;
            //思想：将数组旋转后只数组会分成左右两个有序区间，所以在nums[mid]!= target后只有三种情况：
            //nums[mid] == nums[start],这种情况可能是发生在左区间比右区间大，且某一元素有很多重复值；
            //或者右区间比左区间大，例如：12333333345 -> 34512333333
            //nums[mid] > nums[start]
            //nums[mid] < nums[start]
            if(arr[mid] == target) {
                return true;
            }

            if(arr[mid] == arr[start]){
                start++;  //这里的意思是 target != (nums[mid]/nums[start])
            }else if(arr[mid] > arr[start]){ //说明mid在左边连续区间
                // 边界值需要细推，这里target可能在三个区间，从左往右为：[start, mid), (mid, 分界点), (分界点, end]
                // 由于第一段区间最好确定(分界点不好找), 所以先处理第一段,其余放到else里
                if(target >= arr[start] && target < arr[mid]){
                    end = mid-1;
                }else{
                    start = mid + 1;
                }
            }else{
                if(target > arr[mid] && target <= arr[end]){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }
        }
        return false;
    }

    //题目与在有序数组中找到最小值中解法三对应，将arr[low]=arr[mid]=arr[high]的情况化解为一般情况
    private static boolean isContains3(int[] arr, int target){
        if(arr == null || arr.length == 0) return false;
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) return true;
            if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                while(low != mid && arr[low] == arr[mid]){
                    low++;
                }
                if(low == mid){
                    low++;
                    continue;
                }
            }
            //arr[low] arr[mid] arr[high] 不都相等
            if(arr[low] != arr[mid]){
                if(arr[low] < arr[mid]){
                    if(arr[low] <= target && target < arr[mid]){
                        high = mid - 1;
                    }else{
                        low = mid + 1;
                    }
                }else{
                    if(arr[mid] < target && target <= arr[high]){
                        low = mid + 1;
                    }else{
                        high = mid - 1;
                    }
                }
            }else{
                if(arr[mid] < arr[high]){
                    if(arr[mid] < target && target <= arr[high]){
                        low = mid + 1;
                    }else{
                        high = mid - 1;
                    }
                }else{
                    if(arr[low] <= target && target < arr[mid]){
                        high = mid - 1;
                    }else{
                        low = mid + 1;
                    }
                }
            }
        }
        return false;
    }
}
