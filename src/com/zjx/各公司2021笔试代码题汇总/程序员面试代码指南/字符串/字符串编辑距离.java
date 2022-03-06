package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

/**
 * 字符串的编辑距离，又称为Levenshtein距离，是指利用字符操作，
 * 把字符串A转换成字符串B所需要的最少操作数。其中，字符操作包括：
 *  删除一个字符
 *  插入一个字符
 *  修改一个字符
 *  例如对于字符串"if"和"iff"，可以通过插入一个'f'或者删除一个'f'来达到目的。
 *  不难分析出，两个字符串的编辑距离肯定不超过它们的最大长度
 *  与此题对应的是编辑代价
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class 字符串编辑距离 {
    public static void main(String[] args) {
        System.out.println(editDistance("abc","acde"));
    }
    public static int editDistance(String str1, String str2){
        int lenStr1=str1.length();
        int lenStr2=str2.length();
        int[][] edit=new int[lenStr1+1][lenStr2+1];
        for(int i=1;i<=lenStr1;i++){
            edit[i][0]=i;
        }
        for(int j=1;j<=lenStr2;j++){
            edit[0][j]=j;
        }
        for(int i=1;i<=lenStr1;i++){
            for(int j=1;j<=lenStr2;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    edit[i][j]=edit[i-1][j-1];
                }else{
                    edit[i][j]=Math.min(edit[i-1][j-1], Math.min(edit[i][j-1],edit[i-1][j]))+1;
                }
            }
        }
        return edit[lenStr1][lenStr2];
    }
}
