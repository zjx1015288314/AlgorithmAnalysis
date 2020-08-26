package com.zjx.codingInterviewGuide.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/061d419c7cea4c658ee0484654b11c3e
 * 来源：牛客网
 * 多多鸡打算造一本自己的电子字典，里面的所有单词都只由a和b组成。
 * 每个单词的组成里a的数量不能超过N个且b的数量不能超过M个。
 * 多多鸡的幸运数字是K，它打算把所有满足条件的单词里的字典序第K小的单词找出来，作为字典的封面。
 * 共一行，三个整数N, M, K。(0 < N, M < 50, 0 < K < 1,000,000,000,000,000)
 */
public class PDD2020Test3 {
    private static Map<String, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        long K = Long.parseLong(str[2]);   //long类型可表示2^19或者2^18

        System.out.println(process(N,M,K));
    }

    public static String process(int n, int m, long k) {
        StringBuffer sb = new StringBuffer();
        BigInteger[][] dp = new BigInteger[n + 1][m + 1];  //这里不要用Long,Long只能存储19位，BigInteger可以存储无限位
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new BigInteger(Integer.toString(i));
        }
        for (int j = 0; j <= m; j++) {
//            dp[0][j] = j;
            dp[0][j] = new BigInteger(Integer.toString(j));
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
//                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + 2;
                dp[i][j] = dp[i-1][j].add(dp[i][j-1]).add(new BigInteger("2"));
            }

        }
        System.out.println("dp[n][m]： " + dp[n][m]);  //n,m取50时，dp[n][m]： 399608854866744452032002440110
        while (k > 0) {
            if (n > 0 && m > 0) {
//                long leftNum = calcSubtreeNum(n - 1, m) + 1;  //复杂度过大
                if (dp[n - 1][m].compareTo(new BigInteger(Long.toString(k - 1))) >= 0){
                    sb.append("a");
                    k--;  //因为单独的a算第一个，所以k--
                    n--;
                }else{
                    sb.append("b");
                    k -= (dp[n - 1][m].longValue() + 2);  //！！！注意这里必须减
                    m--;
                }
            } else if (n == 0 && m > 0) {
                sb.append("b");
                k--;
                m--;
            } else if (n > 0 && m == 0) {
                sb.append("a");
                k--;
                n--;
            }
        }
        return sb.toString();
    }

    public static long calcSubtreeNum(long n, long m){
        long min = Math.min(n,m);
        long max = Math.min(n,m);
        String key = min + "_" + max;
        if (map.containsKey(key)){
            return map.get(key);
        }else if(n == 0){
            map.put(key,m);
            return m;
        }else if (m == 0){
            map.put(key,n);
            return n;
        }else{
            long value = calcSubtreeNum(n - 1,m) + calcSubtreeNum(n,m - 1) + 2; //+1表示不含子树,只有根节点
            map.put(key,value);
            return value;
        }
    }
}
