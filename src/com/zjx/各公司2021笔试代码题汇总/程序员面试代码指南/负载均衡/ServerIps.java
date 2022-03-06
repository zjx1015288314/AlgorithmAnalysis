package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.负载均衡;

import java.util.*;

/**
 * 定义一个服务器列表，每个负载均衡算法会从中挑出一个服务器作为算法的结果。
 */
public class ServerIps {
    //服务器列表
    public static final List<String> LIST = new ArrayList<>();
    //带有权重的服务器列表
    public static final Map<String,Integer> WEIGHT_LIST = new HashMap<>();
    //带有当前服务器活跃数的服务器清单
    public static final Map<String, Integer> ACTIVITY_LIST = new LinkedHashMap<>();

    static{
        LIST.add("192.168.1.1");
        LIST.add("192.168.1.2");
        LIST.add("192.168.1.3");
        LIST.add("192.168.1.4");
        LIST.add("192.168.1.5");
        LIST.add("192.168.1.6");
        LIST.add("192.168.1.7");
        LIST.add("192.168.1.8");
        LIST.add("192.168.1.9");
        LIST.add("192.168.1.10");

        WEIGHT_LIST.put("192.168.1.1", 9);
        WEIGHT_LIST.put("192.168.1.2", 1);
        WEIGHT_LIST.put("192.168.1.3", 8);
        WEIGHT_LIST.put("192.168.1.4", 2);
        WEIGHT_LIST.put("192.168.1.5", 7);
        WEIGHT_LIST.put("192.168.1.6", 3);
        WEIGHT_LIST.put("192.168.1.7", 6);
        WEIGHT_LIST.put("192.168.1.8", 4);
        WEIGHT_LIST.put("192.168.1.9", 5);
        WEIGHT_LIST.put("192.168.1.10", 5);

        ACTIVITY_LIST.put("192.168.1.1", 2);
        ACTIVITY_LIST.put("192.168.1.2", 0);
        ACTIVITY_LIST.put("192.168.1.3", 1);
        ACTIVITY_LIST.put("192.168.1.4", 3);
        ACTIVITY_LIST.put("192.168.1.5", 0);
        ACTIVITY_LIST.put("192.168.1.6", 1);
        ACTIVITY_LIST.put("192.168.1.7", 4);
        ACTIVITY_LIST.put("192.168.1.8", 2);
        ACTIVITY_LIST.put("192.168.1.9", 7);
        ACTIVITY_LIST.put("192.168.1.10", 3);
    }

    /**
     * 1.简单随机
     */
    public static String getServerByRandom(){
        Random random = new Random();
        int pos = random.nextInt(LIST.size());
        return LIST.get(pos);
    }

    /**
     * 2.加权随机
     */
    public static String getServerByWeightRandom(){
        boolean sameWeight = true; // 是否所有权重都相等
        int totalWeight = 0; // 总权重

        Object[] weightArr = WEIGHT_LIST.values().toArray(); //HashMap用法
        for (int i = 0; i < weightArr.length; i++) {
            Integer weight = (Integer) weightArr[i];
            totalWeight += weight;
            if(sameWeight && i > 0 && weightArr[i] != weightArr[i - 1]){
                sameWeight = true;
            }
        }
        Random random = new Random();
        int pos = random.nextInt(totalWeight);

        if (!sameWeight){
            for (String key : WEIGHT_LIST.keySet()) {
                if (pos < WEIGHT_LIST.get(key)){
                    return key;
                }
                pos -= WEIGHT_LIST.get(key);
            }
        }
        // 如果所有权重都相等，随机一个IP
        return (String) ServerIps.WEIGHT_LIST.keySet().toArray()[random.nextInt(ServerIps.WEIGHT_LIST.size())];
    }

    /**
     * 3.轮询
     * 缺点，该方法依赖静态的服务端地址列表，而在实际开发中，列表必然是动态的，比如机器的上下线
     * 我们可以在轮询开始前复制一份,这样可能会引入新的问题，复制以后原始链表的修改无法反映给新链表，
     * 也就是说这一轮选择服务器的过程中，新增服务器或者下线服务器，负载均衡算法将无法获知。
     * 新增无所谓，如果有服务器下线或者宕机，那么可能会访问到不存在的地址。因此，服务调用端需要有相应的容错处理
     */
    public static Integer pos = 0;
    public static String getServerByRoundRobin(){
//        List<String> tmp = new ArrayList<>();
//        tmp.addAll(LIST);  //轮询开始前复制一份
        String ip = null;
        synchronized (pos) {
            pos = pos % ServerIps.LIST.size();
            ip = ServerIps.LIST.get(pos);
            pos++;
        }

        //轮询的另一种写法
//        for(int i = 0; i < 10; i++){
//            i = i % ServerIps.LIST.size();
//            ip = ServerIps.LIST.get(i);
//        }
        return ip;
    }

    /**
     * 加权轮询
     */
    private static Integer offset = 0;
    private static Integer sequenceNum = 0; //请求序号，每次调用都会增加

    public static String getServerByRoundRobinInWeight() {
        boolean sameWeight = true; // 是否所有权重都相等
        int totalWeight = 0; // 总权重

        Object[] weightArray = WEIGHT_LIST.values().toArray();
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
