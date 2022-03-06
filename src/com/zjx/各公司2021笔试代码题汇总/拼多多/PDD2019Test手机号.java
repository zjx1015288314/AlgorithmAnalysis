package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A 国的手机号码由且仅由 N 位十进制数字(0-9)组成。一个手机号码中有至少 K 位数字相同则被定义为靓号。A 国的手机号可以有前导零，比如 000123456 是一个合法的手机号。
 * 小多想花钱将自己的手机号码修改为一个靓号。修改号码中的一个数字需要花费的金额为新数字与旧数字之间的差值。比如将 1 修改为 6 或 6 修改为 1 都需要花 5 块钱。
 * 给出小多现在的手机号码，问将其修改成一个靓号，最少需要多少钱？
 * 第一行包含一个整数，表示修改成一个靓号，最少需要的金额。
 * 第二行包含 N 个数字字符，表示最少花费修改的新手机号。若有多个靓号花费都最少，则输出字典序最小的靓号。
 * 输入
 * 6 5
 * 787585
 * 输出
 * 4
 * 777577
 * 说明
 * 花费为4的方案有两种：777577与777775，前者字典序更小。
 * https://www.nowcoder.com/practice/005af31a10834b3688911463065ab47d?tpId=158&rp=1&ru=%2Fta%2Fexam-pdd&qru=%2Fta%2Fexam-pdd%2Fquestion-ranking
 */
public class PDD2019Test手机号 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        int[] phone = new int[N];
        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            phone[i] = s.charAt(i) - '0';
        }
        int[] res = GreedyMinCost(N, K, phone);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }
    }

    //最小代价优先的贪心算法
    private static int[] GreedyMinCost(int phoneNumSize, int neededRepeatTimes, int[] phoneNum) {
        int minCost = Integer.MAX_VALUE;//想要最小值，初值设置最大整数
        int[] newPhoneNum = new int[phoneNumSize];
        int[] repeatTimesArr = getRepeatTimes(phoneNum);

        //！！！因为手机号有限，就直接0-9列举 这里从小到大符合字典序最小的要求
        for (int currentNum = 0; currentNum < 10; currentNum++) {
            int restNeededRepeatTimes = neededRepeatTimes - repeatTimesArr[currentNum];
            //初始可能重复数字次数就大于需求值
            if (restNeededRepeatTimes <= 0) {
                minCost = 0;
                newPhoneNum = phoneNum;
                break;
            }

            //直接从离currentNum最近的数开始改变，把改变的值用alternativePhoneNum存储起来
            int currentNumCost = 0;
            int upperLimitNum = currentNum + 1;
            int lowerLimitNum = currentNum - 1;

            int[] alternativePhoneNum = new int[phoneNumSize];
            //alternativePhoneNum每次循环前都初始为phoneNum的复制
            System.arraycopy(phoneNum, 0, alternativePhoneNum, 0, phoneNumSize);

            while (restNeededRepeatTimes > 0) {
                //先改大的数字，再改小的数字，目标是字典序最小，如果先改小的，字典序就会增加
                // 如果手机号中数字比最佳数字大的情况就从前往后改
                if (upperLimitNum < 10) {
                    for (int i = 0; i < phoneNumSize && restNeededRepeatTimes > 0; i++) {
                        if (phoneNum[i] == upperLimitNum) {
                            currentNumCost += upperLimitNum - currentNum;
                            alternativePhoneNum[i] = currentNum;
                            restNeededRepeatTimes--;
                        }
                    }
                }
                //如果手机号中数字比最佳数字小的情况就从后往前改
                if (lowerLimitNum >= 0) {
                    for (int i = phoneNumSize - 1; i >= 0 && restNeededRepeatTimes > 0; i--) {
                        if (phoneNum[i] == lowerLimitNum) {
                            currentNumCost += currentNum - lowerLimitNum;
                            alternativePhoneNum[i] = currentNum;
                            restNeededRepeatTimes--;
                        }
                    }
                }
                //扩大上下限
                upperLimitNum++;
                lowerLimitNum--;
            }

            if (currentNumCost < minCost) { //这里只考虑小于，不用考虑相等，因为currentNum是从小到大增加的
                minCost = currentNumCost;
                newPhoneNum = alternativePhoneNum;
            }
        }

        System.out.println(minCost);
        return newPhoneNum;
    }

    //得到0-9在原手机号中出现的次数
    private static int[] getRepeatTimes(int[] phoneNum) {
        int[] repeatTimes = new int[10];
        //我的做法是通过Map记录，这里用数组记录
        for (int i = 0; i < phoneNum.length; i++) {
            repeatTimes[phoneNum[i]]++;
        }
        return repeatTimes;
    }

    //自己的解法 只过70
    public static List<Node> process(int[] arr, int k) {
        List<Node> res = new ArrayList<>();
        Map<Integer, Integer> phoneNum = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!phoneNum.containsKey(arr[i])) {
                phoneNum.put(arr[i], 1);
            } else {
                phoneNum.put(arr[i], phoneNum.get(arr[i]) + 1);
            }
        }
        int minCost = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : phoneNum.entrySet()) {
            int key = entry.getKey();
            int tmp = k;
            PriorityQueue<Node> queue = new PriorityQueue<Node>(
                    (o1, o2) -> {
                        if (o1.val != o2.val) {
                            return Math.abs(o1.val - key) - Math.abs(o2.val - key);
                        } else if (o1.val >= key) {
                            return o1.idx - o2.idx;
                        } else {
                            return o2.idx - o1.idx;
                        }

                    });
            for (int i = 0; i < arr.length; i++) {
                queue.add(new Node(i, arr[i]));
            }
            ArrayList<Node> list = new ArrayList<>();
            int cost = 0;
            while (!queue.isEmpty() && tmp > 0) {
                Node node = queue.poll();
                cost += Math.abs(node.val - key);
                node.val = key;
                list.add(node);
                tmp--;
            }
            list.addAll(queue);
            if (cost <= minCost) {
                minCost = cost;
                res = list;
            }
        }
        System.out.println(minCost);
        return res;
    }

    static class Node {
        int idx;
        int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
