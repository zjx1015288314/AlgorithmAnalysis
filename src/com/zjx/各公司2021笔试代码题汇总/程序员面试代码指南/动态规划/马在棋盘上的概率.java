package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.动态规划;

/**
 * 已知一个NxN的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为(0, 0)，
 * 最右下角的记为(N-1, N-1)。
 * 现有一个 “马”（也译作 “骑士”）位于(r, c)，并打算进行K 次移动。
 * 如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。
 * 输入: 3, 2, 0, 0
 * 输出: 0.0625
 * 解释:
 * 输入的数据依次为 N, K, r, c
 * 第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
 * 所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
 *
 * 注意：
 *    N 的取值范围为 [1, 25]
 *    K 的取值范围为 [0, 100]
 *   开始时，“马” 总是位于棋盘上
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 马在棋盘上的概率 {

    public static void main(String[] args) {
        knightProbability1(3, 2, 0, 0);
    }

    /**
     * 递归+回溯
     */
    static final int[][] directs = {{-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}, {1, 2}, {1, -2}, {2, 1}, {2, -1}};
    public static double knightProbability1(int n, int k, int row, int column) {
        Double[][][] cache = new Double[n][n][k + 1];  //缓存计算过的值，降低复杂度,否则会超时
        double res = process(n, k, row, column, cache);
        double totalSum = Math.pow(8, k);
        return res / totalSum;
    }

    public static double process(int n, int k, int row, int column, Double[][][] cache) {
        if(k == 0) return 1.0;

        if (cache[row][column][k] != null) {
            return cache[row][column][k];
        }

        double cnt = 0;
        for(int[] direct : directs) {
            int newX = row + direct[0];
            int newY = column + direct[1];
            if(newX >= 0 && newX < n && newY >= 0 && newY < n) {
                cnt += process(n, k - 1, newX, newY,cache);
            }
        }
        cache[row][column][k] = cnt;
        return cnt;
    }

    /**
     * 动态规划 dp[i][j][step]表示还剩step时马在dp[i][j]处的存活率
     */
    public double knightProbability2(int N, int K, int r, int c) {
        int[][] dir= {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        double[][][] dp= new double[N][N][K+1];
        for (int step = 0; step <= K; step++){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if(step == 0){
                        dp[i][j][step] = 1.0;  //基数
                        continue;
                    }
                    for (int[] d : dir){
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < N && y >= 0 && y < N){
                            //在进入第step层时，step-1已经遍历完毕
                            dp[i][j][step] += dp[x][y][step - 1] / 8.0;  //外层的情况是内层的1/8
                        }
                    }
                }
            }
        }
        return dp[r][c][K];
    }
}
