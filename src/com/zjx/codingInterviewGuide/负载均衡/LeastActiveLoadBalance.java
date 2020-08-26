package com.zjx.codingInterviewGuide.负载均衡;

import java.util.*;

/**
 * 最少活跃连接数
 */
public class LeastActiveLoadBalance {
    public static void main(String[] args) {

        System.out.println(getServer());
    }

    private static String getServer() {
        // 找出当前活跃最小的服务器
        Optional<Integer> minValues = ServerIps.ACTIVITY_LIST.values().stream().min(Comparator.naturalOrder());

        if (minValues.isPresent()) {
            List<String> minActivityIps = new ArrayList<>();
            ServerIps.ACTIVITY_LIST.forEach((ip, activity) -> {
                if (activity.equals(minValues.get())) {
                    minActivityIps.add(ip);
                }
            });

            // 最小活跃数的ip有多个，则根据权重来选，权重大的优先
            if (minActivityIps.size() > 1) {
                // 过滤出对应的ip和权重
                PriorityQueue<String> serverIpByWeight = new PriorityQueue(
                        (ip1, ip2) -> ServerIps.WEIGHT_LIST.get(ip2) - ServerIps.WEIGHT_LIST.get(ip1));
                for (String ip : minActivityIps) {
                    serverIpByWeight.add(ip);
                }
                return serverIpByWeight.poll();
//                Map<String, Integer> weightList = new LinkedHashMap<>();
//                ServerIps.WEIGHT_LIST.forEach((ip, weight) -> {
//                    if (minActivityIps.contains(ip)) {
//                        weightList.put(ip, ServerIps.WEIGHT_LIST.get(ip));
//                    }
//                });
//                // 获取权重最大值的服务器IP
//                return getServerByMaxWeight(weightList);
            } else {
                return minActivityIps.get(0);
            }
        } else {
            // 没配置活跃数，则走加权随机
            return getServerByWeightRandom();
        }
    }

    /**
     * 加权随机
     *
     * @return
     */
    public static String getServerByWeightRandom() {
        boolean sameWeight = true; // 是否所有权重都相等
        int totalWeight = 0; // 总权重

        Object[] weightArr = ServerIps.WEIGHT_LIST.values().toArray(); //HashMap用法
        for (int i = 0; i < weightArr.length; i++) {
            Integer weight = (Integer) weightArr[i];
            totalWeight += weight;
            if (sameWeight && i > 0 && weightArr[i] != weightArr[i - 1]) {
                sameWeight = true;
            }
        }
        Random random = new Random();
        int pos = random.nextInt(totalWeight);

        if (!sameWeight) {
            for (String key : ServerIps.WEIGHT_LIST.keySet()) {
                if (pos < ServerIps.WEIGHT_LIST.get(key)) {
                    return key;
                }
                pos -= ServerIps.WEIGHT_LIST.get(key);
            }
        }

        // 如果所有权重都相等，随机一个IP
        return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[random.nextInt(ServerIps.WEIGHT_LIST.size())];
    }
}
