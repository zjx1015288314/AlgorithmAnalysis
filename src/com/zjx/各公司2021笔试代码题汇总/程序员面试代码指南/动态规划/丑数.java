package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，
 * 因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第 n个丑数。
 *
 * 数据范围：
 * 要求：空间复杂度O(N)， 时间复杂度O(N)
 *
 * @link https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/https://leetcode-cn.com/problems/ugly-number-ii/solution/chou-shu-ii-by-leetcode-solution-uoqd/
 */
public class 丑数 {
    public static void main(String[] args) {
//        int res = GetUglyNumber_Solution1(1500);
        int res = GetUglyNumber_Solution2(1500);
        System.out.println(res);
    }

    /**
     * 复杂度高，超时
     * @param index
     * @return
     */
    public static int GetUglyNumber_Solution1(int index) {
        if(index >= 0 && index <= 6 ) return index;

        int preUglyNumber = 6;
        for(int i = 7; i <= index; i++) {
            int startNum = preUglyNumber + 1;
            while(!isUglyNumber(startNum)) {
                startNum++;
            }
            preUglyNumber = startNum;
        }
        return preUglyNumber;
    }

    private static boolean isUglyNumber(int num) {
        while((num % 2 == 0) || (num % 3 == 0) || (num % 5 == 0)) {
            if (num % 2 == 0) {
                num /= 2;
            }else if(num % 3 == 0) {
                num /= 3;
            }else {
                num /= 5;
            }
        }
        return num == 1;
    }

    /**
     * 动态规划，每一个丑数都是由更小的丑数 *2/*3/*5得到，
     * 下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都是1。
     * 时间复杂度:O(N)   空间复杂度:O(N)
     * 也可以使用最小堆PriorityQueue和HashSet配合，每次取出堆顶元素，并将其*2,*3,*5的结果入推
     * 为了防止溢出，堆内存Long型变量
     * @param index
     * @return
     */
    public static int GetUglyNumber_Solution2(int index) {
        if(index == 0) return 0;
        int[] dp = new int[index + 1];
        // 初始化很重要
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        // i从2开始
        for(int i = 2; i <= index; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            int newUglyNum = Math.min(Math.min(num2, num3), num5);
            dp[i] = newUglyNum;
            if(dp[i] == num2) {
                p2++;
            }
            if(dp[i] == num3) {
                p3++;
            }
            if(dp[i] == num5) {
                p5++;
            }
        }
        return dp[index];
    }
}
