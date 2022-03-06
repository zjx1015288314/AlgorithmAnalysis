package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.IOException;
import java.util.*;

/**
 * @author zhaojiexiong
 * @create 2020/6/22
 * @since 1.0.0
 */
public class GetShortestPaths {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int len = Integer.parseInt(br.readLine());
//        String[] ss = br.readLine().split(" ");  //第二行
        String[] ss = sc.nextLine().split(" ");
        String from = ss[0];  //起点
        String to = ss[1];
        List<String> words = new ArrayList<>();  //输入字符串列表
        String str = null;
        for (int i = 0; i < len; i++) {
//            words.add(br.readLine());
            words.add(sc.nextLine());
        }
        List<List<String>> res = findMinPaths(from,to,words);
        Collections.sort(res,new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                for(int i=0;i<o1.size()&&i<o2.size();i++) {
                    if(o1.get(i).compareTo(o2.get(i))<0) {
                        return -1;
                    }else if(o1.get(i).compareTo(o2.get(i))>0){
                        return 1;
                    }
                }
                return o1.size()>o2.size()?1:-1;
            }
        });
        //打印
        StringBuffer sb = new StringBuffer();
        if(!res.isEmpty()){
            sb.append("YES" + "\n");
            for(List<String> path : res){
                for(int i = 0; i < path.size(); i++){
                    String tmp = i == path.size() - 1 ? path.get(i) + "\n" : path.get(i) + " -> ";
                    sb.append(tmp);
                }
            }
        }else{
            sb.append("NO" + "\n");
        }
        System.out.print(sb.toString());
    }

    private static List<List<String>> findMinPaths(String from, String to, List<String> lists){
        lists.add(from);
        HashMap<String,ArrayList<String>> nexts = getNexts(lists);  //生成图的邻接链表
        HashMap<String,Integer> distances = getDistances(from,nexts);  //生成所有节点到start节点的最小距离

        //准备生成最短路径
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(from,to,nexts,distances,pathList,res); //最终结果
        return res;
    }

    private static HashMap<String,ArrayList<String>> getNexts(List<String> lists){
        Set<String> dict = new HashSet<>(lists);
        HashMap<String,ArrayList<String>> nexts = new HashMap<>();
        for(int i = 0; i < lists.size(); i++){
            nexts.put(lists.get(i),new ArrayList<>());
        }
        for(int i = 0; i < lists.size(); i++){
            ArrayList list = getNext(lists.get(i),dict);
            System.out.println(lists.get(i) + list);
            nexts.put(lists.get(i),list);
        }
        return nexts;
    }

    //生成word对应的邻接链表，因为题目要求字典序顺序输出，所以我们在添加next信息时按字典序加入
    private static ArrayList<String> getNext(String word, Set<String> dict){
        ArrayList<String> res = new ArrayList<>();
        char[] chs = word.toCharArray();
        for(int i = 0; i < chs.length; i++){
            for(char c = 'a'; c <= 'z'; c++){
                if(chs[i] != c){
                    char tmp = chs[i];
                    chs[i] = c;  //只替换一个字符
                    if(dict.contains(String.valueOf(chs))){
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        Collections.sort(res);   //默认按字典序排序
        return res;
    }

//    private static ArrayList<String> getNext(String word, Set<String> dict) {
//        ArrayList<String> res = new ArrayList<>();
//        char[] chs = word.toCharArray();
//        next(0,chs,dict,res);
//        return res;
//    }
//
//    //为了按照字典序排列，所以在chs[i]==c时，需要比较第二个字符，所以next需要修改为这样
//    private static void next(int i,char[] chs,Set<String> dict,ArrayList<String> res){
//        while(i < chs.length){
//            for (char c='a'; c <= 'z'; c++) {
//                if (chs[i] == c){
//                    next(i+1,chs,dict,res);
//                }else{
//                    char tmp = chs[i];
//                    chs[i] = c; //只替换一个字符
//                    if (dict.contains(String.valueOf(chs)) && !res.contains(String.valueOf(chs))) {
//                        res.add(String.valueOf(chs));
//                    }
//                    chs[i] = tmp;
//                }
//            }
//            i++;
//        }
//    }

    //利用宽度优先遍历，生成start节点到图中所有节点的最短距离
    private static HashMap<String,Integer> getDistances(String start, HashMap<String,ArrayList<String>> nexts){
        HashMap<String,Integer> distances = new HashMap<>();
        distances.put(start,0);
        Queue<String> queue = new LinkedList<>();   //层次遍历/宽度遍历需要的结构
        queue.add(start);
        Set<String> set = new HashSet<>();  //为了遍历过程中，不重复访问节点，所以将已访问过的加入set
        set.add(start);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            for(String str : nexts.get(cur)){
                if(!set.contains(str)){
                    distances.put(str,distances.get(cur) + 1);
                    queue.add(str);
                    set.add(str);
                }
            }
        }
        return distances;
    }

    private static void getShortestPaths(String cur, String to,
                                         HashMap<String, ArrayList<String>> nexts,
                                         HashMap<String,Integer> distances, LinkedList<String> solution,
                                         List<List<String>> res){
        solution.add(cur);  //进入方法前就保证是合法的
        if(to.equals(cur)){
            //res.add(solution);  //!!!!!不能直接写res.add(solution);这样属于引用传递，当solution最后弹出所有元素时，res中也被弹出，所以应该拷贝一份
            res.add(new LinkedList<>(solution));
        }else{
            for(String next : nexts.get(cur)){
                if(distances.get(next) == distances.get(cur) + 1){
                    getShortestPaths(next,to,nexts,distances,solution,res);
                }
            }
        }
        //因为深度遍历时共用一个List，不管成功与否，都要将当前节点弹出，这也是选择LinkedList原因之一
        solution.pollLast();
    }
}

