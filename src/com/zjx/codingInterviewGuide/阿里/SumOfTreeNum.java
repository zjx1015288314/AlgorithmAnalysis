package com.zjx.codingInterviewGuide.阿里;

import java.util.Scanner;

/**
 * 作者：阿帕亚
 * 链接：https://www.nowcoder.com/discuss/455839?type=post&order=time&pos=&page=1&channel=1013&source_id=search_post
 * 来源：牛客网
 * ## 阿里笔试
 * n 由三个互不相等的数相而得，这三个数两两的最大公约数是k，1 <= k <= n <= 10^18。
 * 输入：T组数据，每行给定n和k。
 * 输出：是否存在这样三个数，存在则输出任意一组答案（n = x + y +z）,不存在则输出-1。
 */
public class SumOfTreeNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            if (n % k != 0){  //有余数时肯定不满足情况
                System.out.println(-1);
                continue;
            }
            sumOfTreeNum(n,k);
        }
    }

    public static void sumOfTreeNum(int n, int k){
        long p = n / k;
        boolean flag = false;
        for (long x = 1; x <= p/2 && !flag; x++) {
            for (long y = x + 1; y < p/2 && !flag; y++) {
                long z =  (p - x - y);
                if (z != x && z != y) {
                    long temp1 = gcd(z, x);
                    if (temp1 != 1 ) continue;
                    long temp2 = gcd(z, y);
                    if (temp2 != 1 ) continue;
                    long temp3 = gcd(x, y);
                    if (temp3 != 1 ) continue;
                    System.out.println(x * k+ " " + y * k + " " + z * k);
                    flag = true;
                }
            }
        }
    }

    public static long gcd(long a, long b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
