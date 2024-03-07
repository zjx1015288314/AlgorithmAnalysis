package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

/**
 * 给定一个由0和1组成的2维矩阵，返回该矩阵中最大的由1组成的正方形的面积
 * 输入
 * [[1,0,1,0,0],[1,0,1,1,1],[1,1,1,1,1],[1,0,0,1,0]]
 * 输出
 * 4
 */
public class 矩阵中都是1的最大正方形大小 {

    //O(N^2) dp[i][j]表示以dp[i][j]为右下角的正方形边长
    public int solve2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int [m + 1][n + 1];

        int max = 0;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(matrix[i - 1][j - 1] == '1'){ //记得<i - 1, j - 1>对应matrix中位置
                    //dp[i-1][j-1],dp[i][j-1]),dp[i-1][j]覆盖了正方形除右下角以外的全部区域
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1],dp[i][j-1]),dp[i-1][j]) + 1;
                    max = Math.max(max,dp[i][j]);  //这里的边长其实是1的数量
                }
            }
        }
        return max * max;
    }


    //复杂度：O(N^4)
    public int solve(char[][] matrix) {
        int maxSide = 0;  //正方形每条边上1的最大个数
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    //遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    //计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1,如果不是，直接跳出此次循环
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        //判断新增一行和新增一列
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }

    /**
     * 对于该题的变种:给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的
     * 最大正方形子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
     * 边界都是1的最大正方形大小的解题步骤是 先预处理矩阵使用两个预处理矩阵,
     * right[i][j]从当前位置出发向右有多少个连续的1
     * down[i][j]表示从当前位置出发向下有多少个连续的1
     * 示例 1：
     * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：9
     *
     * 示例 2：
     * 输入：grid = [[1,1,0,0]]
     * 输出：1
     *
     * O(N^3)  空间复杂度O(N^2)
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/largest-1-bordered-square
     */
    public int solve1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //dp[i][j][0]: (i,j)及上边连续1的个数
        //dp[i][j][1]: (i,j)及左边连续1的个数
        //dp[i][j]是作为正方形的右下角来定位的
        int[][][] dp = new int[m + 1][n + 1][2];  //这样申请可以不用处理边界
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果当前位置是0，就跳过
                if (matrix[i - 1][j - 1] == 0)
                    continue;
                //如果是1，我们就计算横向和竖向连续1的个数
                dp[i][j][0] = dp[i][j - 1][0] + 1;
                dp[i][j][1] = dp[i - 1][j][1] + 1;
            }
        }

        int maxSide = 0;//记录正方形的最大长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //沿着当前坐标往上和往左找出最短的距离，暂时看做是正方形的边长(正方形的具体边长
                //还要看上边和左边的长度，所以这里要判断一下)
                int curSide = Math.min(dp[i][j][0], dp[i][j][1]);
                //如果边长小于maxSide，即使找到了也不可能再比maxSide大，所以我们没必要再找，直接跳过，
                if (curSide <= maxSide)
                    continue;
                //curSide可以认为是正方形下边和右边的长度，我们还需要根据正方形上边和左边的长度
                //来确认是否满足正方形的条件
                for (; curSide > maxSide; curSide--) {
                    //判断正方形的左边和上边的长度是否大于curSide，如果不大于，我们就缩小正方形
                    //的长度curSide，然后继续判断
                    if (dp[i][j - curSide + 1][1] >= curSide && dp[i - curSide + 1][j][0] >= curSide) {
                        maxSide = curSide;
                        //更短的就没必要考虑了，这里直接中断
                        break;
                    }
                }
            }
        }
        //返回正方形的边长
        return maxSide * maxSide;
    }
}
