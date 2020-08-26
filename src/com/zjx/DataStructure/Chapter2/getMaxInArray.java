package com.zjx.DataStructure.Chapter2;

/**
 * 使用正数的数组a设计有效算法以确定：
 * a.  a[j]+a[i]的最大值,其中j>=i
 * b.  a[j]-a[i]的最大值,其中j>=i
 * c.  a[j]*a[i]的最大值,其中j>=i
 * d.  a[j]/a[i]的最大值,其中j>=i
 */
public class getMaxInArray {


    //max(a[j]+a[i]的最大值,其中j>=i)
    public static int f1(int[] a) {
        int len = a.length;
        if (len < 2)
            throw new ArrayStoreException("the number of items must be more than 2");
        int max = 0;
        int maxItem = a[0];
        for (int i = 0; i < len; i++) {

            if (max < a[i] + maxItem)
                max = a[i] + maxItem;
            if (maxItem < a[i])
                maxItem = a[i];
        }

        System.out.println("max(a[j]+a[i])=" + max);
        return max;
    }

    //max(a[j]-a[i]的最大值,其中j>=i)
    public static int f2(int[] a) {
        int len = a.length;
        if (len < 2)
            throw new ArrayStoreException("the number of items must be more than 2");
        int max = 0;
        int low = a[0];
        for (int i = 0; i < len; i++) {   //题目中j>=i,即存在j==i的情况,所以i从0开始
            if (a[i] < low) {
                low = a[i];
                continue;   //此时可直接跳过,也可以再检查一遍max < a[i] - low
            }

            if (max < a[i] - low)
                max = a[i] - low;

        }

        System.out.println("max(a[j]-a[i])=" + max);
        return max;
    }

    //max(a[j]*a[i]的最大值,其中j>=i)
    public static int f3(int[] a) {
        int len = a.length;
        if (len < 2)
            throw new ArrayStoreException("the number of items must be more than 2");
        int max = 0;
        int maxItem = a[0];
        for (int i = 0; i < len; i++) {

            if (max < a[i] * maxItem)
                max = a[i] * maxItem;
            if (maxItem < a[i])
                maxItem = a[i];
        }

        System.out.println("max(a[j]*a[i])=" + max);
        return max;
    }


    //max(a[j]/a[i]的最大值,其中j>=i)
    public static float f4(int[] a) {
        int len = a.length;
        if (len < 2)
            throw new ArrayStoreException("the number of items must be more than 2");
        float max = 0.0f;      //就该题来说可以写max=1.0,因为j等于i时,结果为1;如果不存在j==i的情况,则max必须初始化为0
        int low = a[0];
        for (int i = 0; i < len; i++) {

            if (max < (float) a[i] / low)    //必须要加float强制转型,否则两个整数相除的结果只能是整数
                max = (float) a[i] / low;
            if (a[i] < low)
                low = a[i];
        }

        System.out.println("max(a[j]/a[i])=" + max);
        return max;
    }


    public static void main(String[] args) {
        f4(new int[]{5, 4, 3, 2, 1});
    }

}
