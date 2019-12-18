package com.zjx.DataStructure.Charpter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * p106,编写一个程序以找出通过单个字母的替换可以变成至少15个其他单词的单词.例：单词wine可以变成dine,fine,line,mine,nine,pine
 * vine;wide,wife,wipe,wire;wind,wing,wink,wins.
 */
public class ComputeAdjacentWords {


    /**
     * 输出具有minWords个或者更多个通过1字母替换得到的单词
     * @param adjWords Map中包含以某个单词为键，与该单词仅有一个字母之差的一系列单词为值的Entry
     * @param minWords 匹配搜索的最少单词个数
     */
    public static void printHighChangeables(Map<String,List<String>> adjWords,int minWords){
        for (Map.Entry<String, List<String>> entry :
                adjWords.entrySet()) {
            List<String> words = entry.getValue();
            if (words.size() >= minWords){
                System.out.print(entry.getKey() + " (");
                System.out.print(words.size() + "):");
                for (String w :
                        words) {
                    System.out.print(" " + w);
                }
                System.out.println();
            }
        }
    }

    /**
     *
     * @param word1
     * @param word2
     * @return true if word1 and word2 are the same length and differ in only one character
     */
    private static boolean oneCharOff(String word1,String word2){
        if (word1.length() != word2.length())
            return false;
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                if (++diff > 1)
                    return false;
        }
        return diff == 1;   //排除diff为0的情况
    }

    /**
     * 将输入列表转换为数组查询，通过嵌套for循环找到并更新Map，最后将Map作为返回值
     * 缺点：对于不同长度的两个单词也会做无用的比较;可以将单词但长度分组
     * @param theWords
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWords1(List<String> theWords){
        Map<String,List<String>> adjWords = new TreeMap<>();
        String[] words = new String[theWords.size()];
        theWords.toArray(words);
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (oneCharOff(words[i],words[j])){
                    //这里在找到后对两个单词都做更新
                    update(adjWords,words[i],words[j]);
                    update(adjWords,words[j],words[i]);
                }
            }
        }
        return adjWords;
    }

    private static <T> void update(Map<T,List<String>> m,T key,String value){
        List<String> lst = m.get(key);
        if (lst == null){
            lst = new ArrayList<>();
            m.put(key,lst);
        }
        lst.add(value);
    }

    /**
     * 将单词按长度分组,减少了一些不必要的运算
     * @param theWords
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWords2(List<String> theWords){
        Map<String,List<String>> adjWords = new TreeMap<>();
        Map<Integer,List<String>> wordsByLength = new TreeMap<>(); //键是单词长度,值是该长度所有单词的集合

        //Group the words by length
        for (String w : theWords) {
            update(wordsByLength,w.length(),w);
        }

        //Work on each group separately
        for (List<String> groupsWords : wordsByLength.values()) {
            String[] words = new String[groupsWords.size()];
            groupsWords.toArray(words);
            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    if (oneCharOff(words[i],words[j])){
                        //这里在找到后对两个单词都做更新
                        update(adjWords,words[i],words[j]);
                        update(adjWords,words[j],words[i]);
                    }
                }
            }
        }
        return adjWords;
    }

    /**
     * 这些附加的Map使得算法更快，用空间换取时间，但程序没有利用该Map的关键字保持有序排列的事实，所以引出
     * HashMap不保证有序排列但(查询)操作更快
     * @param theWords
     * @return
     */
    public static Map<String,List<String>> computeAdjacentWords3(List<String> theWords){
        Map<String,List<String>> adjWords = new TreeMap<>();
        Map<Integer,List<String>> wordsByLength = new TreeMap<>();
        //Group the words by length
        for (String w : theWords) {
            update(wordsByLength,w.length(),w);
        }
        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {
            List<String> groupWords = entry.getValue();
            int groupNum = entry.getKey();
            for (int i = 0; i < groupNum; i++) {
                //Remove one character in specified position,computing representative.
                //Words with same representative are adjacent,so first populate a map
                Map<String,List<String>> repToWord = new TreeMap<>();
                for (String str : groupWords) {
                    //subString(beginIndex,endIndex)，左闭右开
                    //subString(beginIndex)，左闭
                    String rep = str.substring(0,i) + str.substring(i+1);
                    update(repToWord,rep,str);
                }

                //then look for repToWord values with more than one string
                for (List<String> wordClique : repToWord.values()) {
                    if (wordClique.size() >= 2)
                        for (String s1 : wordClique)
                            for (String s2 : wordClique)
                                if (s1 != s2)
                                    update(adjWords,s1,s2);


                }
            }
        }
        return adjWords;
    }


}
