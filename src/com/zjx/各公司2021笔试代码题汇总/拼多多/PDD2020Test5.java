package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 在一块长为n，宽为m的场地上，有n✖m个 1✖1的单元格。
 * 每个单元格上的数字就是按照从1到n和1到m中的数的乘积。具体如下
 * n = 3, m = 3
 * 1   2   3
 * 2   4   6
 * 3   6   9
 * 给出一个查询的值k，求出按照这个方式列举的的数中第k大的值v。
 * 例如上面的例子里，
 * 从大到小为(9, 6, 6, 4, 3, 3, 2, 2, 1)
 * k = 1, v = 9
 * k = 2, v = 6
 * k = 3, v = 6
 * ...
 * k = 8, v = 2
 * k = 9, v = 1
 * 输入描述:
 * 只有一行是3个数n, m, k 表示场地的宽高和需要查询的k。使用空格隔开。
 * 输出描述:
 * 给出第k大的数的值。
 * 输入例子1:
 * 3 3 4
 * 输出例子1:
 * 4
 */
public class PDD2020Test5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int k = Integer.parseInt(str[2]);
        System.out.println(process(n, m, k));
    }

    //错误做法   每个数可能使用不止一次   不能直接弹出
    public static long process(int n, int m, int k) {
        int res = 0;
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.offer(n);
        queue2.offer(m);
        int min = Integer.MAX_VALUE;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int p = queue1.peek();
            int q = queue2.peek();
            res = p * q;
            if (--k == 0) return res;
            if (p > 1 && q > 1) {
                if ((p - 1) * q >= p * (q - 1)) {
                    queue1.poll();
                    queue1.offer(p - 1);
                    res = (p - 1) * q;
                    if (--k == 0) return res;
                } else {
                    queue2.poll();
                    queue2.offer(q - 1);
                    res = p * (q - 1);
                    if (--k == 0) return res;
                }
            } else if (p > 1) {
                return (p - k + 1);
            } else {
                return (q - k + 1);
            }
        }
        return res;
    }
}
