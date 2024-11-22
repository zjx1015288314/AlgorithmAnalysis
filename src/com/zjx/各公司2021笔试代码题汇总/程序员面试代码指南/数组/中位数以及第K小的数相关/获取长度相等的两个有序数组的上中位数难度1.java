package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定两个递增数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。
 * 上中位数：假设递增序列长度为n，为第n/2个数
 *
 * 上下中位数的思路在于二分法，每次去掉不可能的一半，最后剩下的两个数中的较大值就是上中位数
 * 1. 如果两个数组长度是偶数:
 *  [1, 4, 5, 7]与[2, 3, 4, 5]比较, 当中位数比较4, 3比较时, 4比3大, 那么3及3之前的数都不可能是上中位数,
 *  4之后的数(注意4可能是中位数)都不可能是上中位数, 所以第一次比较完后, 剩下的数是[1, 4]与[4, 5]比较
 * 2. 如果两个数组长度是奇数:
 *  [1, 4, 5]与[2, 3, 4]比较, 当中位数比较4, 3比较时, 4比3大, 4及4之后的数都不可能是上中位数,
 *  3(注意3可能是上中位树，不能舍弃)之前的数都不可能是上中位数,所以第一次比较完后, 剩下的数是[1, 4]与[3, 4]比较
 * 总结一下就是，偶数长度的数组比较时, 舍弃策略更加严格, 舍弃的数更多, 奇数长度的数组比较时, 舍弃策略更加宽松, 舍弃的数更少
 *
 * 数据范围：
 * 1≤n≤10^5，0≤arr1,arr2≤10^9
 * 要求：时间复杂度O(n)，空间复杂度O(1)
 * 进阶：时间复杂度为O(logN)，空间复杂度为O(1)
 * https://www.nowcoder.com/practice/6fbe70f3a51d44fa9395cfc49694404f?tpId=196&tqId=37141&ru=/exam/oj
 */
public class 获取长度相等的两个有序数组的上中位数难度1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        String[] ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr1[i] = Integer.parseInt(ss[i]);
        }
        ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr2[i] = Integer.parseInt(ss[i]);
        }
        System.out.print(getLeftMedian(arr1,arr2));  //O(N)
        System.out.print(getLeftMedian2(arr1,arr2)); //O(N)
        System.out.print(getLeftMedian3(arr1,arr2));  //O(logN)

        //思考：如果要找下中位数，三种方法又该怎么做？
    }


    /**
     * 利用数组的有序性，联想到二分法 O(logN)
     * @param arr1
     * @param arr2
     * @return
     */
    public static int getLeftMedian3(int[] arr1, int[] arr2){
        if(arr1 == null || arr2 == null || arr1.length != arr2.length){
            throw new RuntimeException("Input arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;
        // ！！！条件不是等于，数组只有一位时可直接找到。如果这里包括等于，可能会数组越界
        while(start1 < end1){
            int mid1 = (start1 + end1) / 2;
            int mid2 = (start2 + end2) / 2;
            int offset = ((end1 - start1 + 1) & 1) ^ 1;
            if(arr1[mid1] > arr2[mid2]){
                end1 = mid1;
                start2 = mid2 + offset;
            }else if(arr1[mid1] < arr2[mid2]){
                start1 = mid1 + offset;
                end2 = mid2;
            }else{
                //！！！ 注意这里，如果两个数组中间数相等，直接返回即可
                return arr1[mid1];
            }
        }
        // 这里是关键，最后还是要比较以下
        return Math.min(arr1[start1],arr2[start2]);
    }

    //该方法是getLeftMedian的改进，上中位数即第k小的数，因为在两个数组里，我们在确定第k小的时候，需要
    //第k和第k+1个数，才能决定谁能成为第k小；而要比较第k小，必须要走k-1步(某一数组单独走或者两个数组一起走的总和)
    //在走完k-1步后，比较两数组当前位置的大小
    private static int getLeftMedian2(int[] arr1,int[] arr2){
        if(arr1 == null || arr2 == null || arr1.length != arr2.length){
            throw new RuntimeException("Input arr is invalid!");
        }

        int len1 = arr1.length;
        int len2 = arr2.length;

        int target = len1;   //走k步
        int i = 0, j = 0;
        while(i < len1 && j < len2){
            //先判断步数是否用完，用完直接返回，没用完减1
            target--;
            if (target == 0) return Math.min(arr1[i], arr2[j]);
            if(arr1[i] <= arr2[j]){
                i++;
            }else {
                j++;
            }
        }
        return 0;
    }

    /**
     * O(N) 简单易懂
     * @param arr1
     * @param arr2
     * @return
     */
    private static int getLeftMedian(int[] arr1,int[] arr2){
        if(arr1 == null || arr2 == null || arr1.length != arr2.length){
            throw new RuntimeException("Input arr is invalid!");
        }

        int len1 = arr1.length;
        int len2 = arr2.length;

        int target = len1;   //代表从开始到找到目标需要走的步数
        int i = 0, j = 0;
        //因为是上中位数，所以一定不会超过其中一个数组的长度，这里的循环判断条件也就是有效的
        while(i < len1 && j < len2){
            target--;
            if(arr1[i] <= arr2[j]){
                if(target == 0){
                    return arr1[i];
                }
                i++;
            }else if(arr1[i] > arr2[j]){
                if(target == 0){
                    return arr2[j];
                }
                j++;
            }
        }
        return 0;
    }
}
