package com.zjx.各公司2021笔试代码题汇总;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 旋转数组的最小值 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numLen = Integer.valueOf(bf.readLine());
        String[] ss = bf.readLine().split(" ");
        int[] nums= new int[numLen];
        for(int i= 0; i< numLen; i++){
            nums[i] = Integer.valueOf(ss[i]);
        }

        //前两种容易想到，但都是将arr[mid]和arr[high]/arr[start]比较，
        //第三种同时和头尾比较，而且在arr[low]=arr[mid]=arr[high]时，尽量使用二分提速
        System.out.println(getMin(nums));
        //System.out.println(getMin2(nums));
        //System.out.println(getMin3(nums));  //推荐
    }

    public static int getMin(int[] arr){
        if(arr == null || arr.length == 0) return 0;
//        if(arr[0] < arr[arr.length - 1]) return arr[0];
        int low = 0;
        int high = arr.length - 1;
        while (low < high){
            int mid = low + ((high - low) >> 1);
            //if(arr[low] < arr[high]) return arr[low]; //一定要加这句，不然只能过90%
            if(arr[mid] == arr[high]){
                high--;
            } else if(arr[mid] > arr[high]){
                low = mid + 1;
            } else {
                //当mid处在左区间时，不能直接high = mid ，必须high = high - 1,因为array[mid]
                //与array[high]相等，不用考虑array[high]
                high = mid - 1;
            }
        }
        return arr[low];
    }

    public static int getMin2(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int start = 0, end = arr.length-1;
        int res = Integer.MAX_VALUE;
        while(start <= end){  //二分法的经典循环条件
            int mid = start + (end - start)/2;
            //思想：将数组旋转后只数组会分成左右两个有序区间，所以在nums[mid]!= target后只有三种情况：
            //nums[mid] == nums[start],这种情况可能是发生在左区间比右区间大，且某一元素有很多重复值；
            //或者右区间比左区间大，例如：12333333345 -> 34512333333
            //nums[mid] > nums[start]
            //nums[mid] < nums[start]
            if(arr[mid] == arr[start]){
                res = Math.min(res,arr[mid]);
                start++;  //这里的意思是 target != (nums[mid]/nums[start])
            }else if(arr[mid] > arr[start]){ //说明mid在左边连续区间
                //这里不用考虑target=nums[mid],因为第一个if已经排除了这种情况
                res = Math.min(res,arr[start]);
                start = mid+1;
            }else{   //说明mid在右边连续区间
                end = mid;
            }
        }
        return res;
    }

    public static int getMin3(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int low = 0, high = arr.length - 1;
        while(low < high){  //二分法的经典循环条件
            int mid = low + (high - low)/2;
            //思想：将数组旋转后只数组会分成左右两个有序区间，所以在nums[mid]!= target后只有三种情况：
            //nums[mid] == nums[start],这种情况可能是发生在左区间比右区间大，且某一元素有很多重复值；
            //或者右区间比左区间大，例如：12333333345 -> 34512333333
            if(arr[low] < arr[high]) return arr[low];
            if(arr[low] > arr[mid]){
                high = mid;
                continue;
            }
            if(arr[mid] > arr[high]){
                low = high;
                continue;
            }
            while(low < mid){
                if(arr[low] == arr[mid]){
                    low++;
                } else if(arr[low] < arr[mid]){
                    return arr[low];
                }else{
                    high = mid;
                    break;
                }
            }
        }
        return Math.min(arr[low],arr[high]);
    }
}
