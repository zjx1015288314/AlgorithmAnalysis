package com.zjx.各公司2021笔试代码题汇总.猿辅导;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 一副牌7 1 5 2 3，首先拿出牌顶7，剩余的牌顶的牌1移到牌底，变为5 2 3 1，然后接着拿牌顶5,...直到拿完
 * 则最后的顺序为7 5 3 2 1，则为了得到从大到小的输出顺序，求原来的牌序
 */
public class 抽牌算法 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int N = Integer.parseInt(ss[0]);

        int[] arr = new int[N];
        ss = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }
        int[] res = process(arr);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    private static int[] process(int[] arr) {
        List<String> idxList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            idxList.add(i + "");
        }
        int[] res = new int[arr.length];
        int idx = 0;
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            res[idx] = arr[i];
            idxList.remove(idx + "");
            if (idxList.isEmpty()) break;  //防止除0异常
            num = (num + 1) % idxList.size();
            idx = Integer.parseInt(idxList.get(num));
        }
        return res;
    }
}
