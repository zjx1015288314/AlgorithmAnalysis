package com.zjx.DataStructure.Chapter2;

public class QuickPower {
    /**
     * 递归实现,幂次n分为偶数和奇数的情况
     * @param x
     * @param n
     * @return
     */
    public static long pow(long x, int n){
        if(0 == n)
            return 1;
        if(1 == n)
            return x;
        if(n % 2 == 0)
            return pow(x*x, n/2);
        else
            return pow(x*x,n/2)*x;
        }

    /*
    *非递归实现, 幂次n用二进制表示,数组存放X,X^2,X^4,X^(2^logN)
    *
    */
    public static long pow1(long x, int n){
        long result = 1;
        if (n == 0)
            return 1;
        else if (n == 1)
            return x;
        if (x == 0)
            return 0;
        long[] power = new long[log2Of(n)];
        System.out.println((int)Math.log(n));
        power[0] = x;
        for (int i = 1; i < power.length; i++) {
            power[i] = power[i-1] * power[i-1];
            System.out.println(power[i]);
        }
        while(n > 0){
            int i = 0;
            if (n % 2 == 1)
                result *= power[i];
            i++;
            n /= 2;
        }
        return result;
    }
    static int log2Of(long n){
        //返回n的二进制位数
        return 1  ;
    }

    public static void main(String[] args) {
        System.out.println("the pow(12,5) is " + pow(12,5));
        System.out.println("the pow1(12,5) is " + pow1(12,4));
    }
}
