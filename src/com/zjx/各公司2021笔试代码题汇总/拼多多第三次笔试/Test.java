package com.zjx.各公司2021笔试代码题汇总.拼多多第三次笔试;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int p = 1000000007;
        int sum = 0;
        if(m == 1 || n == 1){
            System.out.print(1);
        }else{
            int sum1 = jump(m );
            if (n == 2){
                System.out.print(sum1 % p);
            }else if(n == 3){
                System.out.print(2 * sum1 % p);
            }else if (n == 4){
                System.out.print((3*sum1+ sum1 *sum1) % p);
            }else if (n == 5){
                System.out.print((3*(sum1*sum1)+3*sum1) % p);
            }
        }
    }
    public static  int jump(int target){
        if(target == 1){
            return 1;
        }
        if (target == 2){
            return 2;
        }
        return jump(target - 1) + jump(target - 2);
    }
}
