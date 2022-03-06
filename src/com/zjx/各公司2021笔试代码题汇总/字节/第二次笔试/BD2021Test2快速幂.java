package com.zjx.各公司2021笔试代码题汇总.字节.第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 计算 a op b == ?    op是操作类型+ - * ^
 * 输入
 * 2 1000000000 ^
 * 140625001
 */
public class BD2021Test2快速幂 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int a = Integer.parseInt(str[0]);
        int b = Integer.parseInt(str[1]);
        String op = str[2];

        System.out.println(process(a,b,op));
    }

    public static long process(long a, long b, String op){
        int p = 1000000007;
        if (op.equals("+")){
            return (a + b) % p;
        }else if (op.equals("-")){
            return (a - b) % p;
        }else if (op.equals("*")){
            long tmp = (a % p) * (b % p);
            return tmp % p;
        }else if (op.equals("^")){
            //快速幂  logn复杂度
            long res = 1;
            long base = a;
            while (b > 0){
                if (b % 2 == 0){   //偶数
                    b /= 2;
                    base = (base * base) % p;
                }else{  //奇数
                    b -= 1;
                    res = res * base % p;
                }
            }
            return res;
        }
        return -1;
    }
}
