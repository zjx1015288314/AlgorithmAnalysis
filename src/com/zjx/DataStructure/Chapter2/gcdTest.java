package com.zjx.DataStructure.Chapter2;

public class gcdTest {

    /**
     * 最大公约数(欧几里得算法)
     *
     * @param m
     * @param n
     * @return
     */
    public static long gcd2(long m, long n) {
        if ((m & 1) == 0 && (n & 1) == 0)
            return 2 * gcd(m / 2, n / 2);
        else if ((m & 1) == 0 && (n & 1) != 0)
            return gcd(m / 2, n);
        else if ((m & 1) != 0 && (n & 1) == 0)
            return gcd(m, n / 2);
        else
            return gcd((m + n) / 2, (m - n) / 2);
    }

    public static long gcd(long m, long n) {
        if (m < n) {
            m = m ^ n;
            n = m ^ n;
            m = m ^ n;
        }
        while (n != 0) {
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }


    /*
     *最小公倍数(两个数乘积除以最小公约数)
     *
     */
    public static long lcm(long m, long n) {
        long gcd = gcd(m, n);
        return m * n / gcd;
    }

    public static void main(String[] args) {
        System.out.println(gcd2(15, 20));        //习题2.16
        System.out.println(lcm(15, 20));         //习题2.16
    }
}
