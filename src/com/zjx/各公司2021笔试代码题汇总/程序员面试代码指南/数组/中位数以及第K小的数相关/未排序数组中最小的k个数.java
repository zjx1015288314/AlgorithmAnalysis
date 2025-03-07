package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.util.Arrays;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可
 * @author zhaojiexiong
 * @create 2020/7/25
 * @since 1.0.0
 *
 * @link https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 未排序数组中最小的k个数 {

    public static void main(String[] args) {
        int[] arr = {5,2,4 ,1, 3 ,6 ,0};
        int k = 3;
        int[] res = smallestK1(arr,k);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }

    //-----------------------------------快排--------------------------------------------
    /**
     * 快速排序,因快排本来就是分区间排序，如果分区的结果正好符合k，直接返回
     */
    public static int[] smallestK1(int[] arr, int k) {
        if(arr == null || arr.length == 0 || k < 1 || k >  arr.length) return new int[]{};
        int left = 0;
        int right = arr.length - 1;
        int target = k - 1;
        while(left < right){
            int index = partition(arr,left,right);
            if(index - 1 == target){  //这里与求第k小的逻辑不同，因为是求最小的k个数，所以直接返回。如果求第k小的数，需要再次判断
                break;
            }else if(index - 1 < target){
                left = index;
            }else{
                right = index - 1; //注意right 不能写为index - 2，那样导致index-1位置的元素不参与排序
            }
        }
        return Arrays.copyOfRange(arr,0,target + 1);
    }

    private static int partition(int[] arr, int left, int right){
        int pivot = arr[(left + right) / 2];
        while(left <= right){
            while(arr[left] < pivot) left++;
            while(arr[right] > pivot) right--;
            if(left <= right){
                swap(arr,left++,right--);
            }
        }
        return left;
    }

    //-----------------------------------堆排--------------------------------------------
    /**
     * 堆排  建立大顶堆  之后进行length - k次调整将最大的值移到末尾，最后返回0到k-1
     */
    public int[] smallestK(int[] arr, int k) {
        if(arr == null || arr.length == 0 || k < 1 || k >  arr.length) return new int[]{};
        int heapSize = arr.length;
        buildHeap(arr,heapSize);
        //for(int i = arr.length - 1; i >= k; i--){
        while(heapSize > k){
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
        }
        return Arrays.copyOfRange(arr,0,k);
    }

    public void buildHeap(int[] arr,int heapSize){
        for(int i = heapSize / 2 - 1; i >= 0; i--){
            heapify(arr,i,heapSize);
        }
    }
    private void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        if(left < heapSize && arr[left] > arr[largest]){
            largest = left;
        }
        if(right < heapSize && arr[right] > arr[largest]){
            largest = right;
        }
        if(largest != index){
            swap(arr,largest,index);
            heapify(arr,largest,heapSize);
        }
    }

    private static void swap(int[] arr,int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
