package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @autho2020/6/26
 * @since 1.0.0
 */
public class GetMinKthByBFPRT {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] ss = br.readLine().split(" ");
            int N = Integer.parseInt(ss[0]);
            int k = Integer.parseInt(ss[1]);
            int[] arr = new int[N];
            ss = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(ss[i]);
            }
            StringBuffer sb = new StringBuffer();
            int[] res = getMinNumsByBFPRT(arr,k);
            for(int i = 0; i < res.length; i++){
                sb.append(res[i] + " ");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int[] getMinNumsByBFPRT(int[] arr, int k){
        if(k < 1 || k > arr.length){
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr,k);  //找到arr中第k小的数
        int[] res = new int[k];
        int index = 0;
        for(int i = 0; i != arr.length; i++){
            if(arr[i] < minKth){
                res[index++] = arr[i];
            }
        }
        for(; index != res.length; index++){
            res[index] = minKth;
        }
        return res;
    }

    private static int getMinKthByBFPRT(int[] arr, int K){
        int[] copyArr = copyArray(arr); //因为后面要移动数组元素，所以这里使用拷贝数组
        return select(copyArr, 0, copyArr.length - 1, K - 1); //K-1表示索引
    }

    private static int[] copyArray(int[] arr){
        int[] res = new int[arr.length];
        for(int i = 0; i != res.length; i++){
            res[i] = arr[i];
        }
        return res;
    }

    private static int select(int[] arr, int begin, int end, int i){
        if(begin == end) return arr[begin];

        int pivot = medianOfMedians(arr, begin, end); //根据BFPRT算法找到中位数
        //类似快排分区间,但返回值表示区间的中间部分
        int[] pivotRange = partition(arr, begin, end, pivot);
        if(i >= pivotRange[0] && i <= pivotRange[1]){
            return arr[i];
        }else if(i < pivotRange[0]){
            return select(arr, begin, pivotRange[0] - 1, i);
        }else{
            return select(arr, pivotRange[1] + 1, end, i);
        }
    }

    private static int medianOfMedians(int[] arr, int begin, int end){
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset]; //考虑无法整除
        for(int i = 0; i < mArr.length; i++){
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(endI,end)); //将每5个元素一组，通过插入排序取得中位数
        }
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);  //找到中位数数组mArr中的中位数
    }

    private static int[] partition(int[] arr, int begin, int end, int pivotValue){
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while(cur != big){
            if(arr[cur] < pivotValue){
                swap(arr, ++small, cur++);
            }else if(arr[cur] > pivotValue){
                swap(arr, cur, --big);
            }else{
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    //取得中位数
    private static int getMedian(int[] arr, int begin, int end){
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);  //下中位数索引
        return arr[mid];
    }

    //插入排序
    private static void insertionSort(int[] arr, int begin, int end){
        for(int i = begin + 1; i <= end; i++){
            for(int j = i; j > begin && arr[j] < arr[j - 1]; j--){
                swap(arr,j,j - 1);
            }
        }
    }

    private static void swap(int[] arr, int i ,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
