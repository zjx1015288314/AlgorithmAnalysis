package com.zjx.codingInterviewGuide.负载均衡;

/**
 * 加权轮询
 */
public class RoundRobinInWeight {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getServer());
        }
    }

    private static Integer offset = 0;
    private static Integer sequenceNum = 0; //请求序号，每次调用都会增加

    public static String getServer() {
        boolean sameWeight = true; // 是否所有权重都相等
        int totalWeight = 0; // 总权重

        Object[] weightArray = ServerIps.WEIGHT_LIST.values().toArray();
        for (int i = 0; i < weightArray.length; i++) {
            Integer weight = (Integer) weightArray[i];
            totalWeight += weight;
            if (sameWeight && i > 0 && weight != weightArray[i - 1]) {
                sameWeight = false;
            }
        }

        offset = (sequenceNum++) % totalWeight;
        if (!sameWeight) {
            for (String key : ServerIps.WEIGHT_LIST.keySet()) {
                if (offset < ServerIps.WEIGHT_LIST.get(key)) {
                    return key;
                }
                offset = offset - ServerIps.WEIGHT_LIST.get(key);
            }
        }

        // 如果权重都相等，则按照简单轮询策略，也可以不写
        String serverIp = "";
        synchronized (offset) {
            if (offset >= ServerIps.WEIGHT_LIST.size()) {
                offset = 0;
            }
            serverIp = (String) ServerIps.WEIGHT_LIST.keySet().toArray()[offset];
            offset++;
        }
        return serverIp;
    }
}
