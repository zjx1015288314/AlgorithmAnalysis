package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.负载均衡;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash
 * 为什么选用：保证在删除或新增节点的情况下尽可能小的影响原有节点的数据分布，从而减少数据迁
 * 移的大小，防止集群雪崩的发生，其一致性在于尽可能让原来的节点数据保持一致性不变
 * 同时为了防止热点数据所在的节点崩溃时可能引起的集群雪崩，增加虚拟节点，将压力分散到多个节点
 */
public class ConsistentHashLoadBalance {
    private static SortedMap<Integer,String> virtualNodes = new TreeMap<>();
    //虚拟节点个数
    private static final int VIRTUAL_NODES = 400;

    static {
        // 对每个真实节点添加虚拟节点，虚拟节点会根据哈希算法进行散列
        for (String ip : ServerIps.LIST) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
//                int vhash = getHash(ip + "VN" + i);
                int vhash = (ip + "VN" + i).hashCode();
                vhash = Math.abs(vhash);
                virtualNodes.put(vhash, ip);
            }
        }
    }
    private static String getServer(String client) {
//        int hash = getHash(client);
        int hash = Math.abs(client.hashCode());  //以防溢出

        // 得到大于该Hash值的排好序的Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);

        // 大于该Hash值的第一个元素的位置
        Integer nodeIndex = subMap.firstKey();

        // 如果不存在大于该Hash值的元素，则返回根节点
        if (nodeIndex == null) {
            nodeIndex = virtualNodes.firstKey();
        }

        // 返回对应的虚节点名称
        return virtualNodes.get(nodeIndex);
    }

    // hash实现,没看懂
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为附属则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }
}
