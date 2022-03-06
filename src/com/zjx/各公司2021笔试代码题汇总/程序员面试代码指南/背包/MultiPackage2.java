package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.背包;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 多重背包的优化  二进制优化+01背包
 * 优化多重背包的优化
 * 首先，我们不能用完全背包的优化思路来优化这个问题，
 * 因为每组的物品的个数都不一样，是不能像之前一样推导不优化递推关系的。
 * 我们列举一下更新次序的内部关系：
 * f[i , j ] = max( f[i-1,j] , f[i-1,j-v]+w , f[i-1,j-2*v]+2*w , f[i-1,j-3*v]+3*w , .....)
 * f[i , j-v]= max( f[i-1,j-v] , f[i-1,j-2*v] + w , f[i-1,j-2*v]+2*w , .....)
 * 由上两式，可得出如下递推关系： f[i][j]=max(f[i,j-v]+w , f[i-1][j])
 * 接下来，我介绍一个二进制优化的方法，假设有一组商品，一共有11个。我们知道，十进制数字 11 可以这样表示
 * 11=1011(B)=0111(B)+(11−0111(B))=0111(B)+0100(B)
 * 正常背包的思路下，我们要求出含这组商品的最优解，我们要枚举12次（枚举装0，1，2....12个）。
 * 现在，如果我们把这11个商品分别打包成含商品个数为1个，2个，4个，4个（分别对应0001,0010,0100,0100）的四个"新的商品",
 * 将问题转化为01背包问题，对于每个商品，我们都只枚举一次，那么我们只需要枚举四次 ，就可以找出这含组商品的最优解。
 * 这样就大大减少了枚举次数。种优化对于大数尤其明显，例如有1024个商品，
 * 在正常情况下要枚举1025次 ， 二进制思想下转化成01背包只需要枚举10次。
 * 优化的合理性的证明
 * 先讲结论：上面的1，2，4，4是可以通过组合来表示出0~11中任何一个数的，还是拿11证明一下：
 * 首先，11可以这样分成两个二进制数的组合：
 * 11=0111(B)+(11−0111(B))=0111(B)+0100(B)
 * 其中0111通过枚举这三个1的取或不取（也就是对0001(B)，0010(B)，0100(B)的组合），
 * 可以表示十进制数0~7( 刚好对应了 1，2，4 可以组合出 0~7 ) , 0~7的枚举再组合上0100(B)( 即 十进制的 4 ) ，
 * 可以表示十进制数 0~11。其它情况也可以这样证明。这也是为什么，这个完全背包问题可以等效转化为01背包问题，
 * 参考链接：https://www.acwing.com/solution/content/5527/
 */
public class MultiPackage2 {
    static class Goods {
        int v; //体积
        int w; //价值
        public Goods(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];
        int[] s = new int[N + 1];

        // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
        for (int i = 1; i <= N; i++) {
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
            s[i] = reader.nextInt();
        }
        reader.close();
        //二进制处理
        List<Goods> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= s[i]; j *= 2) {
                s[i] -= j;
                list.add(new Goods(v[i] * j, w[i] * j));
            }
            if (s[i] > 0) {
                list.add(new Goods(v[i] * s[i], w[i] * s[i]));
            }
        }
        int[] dp = new int[V + 1]; //与01背包一样，从第1个元素开始对其,数组长度就是切分后的个数+1
        for (Goods good : list) {
            for (int j = V; j >= good.v; j--) { //体积j不会超过V
                dp[j] = Math.max(dp[j], dp[j - good.v] + good.w);
            }
        }
        System.out.print(dp[V]);
    }
}

