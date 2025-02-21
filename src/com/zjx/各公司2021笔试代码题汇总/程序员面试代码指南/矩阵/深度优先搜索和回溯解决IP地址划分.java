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
 *
 * 我们用SEG_COUNT=4表示 IP 地址的段数。
 *
 * 时间复杂度：O(3^SEG_COUNT×∣s∣)。由于 IP 地址的每一段的位数不会超过3，因此在递归的每一层，我们最多只会深入到下一层的3种情况。
 * 由于SEG_COUNT=4，对应着递归的最大层数，所以递归本身的时间复杂度为O(3^SEG_COUNT)。
 * 如果我们复原出了一种满足题目要求的 IP 地址，那么需要O(∣s∣)的时间将其加入答案数组中，因此总时间复杂度为O(3^SEG_COUNT×∣s∣)。
 *
 * 空间复杂度：O(SEG_COUNT)，这里只计入除了用来存储答案数组以外的额外空间复杂度。递归使用的空间与递归的最大深度SEG_COUNT成正比。
 * 并且在上面的代码中，我们只额外使用了长度为SEG_COUNT的数组segments存储已经搜索过的 IP 地址，因此空间复杂度为O(SEG_COUNT)。
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
        // !!! i < s.length() 不能漏写, 否则会数组越界
        for(int i = start; i < start + 3 && i < s.length(); i++){
            num = num * 10 + (s.charAt(i) - '0');
            if(num < 0 || num > 255) break;

            list.add(s.substring(start,i+1));
            dfs(s, i + 1, list);
            list.remove(list.size()-1);

            if(num == 0) break;  //！！！！0只能有一位，即不能出现00， 01， 001这样
        }
    }
}
