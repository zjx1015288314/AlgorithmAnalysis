package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 作者：WZ6877
 * 链接：https://www.nowcoder.com/discuss/465066?channel=666&source_id=subject
 * 食堂吃饭, 分中餐和晚餐, 每一餐又分若干种套餐. 每种套餐有热量和美味值两个属性.
 * 每餐最多只能选一种套餐(可不吃). 问在满足能量值的情况下, 最少摄入的热量. 返回最少的热量,
 * 如果不能达到美味值则返回-1. 50%的中餐晚餐不超过100000种, 100%的中餐晚餐不超过200000种.
 * 3 3 10 // 中餐套餐数 晚餐套餐数 要求的美味值
 * 1 1 // 热量 美味值
 * 2 5
 * 3 7
 * 2 4
 * 4 8
 * 6 9
 * 将返回 5 (中餐选3 7 晚餐选 2 4, 此时热量最少)
 */
public class PDD2021Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int m = Integer.parseInt(str[0]);
        int n = Integer.parseInt(str[1]);
        int t = Integer.parseInt(str[2]);
        int[][] mid = new int[m][2];
        int[][] late = new int[n][2];
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            mid[i][0] = Integer.parseInt(str[0]);
            mid[i][1] = Integer.parseInt(str[1]);
        }
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            late[i][0] = Integer.parseInt(str[0]);
            late[i][1] = Integer.parseInt(str[1]);
        }
        int res = search(m, n, t, mid, late);
        if (res == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    public static int search(int m, int n, int t, int[][] mid, int[][] late) {
        if (t <= 0) return 0;
        int res = Integer.MAX_VALUE;
        int midLen = mid.length;
        int lateLen = late.length;
        //这里只是单纯排序，如果遇到美味值小但是热量大的食物而可以
        //将热量值替换为后面食物中最小的美味值
        Arrays.sort(mid, (o1, o2) -> o1[1] - o2[1]);
        Arrays.sort(late, (o1, o2) -> o1[1] - o2[1]);
        //根据此思想，重新刷新两个数组
        int minHot = Integer.MAX_VALUE;
        for (int i = midLen - 1; i >= 0; i--) {
            mid[i][0] = Math.min(mid[i][0],minHot);
            minHot = mid[i][0];
        }
        minHot = Integer.MAX_VALUE;
        for (int i = lateLen - 1; i >= 0; i--) {
            late[i][0] = Math.min(late[i][0],minHot);
            minHot = late[i][0];
        }
        // 如果两餐的最大热量不够, 返回-1 , 很重要，决定了二分查找的必要性
        if(mid[midLen - 1][1] + late[lateLen - 1][1] < t){ return -1;}
        // 如果第一餐的最大美味值超过t, 则根据二分查找判断一下只吃第一餐满足要求的最少热量
        if (mid[midLen - 1][1] >= t){
            int idx = binarysearch(mid,t);
            res = Math.min(res,mid[idx][0]);
        }
        // 如果第二餐的最大美味值超过t, 则判断一下只吃第二餐满足要求的最少热量
        if (late[lateLen - 1][1] >= t){
            int idx = binarysearch(late,t);
            res = Math.min(res,late[idx][0]);
        }
        for (int i = 0; i < midLen; i++) {
            int midBeauty = mid[i][1];
            if (midBeauty < t){
                int idx = binarysearch(late,t - midBeauty);
                res = Math.min(res,mid[i][0] + late[idx][0]);
            }else{
                break;
            }
        }

        //O(N^2)解法
//        for (int i = 0; i < mid.length; i++) {
//            if (mid[i][1] + late[j][1] < t) continue;
//            while (j >= 0 && mid[i][1] + late[j][1] >= t) j--;
//            if (mid[i][0] + late[j + 1][0] < res) {
//                res = mid[i][0] + late[j + 1][0];
//            }
//        }
        return res;
    }

    public static int binarysearch(int[][] arr, int t){
        int l = 0, r = arr.length - 1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            int midBeauty = arr[mid][1];
            if(midBeauty < t){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }
}
