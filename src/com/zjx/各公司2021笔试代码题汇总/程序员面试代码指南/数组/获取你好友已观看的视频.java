package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

import java.util.*;

/**
 * 有n 个人，每个人都有一个 0到n-1的唯一id。
 *
 * 给你数组 watchedVideos和friends，其中watchedVideos[i]
 * 和friends[i]分别表示id = i的人观看过的视频列表和他的好友列表。
 *
 * Level1的视频包含所有你好友观看过的视频，level2的视频包含所有你好友的好友观看过的视频，
 * 以此类推。一般的，Level 为 k的视频包含所有从你出发，最短距离为k的好友观看过的视频。
 *
 * 给定你的id 和一个level值，请你找出所有指定 level 的视频，并将它们按观看频率升序返回。如果有频率相同的视频，请将它们按字母顺序从小到大排列。
 *
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * 输出：["B","C"]
 * 解释：
 * 你的 id 为 0（绿色），你的朋友包括（黄色）：
 * id 为 1 -> watchedVideos = ["C"]
 * id 为 2 -> watchedVideos = ["B","C"]
 * 你朋友观看过视频的频率为：
 * B -> 1
 * C -> 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-watched-videos-by-your-friends
 */
public class 获取你好友已观看的视频 {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> queue = findLevelKFriends(friends, id, level);

        //对levelK的朋友观看视频的频率进行统计
        Map<String, Integer> watchedVideosFrequency = new HashMap<>();
        for(int identity : queue) {
            List<String> videos = watchedVideos.get(identity);
            for(String video : videos) {
                watchedVideosFrequency.put(video, watchedVideosFrequency.getOrDefault(video, 0) + 1);
            }
        }

        //对结果进行排序
        List<Map.Entry<String,Integer>> list = new ArrayList<>(watchedVideosFrequency.entrySet());
        list.sort((o1, o2) -> {
            if(o1.getValue().compareTo(o2.getValue()) != 0){
                return o1.getValue().compareTo(o2.getValue());
            }else{
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        List<String> result = new ArrayList<>();
        for(Map.Entry<String,Integer> entry: list){
            result.add(entry.getKey());
        }
        return result;
    }

    private Queue<Integer> findLevelKFriends(int[][] friends, int id, int level) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);   //类似于树的层次遍历
        Set<Integer> visited = new HashSet<>();
        visited.add(id);    //如果B是A的朋友，那么在level2统计A的朋友喜欢的视频时不能回头把A的喜好加进去

        for(int i = level; i > 0; i--) {
            int size = queue.size();
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < size; j++) {
                int identity = queue.poll();
                int[] friend = friends[identity];
                for(int k = 0; k < friend.length; k++) {
                    if(!visited.contains(friend[k])) {
                        set.add(friend[k]);
                        visited.add(friend[k]);
                    }
                }
            }
            for(int item : set) {
                queue.offer(item);
            }
        }
        return queue;
    }
}
