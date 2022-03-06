package com.zjx.各公司2021笔试代码题汇总.腾讯;

import java.io.IOException;
import java.util.*;

/**
 * 腾讯2021笔试
 */
public class TX2021Test2 {
    static int[] unionFind;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //人数
        int m = sc.nextInt();  //团队数量
        if(n == 0 && m == 0){
            return;
        }
        unionFind = new int[n + 1];
        int[] count = new int[n +1];
        Arrays.fill(count,1);
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int k = sc.nextInt();
            int before = -1;
            for (int j = 0; j < k; j++) {
                int v = sc.nextInt();
                if(before != -1){
                    int x = find(before);
                    int y = find(v);
                    //x == y说明之前合并过 这里就不用做改变
                    //x != y说明还没有合并
                    //这部相当于并查集中的union
                    if(x != y){
                        if(x == 0){
                            unionFind[y] = 0;
                            count[0] += count[y];
                        }else if(y == 0){
                            unionFind[x] = 0;
                            count[0] += count[x];
                        }else{
                            unionFind[x] = y;
                            count[y] += count[x];
                        }
                    }

                }
                before = v;
            }
        }
        System.out.println(count[0]);
    }

    private static int find(int before) {
        return before ==  unionFind[before] ? before : find(unionFind[before]);
    }
}
