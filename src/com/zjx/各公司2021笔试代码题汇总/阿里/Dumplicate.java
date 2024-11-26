package com.zjx.各公司2021笔试代码题汇总.阿里;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 猿辅导：输入N表示N节课，接下来输入N行每行输入课程的开始时间和结束时间，求最多的时候有几节课时间重了。
 * 输入示例 ：
 * 4
 * 1 4
 * 1 2
 * 2 3
 * 3 4
 * 输出：2
 */
public class Dumplicate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        System.out.println(getDumplicateCourse(arr));
    }


    public static int getDumplicateCourse(int[][] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        queue.offer(arr[0][1]);   //queue中存放每节课的结束时间
        int ans = 1;  //重复课程数量
        for (int i = 1; i < arr.length; i++) {
            while(!queue.isEmpty() && queue.peek() <= arr[i][0]){
                queue.poll();
            }
            queue.offer(arr[i][1]);
            ans = Math.max(ans,queue.size());
        }
        return ans;
    }
}
