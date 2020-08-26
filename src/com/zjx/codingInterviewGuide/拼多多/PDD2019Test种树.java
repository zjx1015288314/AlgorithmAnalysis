package com.zjx.codingInterviewGuide.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/52f25c8a8d414f8f8fe46d4e62ef732c?tpId=158&rp=1&ru=%2Fta%2Fexam-pdd&qru=%2Fta%2Fexam-pdd%2Fquestion-ranking
 * 小多想在美化一下自己的庄园。他的庄园毗邻一条小河，他希望在河边种一排树，共 M 棵。
 * 小多采购了 N 个品种的树，每个品种的数量是 Ai (树的总数量恰好为 M)。
 * 但是他希望任意两棵相邻的树不是同一品种的。小多请你帮忙设计一种满足要求的种树方案。
 * 输入描述:
 * 第一行包含一个正整数 N，表示树的品种数量。
 * 第二行包含 N 个正整数，第 i (1 <= i <= N) 个数表示第 i 个品种的树的数量。
 * 数据范围：
 * 1 <= N <= 1000
 * 1 <= M <= 2000
 * 输出描述:
 * 输出一行，包含 M 个正整数，分别表示第 i 棵树的品种编号 (品种编号从1到 N)。若存在多种可行方案，
 * 则输出字典序最小的方案。若不存在满足条件的方案，则输出"-"。
 * 示例1
 * 输入
 * 3
 * 4 2 1
 * 输出
 * 1 2 1 2 1 3 1
 */
public class PDD2019Test种树 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] arr = new int[N];
        str = br.readLine().split(" ");
        int M = 0;
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(str[i]);
            M += arr[i];
        }
//        int[] res = process(arr,M);
//        if(res == null || res.length == 0){
//            System.out.print("-");
//        }else{
//            for(int i : res){
//                System.out.print(i + " ");
//            }
//        }

        //方法二 100%
        if (dfs(0,M,arr)){
            for (int i : ans) {
                System.out.print(i + " ");
            }
        }else{
            System.out.println("-");
        }
    }

    /**
     * 为了保证字典序最小，每次都从头开始遍历
     * @param arr
     * @param M
     * @return
     */
    public static int[] process(int[] arr, int M){
        int[] res = new int[M];
        int remainNum = M;
        int idx = 0;
        for(int i = 0; i < M; i ++){
            if(!check(remainNum,arr)) return new int[0];  //检查arr数组中某种数的数量是否超过总数的一半
            int maxNum = Integer.MIN_VALUE;
            int maxNumIdx = -1;
            int minIdx = -1;
            for (int j = 0; j < arr.length; j++){ //找到最大数量的树
                if(maxNum < arr[j]){
                    maxNum = arr[j];
                    maxNumIdx = j;
                }
                if(arr[j] != 0 && minIdx == -1){minIdx = j;} //找到现存的编号最小的树
            }

            // 如果通过了check,则还要检查总数为奇数时，maxNum是否为remainNum的下中位数;若是，
            // 则优先种此树，否则后面种不下；否则考虑字典序与前后不相邻
            if(remainNum % 2 != 0 && maxNum == (remainNum + 1) / 2){
                res[idx++] = maxNumIdx + 1;
                arr[maxNumIdx]--;
                remainNum--;
            }else if (idx == 0 || res[idx - 1] != minIdx + 1){
                res[idx++] = minIdx + 1;
                arr[minIdx]--;
                remainNum--;
            }else{  //前后相邻的话，就选择下一个不为0的树
                while(++minIdx < arr.length){
                    if (arr[minIdx] != 0){
                        res[idx++] = minIdx + 1;
                        arr[minIdx]--;
                        remainNum--;
                        break;
                    }
                }
            }
        }
        return res;
    }

    //检查某种树的个数是否超过总数的一半
    static boolean check(int remainSum, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > (remainSum + 1) / 2) return false; //remainSum不管是奇偶，都可以用这个公式
        }
        return true;
    }

    //第二种解法 ： 暴力dfs 不考虑最优解，在种第i颗树的时候，尝试所有的可能
    private static ArrayList<Integer> ans = new ArrayList<>();
    static boolean dfs(int idx, int m,int[] arr) { //m是树的总量，arr[]是树的数量数组
        if (!check(m - idx,arr)) return false; //剪枝
        if (idx == m) {
            return true;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (idx == 0 || (arr[i] != 0 && i + 1 != ans.get(idx - 1))) {
                    arr[i]--; //dfs需要回溯
                    ans.add(i + 1);
                    if (dfs(idx + 1,m,arr)) return true;
                    ans.remove(ans.size() - 1);
                    arr[i]++; //dfs需要回溯
                }
            }
        }
        return false;
    }

}
