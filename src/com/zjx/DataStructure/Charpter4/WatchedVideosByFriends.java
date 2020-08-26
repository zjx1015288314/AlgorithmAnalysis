package com.zjx.DataStructure.Charpter4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 有 n 个人，每个人都有一个  0 到 n-1 的唯一id 。
 * 给你数组watchedVideos和friends，其中watchedVideos[i]和friends[i] 分别表示 id = i 的人观看过的视频列表和他的好友列表。
 * Level 1 的视频包含所有你好友观看过的视频，level2的视频包含所有你好友的好友观看过的视频，以此类推。一般的，Level 为 k 的视频包含
 * 所有从你出发，最短距离为 k 的好友观看过的视频。给定你的id和一个level值，请你找出所有指定level的视频，并将它们按观看频率升序
 * 返回。如果有频率相同的视频，请将它们按名字字典序从小到大排列。
 * 示例 1：
 * 0
 * / \
 * 1  2
 * \  /
 * 3
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
 * 输出：["B","C"]
 * 解释：
 * 你的 id 为 0 ，你的朋友包括：
 * id 为 1 -> watchedVideos = ["C"] 
 * id 为 2 -> watchedVideos = ["B","C"] 
 * 你朋友观看过视频的频率为：
 * B -> 1 
 * C -> 2
 * 示例 2：
 * 0
 * / \
 * 1  2
 * \  /
 * 3
 * 输入：watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
 * 输出：["D"]
 * 解释：
 * 你的 id 为 0 ，你朋友的朋友只有一个人，他的 id 为 3 。
 * 提示：
 * n == watchedVideos.length == friends.length
 * 2 <= n <= 100
 * 1 <= watchedVideos[i].length <= 100
 * 1 <= watchedVideos[i][j].length <= 8
 * 0 <= friends[i].length < n
 * 0 <= friends[i][j] < n
 * 0 <= id < n
 * 1 <= level < n
 * 如果 friends[i] 包含 j ，那么 friends[j] 包含 i
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-watched-videos-by-your-friends
 */
public class WatchedVideosByFriends {
    /**
     * 解法一：自己的解法
     *
     * @param watchedVideos
     * @param friends
     * @param id
     * @param level
     * @return
     */
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        HashMap<String, Integer> watchedVideosFrequency = new HashMap<>();
        //此题一定要清楚Level为k时包含所有从你出发，最短距离为 k 的好友观看过的视频，所以不包括level<k的朋友
        boolean[] visited = new boolean[friends.length];  //很重要，通过该数组确定某一个id之前没有出现过
        Arrays.fill(visited, false);   //注意借助现有的方法
        LinkedList<Integer> res = new LinkedList<>(); //可替换Queue
        res.add(id);
        visited[id] = true;
        for (int i = 0; i < level; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                for (int y : getFriends(res.remove(), friends)) {
                    if (!visited[y]) {
                        visited[y] = true;
                        res.add(y);
                    }
                }
            }
        }

        while (!res.isEmpty()) {
            int x = res.remove();
            // for (String video: watchedVideos.get(x)) {
            //     watchedVideosFrequency.compute(video, (k, v) -> v == null ? 1 : v + 1);
            // }
            for (String video : watchedVideos.get(x)) {
                if (watchedVideosFrequency.get(video) == null) {
                    watchedVideosFrequency.put(video, 1);
                } else {
                    int frequency = watchedVideosFrequency.get(video);
                    //导致程序错误的地方,应该是++frequency，而不是frequency++,这样做会导致少算一次出现的频率
                    watchedVideosFrequency.put(video, ++frequency);

                }

            }
        }

        //对结果进行排序,这里将Map转换为List，使用List.sort(Comparator<? super Map.Entry<String.Integer>>)
        //compare的结果会影响结果的升序排列：
        //String和Integer都实现了Comparabl,它们的比较用compareTo方法,String的比较从第一位开始比较,
        //如果遇到不同的字符,则马上返回这两个字符的ascii值差值.返回值是int类型，如果两个长度不同的字符串，
        //第一个String为长字符串，且包含短字符串，则返回len1-len2;先比较ASCII值再比较长度
        //Collection.sort()也有两种，一种是直接传List，另一种是传List，Comparator<? super V>,并重写compare方法
        List<Map.Entry<String, Integer>> list = new ArrayList<>(watchedVideosFrequency.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().compareTo(o2.getValue()) != 0) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }

            }
        });
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.add(entry.getKey());
        }
        return result;


    }

    public List<Integer> getFriends(int id, int[][] friends) {
        List<Integer> res = new LinkedList<>();
        int[] id_friends = friends[id];
        for (int i : id_friends) {
            res.add(i);
        }
        return res;
    }


    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList(new String[]{"A", "B"}));
        list.add(Arrays.asList(new String[]{"C"}));
        list.add(Arrays.asList(new String[]{"B", "C"}));
        list.add(Arrays.asList(new String[]{"D"}));
        int[][] friends = {{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        new WatchedVideosByFriends().watchedVideosByFriends(list, friends, 1, 3);
    }

    /**
     * 解法二
     * 作者：小白二号
     * 链接：https://leetcode-cn.com/circle/discuss/BlV9CE/view/vIbyv2/
     *
     * @param watchedVideos
     * @param friends
     * @param id
     * @param level
     * @return
     */
    public List<String> watchedVideosByFriends2(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        boolean[] visited = new boolean[friends.length];
        Arrays.fill(visited, false);
        Queue<Integer> Q = new LinkedList<>();
        Q.add(id);
        visited[id] = true;

        for (int i = 1; i <= level; i++) {
            int n = Q.size();
            for (int j = 0; j < n; j++) {
                int x = Q.poll();
                for (int y : friends[x]) {
                    if (visited[y]) {
                        continue;
                    }
                    visited[y] = true;
                    Q.add(y);
                }
            }
        }

        Map<String, Integer> counter = new HashMap<>();
        while (!Q.isEmpty()) {
            int x = Q.poll();
            for (String video : watchedVideos.get(x)) {
                counter.compute(video, (k, v) -> v == null ? 1 : v + 1);    //lambda表达式，新语法
            }
        }
        //看不懂stream的用法
        return counter.entrySet().stream().sorted(
                (x, y) -> {
                    if (x.getValue().intValue() == y.getValue().intValue()) {
                        return x.getKey().compareTo(y.getKey());
                    } else {
                        return x.getValue().compareTo(y.getValue());
                    }
                }
        ).map(x -> x.getKey()).collect(Collectors.toList());
    }
}
