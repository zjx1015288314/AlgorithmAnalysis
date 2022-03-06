package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 在未排序的数组中找到第 k 大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @author zhaojiexiong
 * @create 2020/7/15
 * @since 1.0.0
 */
public class 未排序数组中找第K大小 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int len = Integer.parseInt(ss[0]);
        int k = Integer.parseInt(ss[1]);
        int[] arr = new int[len];
        ss = br.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }
        //7 4
        //5 2 4 1 3 6 0
        System.out.println(findKthLargest(arr, k));
    }

    /**
     * 方法一：快排完成之后直接就可以找到，和快排稍有区别的是，此次排序每次只排序半个区间，复杂度O(N)
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) return 0;
        int len = nums.length;
        int target = len - k;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int index = partition(nums, left, right);
            if (index - 1 < target) {
                //target -= index - left;  //注意target变化
                left = index;
            } else {
                right = index - 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left++, right--);
            }
        }
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 方法二：堆排
     * 复杂度：建立大顶堆(O(N)) + 堆排(kO(logN))
     */
    public int findKthLargest2(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1 || k > nums.length) return 0;

        int heapSize = nums.length;
        buildHeap(nums, heapSize);   //建立大顶堆

        while (k > 1) {
            swap(nums, 0, --heapSize);
            heapify(nums, 0, heapSize);
            k--;
        }
        return nums[0];
    }

    public void buildHeap(int[] nums, int heapSize) {
        for(int i = heapSize / 2 - 1; i >= 0; i--) {
            heapify(nums, i, heapSize);
        }
    }

    public void heapify(int[] nums, int parent, int heapSize) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;

        int largest = parent;
        if(left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }
        if(right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        if(largest != parent) {
            swap(nums, parent, largest);
            heapify(nums, largest, heapSize);   //记得继续堆化
        }
    }
}
