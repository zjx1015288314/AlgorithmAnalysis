package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.连续子数组相关;

import java.util.ArrayList;
import java.util.List;

/**
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数!!!!!!!!!!!)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,
 * 你能不能也很快的找出所有和为S的连续正数序列?
 *
 * 数据范围：(0,100]
 * 时间复杂度:O(N)
 *
 * @Link https://www.nowcoder.com/practice/c451a3fd84b64cb19485dad758a55ebe?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 和为S的连续正整数序列 {

    public static void main(String[] args) {
        List<List<Integer>> continuousSequence = findContinuousSequence(15);
        for (List<Integer> list : continuousSequence) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> findContinuousSequence(int sum) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> sequence = new ArrayList<>();
        if(sum == 1 || sum == 2) return res;   //至少两个数
        int preSum = 0;   //连续子序列的和
        int num = 1;  //待加入子序列的数，每次循环递增
        //不用超过一半，减少循环次数  如sum=101，则只需要验证到51即可
        while(num <= sum / 2 + 1) { //变相限制sequence的个数不能少于2,因为除了1和2以外，num永远取不到sum
            sequence.add(num);
            preSum += num;

            while(preSum > sum) { //删除头部数字
                int n = sequence.removeFirst();
                preSum -= n;
            }
            //小于则不做处理，等于加入res
            if(preSum == sum) {
                res.add(new ArrayList<>(sequence));
            }
            num++;
        }
        return res;
    }
}
