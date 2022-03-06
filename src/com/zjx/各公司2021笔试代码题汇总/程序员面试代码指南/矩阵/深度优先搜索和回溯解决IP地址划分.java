package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.util.ArrayList;

/**
 * 现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
 * 例如：
 * 给出的字符串为"25525511135",
 * 返回["255.255.11.135", "255.255.111.35"]. (顺序没有关系)
 * 示例1
 * 输入
 * "25525511135"
 * 输出
 * ["255.255.11.135","255.255.111.35"]
 */
public class 深度优先搜索和回溯解决IP地址划分 {
    ArrayList<String> res = new ArrayList<>();
    public ArrayList<String> restoreIpAddresses (String s) {
        if(s == null || s.isEmpty()) return  res;

        ArrayList<String> list = new ArrayList<>();
        dfs(s, 0, list);
        return res;
    }

    /**
     * @param s
     * @param start  从start开始新一轮划分
     * @param list
     */
    public void dfs(String s, int start, ArrayList<String> list){
        //dfs的成功出口是要start == s.length() && list.size() == 4两个条件的匹配
        //如果不满足且下面两个if条件不满足时，进入失败出口，否则进入正常逻辑
        if(start == s.length() && list.size() == 4){
            res.add(list.get(0)+'.'+list.get(1)+'.'+list.get(2)+'.'+list.get(3));
            return;
        }
        if(s.length( )- start > 3 * (4 - list.size())) return;  //剩下的长度太长
        if(s.length() - start < 4 - list.size()) return;   //剩下的长度太短

        int num = 0;
        for(int i = start; i < start + 3 && i < s.length(); i++){
            num = num * 10 + (s.charAt(i) - '0');
            if(num < 0 || num > 255) continue;

            list.add(s.substring(start,i+1));
            dfs(s, i + 1, list);
            list.remove(list.size()-1);

            if(num == 0) break;  //！！！！0只能有一位，即不能出现00， 01， 001这样
        }
    }
}
