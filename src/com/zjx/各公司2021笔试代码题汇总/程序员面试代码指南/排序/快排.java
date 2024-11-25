package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/7/10
 * @since 1.0.0
 */
public class 快排 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        String[] ss = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);  //2 7 8 3 1 6 9 0 5 4 9 19 12 16 14 12 22 33
        }
        int[] res = quickSort(arr,0,arr.length - 1);
        StringBuffer sb = new StringBuffer();
        for (int i : res) {
            sb.append(i + " ");
        }
        System.out.print(sb);
    }

    public static int[] quickSort(int[] arr, int low, int high){
        if (arr == null || arr.length == 0) return arr;
        // index这里是右区间的第一个元素下标
        int index = partition(arr, low, high);
        // low < index - 1表示区间数组还有大于1个元素时，继续递归(1个元素就不用排序了)
        if (low < index - 1) quickSort(arr,low,index - 1);
        if (index < high) quickSort(arr,index,high);
        return arr;
    }
    public static int partition(int[] arr, int low, int high){
        int pivot = arr[(low + high) / 2];
        // ！！这里加不加等号，主要取决于partition方法最后想返回什么,这里是想返回右区间的开始
        while (low <= high){
            while(arr[low] < pivot) low++;
            while (arr[high] > pivot) high--;
            // ！！！同上
            if(low <= high) swap(arr,low++,high--);  //in case of low > high
        }
        return low;
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
