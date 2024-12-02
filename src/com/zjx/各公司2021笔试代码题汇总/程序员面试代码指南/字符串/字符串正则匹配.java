package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 对于字符串str，其中绝对不含有字符’.’和‘*’。再给定字符串exp，其中可以含有’.’或’‘*’，
 * ’*’字符不能是exp的首字符，并且任意两个’*‘字符不相邻。exp中的’.’代表任何一个字符，
 * exp中的’*’表示’*‘的前一个字符可以有0个或者多个。请写一个函数，
 * 判断str是否能被exp匹配(注意：输入的数据不保证合法，但只含小写字母和‘.’和‘*’)。
 */
public class 字符串正则匹配 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s1 = bf.readLine();
        String s2 = bf.readLine();

        if(s1 == null || s2 == null){
            System.out.print("NO");
        }
        char[] s = s1.toCharArray();
        char[] e = s2.toCharArray();

//        System.out.print(isValid(s,e) ? isMatch(s1,s2) ? "YES" : "NO" : "NO");   //方法一通过 (次选)
//        System.out.print(isValid(s,e) ? process(s,e,0,0) ? "YES" : "NO" :"NO");  //方法二通过
//        System.out.print(isValid(s,e) ? processDP(s,e) ? "YES" : "NO" : "NO"); //DP:方法三(对应于方法二)通过
//        System.out.print(isValid(s,e) ? isMatchDP(s1,s2) ? "YES" : "NO" : "NO"); //DP:对应于方法一
        System.out.print(isMatchDp5(s1, s2) ? "YES" : "NO"); // （首选）, 从小到大DP
    }
    //方法一：
    //大体情况分为三种，根据p(模式串)的长度依次分为：0，1，>1；前两种情况比较好判断，最后一种情况即
    //(p.length() >= 2 && p.charAt(1) == '*')时，作两种选择：第一种是‘*’表示前面字符为0个，第二种是
    //贪心策略，在前面字符匹配成功的情况下，希望一直往后匹配
    private static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();   //很巧妙,统一多种情况，这里也知道substring()不会返回null
        //利用递归的思想,先判断各自的第一个字符是否匹配
        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        //对模式串长度分情况讨论，长度小于2或者第二个字符不是'*'时,就将s,p分成两部分;
        //否则，将情况再分两种情况,要么p排除前两个字符再与s比较,要么s排除第一个字符与p匹配
        if (p.length() >= 2 && p.charAt(1) == '*'){
            //isMatch(s, p.substrin(2))即可当做‘*’匹配0个，也可当做贪婪模式下最后的匹配
            return (isMatch(s, p.substring(2)) ||
                    (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    //方法二：在情况三时，作了不一样的处理
    private static boolean process(char[] s, char[] e, int si, int ei){
        if(ei == e.length) return si == s.length;
        boolean firstMatch = si != s.length && (e[ei] == s[si] || e[ei] == '.');
        if(ei + 1 == e.length || e[ei + 1] != '*'){//后面没有'*'
            return firstMatch && process(s,e,si + 1, ei + 1);
        }
        //先尽可能多地往后匹配  匹配分为两种第一个匹配的情况和第一个不匹配的情况
        while(si != s.length && (e[ei] == s[si] || e[ei] == '.')){  //这里si一直在变化，导致first
            if(process(s, e, si, ei + 2)){//先采取*代表0次，再是1次，直到后面的不再匹配，交给最后处理
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);

    }

    private static boolean isValid(char[] s, char[] e){
        for(int i = 0; i < s.length; i++){
            if(s[i] == '.' || s[i] == '*'){
                return false;
            }
        }
        for(int i = 0; i < e.length; i++){
            if(e[i] == '*' && (i == 0 || e[i - 1] == '*')){
                return false;
            }
        }
        return true;
    }

    //方法三：动态规划
    private static boolean processDP(char[] s, char[] e){
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        //dp[slen][elen-1]必为false，所以就不考虑，正常情况需要考虑elen和elen-1，因为前面的dp[][j]依赖于dp[][j+2]
        for(int j = elen - 2; j > -1; j -= 2){
            if(e[j] != '*' && e[j + 1] == '*'){
                dp[slen][j] = true;
            }else{
                break;
            }
        }
        //正常情况下，dp[..][elen-1]每个都需要求，但是在i<slen-1 && j == elen-1时，
        //e的长度为1，s长度>1,所以必不可能匹配
        if(slen > 0 && elen > 0){
            if((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])){
                dp[slen - 1][elen - 1] = true;
            }
        }
        for(int i = s.length - 1; i > -1; i--){
            for(int j = e.length - 2; j > -1; j--){
                if(e[j + 1] != '*'){
                    dp[i][j] = ((s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1]);
                }else{
                    int si = i;
                    while(si != s.length && (s[si] == e[j] || e[j] == '.')){
                        if(dp[si + 1][j + 2]){
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if(dp[i][j] != true){
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    @Deprecated
    // 从后往前可能不太适应，所以舍弃了，看方法五
    //方法四：对应于方法一  dp[i][j]表示<i, len1 -1>与<j, len2 - 1>的匹配程度
    private static boolean isMatchDP(String s, String e) {
        boolean[][] dp = new boolean[s.length() + 1][e.length() + 1];
        dp[s.length()][e.length()] = true;

        //对于i，j起点与终点的选取要记住.i = text.length()是表示匹配串为null时也要进行判断
        //j = pattern.length() - 1;表示模式串末尾
        for (int i = s.length(); i >= 0; i--){
            for (int j = e.length() - 1; j >= 0; j--){
                boolean first_match = (i < s.length() &&
                        (e.charAt(j) == s.charAt(i) ||
                                e.charAt(j) == '.'));
                if (j + 1 < e.length() && e.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || (first_match && dp[i+1][j]);
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * @see 通配符匹配#isMatch(String, String)  两个算法很像
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatchDp5(String s, String p) {
        if (s == null || p == null || p.length() == 0) {
            return false;
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0]  = true;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                //下面的初始化可以去掉，matches()的 i == 0的判断处理已经包含了这种情况
//                if (i == 0) {
//                    if (j >= 2 && p.charAt(j - 1) == '*') {
//                        dp[i][j] = dp[i][j - 2];
//                    }
//                    continue;
//                }

//                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else if (p.charAt(j - 1) == '*'){
//                    // 精髓在这，*可以表示0个 所以先看dp[i][j - 2];
//                    // * 也可以复制前一个, 所以让p的前一个和s的当前字符比较, 匹配成功的话就可以
//                    // 把i往左推,因为*可以表示多个;
//                    dp[i][j] = dp[i][j - 2];
//                    if (matches(s, p, i, j - 1)) {
//                        dp[i][j] = dp[i][j] || dp[i - 1][j];
//                    }
//
//                }
                if (j >= 2 && p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (matches(s, p, i, j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }

        }
        return dp[s.length()][p.length()];
    }

    /**
     * 对比s的第i个字符与 p的第j个字符是否一致
     * 注意: i,j 不是单纯的下标
     * @param s
     * @param p
     * @param i
     * @param j
     * @return
     */
    private static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
