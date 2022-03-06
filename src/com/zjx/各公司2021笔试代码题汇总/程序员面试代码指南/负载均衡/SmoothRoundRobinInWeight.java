package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.负载均衡;


/**
 * 平滑加权轮询,可将请求均匀的分布到各个节点上
 * 加权轮询的缺点：请求被分配到三个节点上的机会不够平滑，假设这样一个场景：在内存中初始化一个数组，
 * 数组内容是根据权重值生成相等数量的节点，如，a、b、c三个节点权重值分别是1、2、4，
 * 则生成一个数组：{a, b, b, c, c, c, c}，维护一个总请求数 totalCount，
 * 每次使用总请求数对数组长度取模mod，mod就是要选中的节点在数组中的下标，则前3次请求都不会打在c节点上
 */
public class SmoothRoundRobinInWeight {

    static final String[] serverIp = {"192.168.1.1", "192.168.1.2", "192.168.1.3", "192.168.1.4"};
    static int[] originalWeight = {1, 2, 4, 3};
    static int[] currentWeight = {1, 2, 4, 3};  //初始为0

    public static String getServer() {
        int totalWeight = 0; // 总权重
        int maxWeight = 0;
        int maxWeightIdx = -1;
        for (int i = 0; i < originalWeight.length; i++) {
            totalWeight += originalWeight[i];
            if(currentWeight[i] > maxWeight) {
                maxWeight = currentWeight[i];
                maxWeightIdx = i;
            }
        }

        currentWeight[maxWeightIdx] -= totalWeight;  //当前最大权重减去总权重
        for (int i = 0; i < currentWeight.length; i++) {
            currentWeight[i] += originalWeight[i];   //所有都增加
        }
        return serverIp[maxWeightIdx];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
