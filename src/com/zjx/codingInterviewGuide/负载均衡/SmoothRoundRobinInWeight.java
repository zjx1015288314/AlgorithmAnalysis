package com.zjx.codingInterviewGuide.负载均衡;

import java.util.Arrays;

/**
 * 平滑加权轮询
 */
public class SmoothRoundRobinInWeight {
    public static void main(String[] args) {
        //选择完最大的ip，用于存放的
        int[] tmpWeightArr = new int[ServerIps.WEIGHT_LIST.size()];
        for (int i = 0; i < 100; i++) {
            System.out.println(getServer(tmpWeightArr));
        }
    }


    public static String getServer(int[] tmpArr) {
        int totalWeight = 0; // 总权重
        Object[] weightArray = ServerIps.WEIGHT_LIST.values().toArray();
        for (int i = 0; i < weightArray.length; i++) {
            totalWeight += (int)weightArray[i];
        }
        return "1";
    }
}
