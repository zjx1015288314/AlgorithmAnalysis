package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 *
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 猜数字 {
    public int getMoneyAmount(int n) {
        //return process(1,n);
        return process1(n);
    }

    /**
     * 暴力解法
     * 时间复杂度：O(n!)我们选择一个数作为第一次尝试，然后递归中再选一个数，这样重复n次的时间代价为 O(n!)
     * 空间复杂度：O(n) n层递归的开销。
     */
    public int process(int left,int right){
        if(left >= right){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = left; i <= right; i++){
            int res = i + Math.max(process(left,i - 1),process(i + 1,right));
            min = Math.min(res,min);
        }
        return min;
    }

    /**
     * 动态规划
     */
    public int process1(int n){
        int[][] dp = new int[n + 2][n + 1];  //这里n+2是为了方式k=j且j=n时 dp[k+1][j]越界异常

        for(int i = n; i >= 1; i--) {
            for(int j = i + 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;   //如果不处理，则dp[i][j] = 0
                for(int k = i; k <= j; k++) {
                    int res = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    dp[i][j] = Math.min(dp[i][j], res);
                }
            }
        }
        return dp[1][n];
    }
}
