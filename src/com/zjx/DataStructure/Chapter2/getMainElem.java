package com.zjx.DataStructure.Chapter2;

/**
 * 主元素的概念是一个元素的出现次数占50%以上.利用主快排方法求已知有主元素的数组的主元素,复杂度O(nlogn),
 * 占50%，所以中间元素一定是主元素
 */
public class getMainElem {

    static int get(int A[], int n) {
        quickSort(A);
        return A[n / 2];
    }

    /*
     * 快排最重要的是partition方法,有两种方法,一种是从两边同时开始并同时交换;
     * 一种是先从后边开始,遇到比轴点小的,先赋值给左边,再从左边开始
     */

    public static void quickSort(int[] array) {
        if (array != null) {
            quickSort(array, 0, array.length - 1);
        }
    }

    private static void quickSort(int[] array, int beg, int end) {
        if (beg >= end || array == null)
            return;
        int p = partition(array, beg, end);
        quickSort(array, beg, p - 1);
        quickSort(array, p + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int i = start, j = end;
        while (i < j) {
            //从数组头尾两端入手,知道遇到比first大(正向)/小(逆向)的元素
            while (array[i] <= pivot && i < end) {
                i++;
            }
            while (array[j] > pivot && j >= start) {
                j--;
            }
            if (i < j) {  //交换array[i]与array[j]
                array[i] = array[i] ^ array[j];
                array[j] = array[i] ^ array[j];
                array[i] = array[i] ^ array[j];
            }
        }
        if (j != start) {//交换first与j索引下的元素
            array[j] = array[start] ^ array[j];
            array[start] = array[start] ^ array[j];
            array[j] = array[start] ^ array[j];
        }
        return j;
    }

    private static int partition1(int[] array, int start, int end) {
        int pivot = 0;
        //当开始位置小于结束位置时（数组有数据）  进行排序  也就是递归入口
        if (start < end) {
            //首先找到基准数  作为比较的标准数  取数组开始位置   从哪里开始  用哪个数当标准数 这个就是标准数
            int stard = array[start];
            //记录需要进行排序的下标
            int low = start;
            int high = end;

            //循环比对比标准数大和小的数字
            while (low < high) {
                //
                while (low < high && stard <= array[high])
                    high--;
                //如果标准数大于 右边的数字,用低位数字替换右边数字
                array[low] = array[high];
                //如果左边的数字比标准数小
                while (low < high && array[low] <= stard)
                    low++;
                //如果左边的数字比标准数大
                //用左边数字替换右边数字
                array[high] = array[low];
            }
            //把标准数赋给高或者低所在的元素
            array[low] = stard;
            pivot = low;
        }
        return pivot;
    }

    //利用主元素的特性求已知有主元素的数组的主元素，复杂度O(n)
    //因为其占50%以上，出现一次得＋＋，不同得－－，所以最后保留下来的一定是主元素
    static int get1(int A[], int n) {
        int result, cnt;
        result = A[0];
        cnt = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] == result) {
                cnt++;
            } else if (cnt == 1) {//相当于cnt--,因为cnt==0,result=A[i],cut=1
                result = A[i];

            } else {
                cnt--;
            }
        }
        return result;
    }

    //利用主元素的特性求不知是否有主元素的数组的主元素，复杂度O(n)
    //在上个程序上进行加工，求出后判断其数量是否占总数50%，是就是主元素，否则无主元素
    static int get2(int A[], int n) {
        int result, cnt;
        result = A[0];
        cnt = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] == result) {
                cnt++;
            } else if (cnt == 1) {
                result = A[i];
                cnt = 1;
            } else {
                cnt--;
            }
        }
        cnt = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == result)
                cnt++;
        }
        if (cnt > (n / 2))
            return result;
        else
            return -1;
    }


    public static void main(String[] args) {
        System.out.println("The main Elemnet of get is " + get(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4}, 8));
        System.out.println("The main Elemnet of get1 is " + get1(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4}, 8));
        System.out.println("The main Elemnet of get2 is " + get2(new int[]{3, 3, 4, 2, 4, 4, 2, 4}, 7));
    }

}
