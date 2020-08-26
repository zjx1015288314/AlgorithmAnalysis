package com.zjx.codingInterviewGuide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 寻找两个数组的下中位数
 */
public class GetDownMedian {
    public static void main(String[] args) throws IOException {
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

    }

    //利用数组的有序性，联想到二分法 O(logN)
    public static int getLeftMedian3(int[] arr1, int[] arr2){
        if(arr1 == null || arr2 == null || arr1.length != arr2.length){
            throw new RuntimeException("Input arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while(start1 < end1){
            mid1 = (start1 + end1) / 2 + 1;                     //修改1
            mid2 = (start2 + end2) / 2 + 1;                     //修改1
            //和上中位不同，这里数组长度为偶数的时候，offset为-1
            offset = ((end1 - start1 + 1) & 1) == 0 ? -1 : 0;   //修改2
            if(arr1[mid1] > arr2[mid2]){
                end1 = mid1 + offset;                           //修改3
                start2 = mid2;                                  //修改3
            }else if(arr1[mid1] < arr2[mid2]){
                start1 = mid1;                                  //修改3
                end2 = mid2 + offset;                           //修改3
            }else{
                return arr1[mid1];
            }
        }
        return Math.max(arr1[start1],arr2[start2]);             //修改4
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
            if (target-- == 0) return arr1[i] <= arr2[j] ? arr1[i] : arr2[j];
            if(arr1[i] <= arr2[j]){
                i++;
            }else if(arr1[i] > arr2[j]){
                j++;
            }
        }
        return i == len1 ? arr2[j] : arr1[i];
    }

    private static int getLeftMedian(int[] arr1,int[] arr2){
        if(arr1 == null || arr2 == null || arr1.length != arr2.length){
            throw new RuntimeException("Input arr is invalid!");
        }

        int len1 = arr1.length;
        int len2 = arr2.length;

        int target = len1;   //代表从1开始到找到目标需要走的步数
        int i = 0, j = 0;
        int index = 0;
        //因为是上中位数，所以一定不会超过其中一个数组的长度，这里的循环判断条件也就是有效的
        while(i < len1 && j < len2){
            if(arr1[i] < arr2[j]){
                if(target-- == 0){
                    return arr1[i];
                }
                i++;
            }else if(arr1[i] > arr2[j]){
                if(target-- == 0){
                    return arr2[j];
                }
                j++;
            }else{
                if(target-- == 0){
                    return arr1[i];
                }
                i++;
            }
        }
        return i == len1 ? arr2[j] : arr1[i];
    }
}
