package com.zjx.各公司2021笔试代码题汇总.华为;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1、幼儿园小朋友站成一列，按位置1、2、3....顺序编号，每个小朋友都拿了若干糖果，请找出3个小朋友，他们拿着相同颜色的糖果，
 * 且他们拿的糖果总数不少于其他任何小朋友（拿相同颜色的糖果）的糖果总数，如果存在多组这样的小朋友，则找出位置编号最小的小朋友所在的组。
 * 设置的前提条件：
 * （1）每个小朋友最少拿一颗糖，最多拿1024颗糖，且只拿一种颜色的糖果；不存在两个小朋友拿相同颜色相同树木的糖果。
 * （2）糖果颜色只有2种：1为红色，2为蓝色。
 * 输出描述：
 * 拿相同颜色且糖果总数最多的3位小朋友位置编号，糖果颜色及总数；第一行为3个小朋友位置编号（糖果数从小到大对应的位置编号） ，第二行为糖果颜色，第三行为糖果总数。
 * 如果没有满足条件的小朋友，则输出字符串“null”。
 * 示例1：
 * 输入
 * 6
 * 2 2
 * 2 1
 * 3 2
 * 5 2
 * 3 1
 * 7 2
 * 输出
 * 3 4 6
 * 2
 * 15
 */
public class HW2021Test {
    static class Children{
        int val;
        int color;
        int num;
        public Children(int val, int color,int num){
            this.val = val;
            this.color = color;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);

        int[][] arr = new int[N][2];
        PriorityQueue<Children> queue1 = new PriorityQueue<Children>((o1,o2) -> o2.num - o1.num);
        PriorityQueue<Children> queue2 = new PriorityQueue<Children>((o1,o2) -> o2.num - o1.num);
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(str[0]);
            arr[i][1] = Integer.parseInt(str[1]);
            if (arr[i][1] == 1){
                queue1.offer(new Children(arr[i][0],arr[i][1],i + 1));
            }else{
                queue2.offer(new Children(arr[i][0],arr[i][1],i + 1));
            }
        }
        process(arr,queue1,queue2);
    }

    public static void process(int[][] arr, PriorityQueue<Children> queue1, PriorityQueue<Children> queue2){
        if (queue1.size() < 3 && queue2.size() < 3){
            System.out.println("null");
            return;
        }
        int[] nums = new int[3];
        int idx = 0;
        int sum = 0;
        int color = 0;
        if (queue1.size() < 3){
            for (int i = 0; i < 3; i++) {
                Children c = queue2.poll();
                nums[idx++] = c.num;
                sum += c.val;
                color = c.color;
            }
            Arrays.sort(nums);
            printRes(nums,color,sum);
        } else if (queue2.size() < 3){
            for (int i = 0; i < 3; i++) {
                Children c = queue1.poll();
                nums[idx++] = c.num;
                sum += c.val;
                color = c.color;
            }
            Arrays.sort(nums);
            printRes(nums,color,sum);
        }else{
            int sum1 = 0;
            int[] nums1 = new int[3];
            int idx1 = 0;

            int sum2 = 0;
            int[] nums2 = new int[3];
            int idx2 = 0;

            for (int i = 0; i < 3; i++) {
                Children c = queue1.poll();
                nums1[idx1++] = c.num;
                sum1 += c.val;
            }
            for (int i = 0; i < 3; i++) {
                Children c = queue2.poll();
                nums2[idx2++] = c.num;
                sum2 += c.val;
            }
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            if (sum1 > sum2 || (sum1 == sum2 && nums1[0] < nums2[0])){
                printRes(nums1,1,sum1);
            }else if (sum1 < sum2 || (sum1 == sum2 && nums1[0] > nums2[0])){
                printRes(nums2,2,sum2);
            }
        }
    }

    public static void printRes(int[] num,int color,int sum){
        for (int item : num){
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println(color);
        System.out.println(sum);
    }
}
