package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/21
 * @since 1.0.0
 */
public class IsOnceInArr {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int len = Integer.parseInt(br.readLine());
            int[] chs = new int[len];
            String[] ss = br.readLine().split(" ");
            for (int i = 0; i < len; i++) {
                chs[i] = Integer.valueOf(ss[i]);
//                if (!heapInsert(chs, i)) {
//                    System.out.println("NO");
//                    return;
//                }
                System.out.println(chs[i]);
            }

//            System.out.println("YES");
//            System.out.print(isUnique2(chs) ? "YES" : "NO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //原题是字符数组char[],所以可以使用boolean[256],英文字符不超过256，而此题为数字，1≤arr[i]≤10^7
    private static boolean isUnique(int[] chs) {
        if (chs == null) {
            return true;
        }
        boolean[] map = new boolean[256];
        for (int i = 0; i < chs.length; i++) {
            if (map[chs[i]]) {
                return false;
            }
            map[chs[i]] = true;
        }
        return true;
    }

    //额外空间复杂度为O(1)
    private static boolean isUnique2(int[] chs) {
        if (chs == null) {
            return true;
        }
        heapSort(chs);
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == chs[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void heapSort(int[] chs) {
        for (int i = 0; i < chs.length; i++) {
            heapInsert(chs, i);    //自上而下的上滤     ，也可以采用自下而上的下滤 复杂度都为O(N)
        }
        for (int i = chs.length - 1; i > 0; i--) {
            //大根堆把最大的堆顶元素与末尾元素交换，即升序排序;小根堆把最小的堆顶元素与末尾元素交换，即降序排序
            swap(chs, 0, i);
            heapify(chs, 0, i);
        }
    }

    //    private static void heapInsert(int[] chs,int i){
//        int parent = 0;
//        while(i != 0){
//            parent = (i - 1) / 2;
//            if(chs[parent] < chs[i]){
//                swap(chs,i,parent);
//                i = parent;
//            }else{
//                break;
//            }
//        }
//    }
    private static boolean heapInsert(int[] chs, int i) {
        int parent = 0;
        while (i != 0) {
            parent = (i - 1) / 2;
            if (chs[parent] < chs[i]) {
                swap(chs, parent, i);
                i = parent;
            } else if (chs[parent] == chs[i]) {
                return false;
            } else {
                break;
            }
        }
        return true;
    }

    private static void heapify(int[] chs, int i, int size) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < size) {
            if (chs[left] > chs[i]) {
                largest = left;
            }
            if (right < size && chs[right] > chs[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(chs, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    private static void swap(int[] chs, int idx1, int idx2) {
        int tmp = chs[idx1];
        chs[idx1] = chs[idx2];
        chs[idx2] = tmp;
    }
}
