package com.zjx.各公司2021笔试代码题汇总.华为.面试;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test2 {

    /**
     * abcdee -> abcde   abbae -> bae   aabcc -> abc
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String testStr = "abbae";
        System.out.println(longestSubStr(testStr));

        String testStr1 = "abcdee";
        System.out.println(longestSubStr(testStr1));

        String testStr2 = "aaaaaa";
        System.out.println(longestSubStr(testStr2));

        String testStr3 = "aabcc";
        System.out.println(longestSubStr(testStr3));
    }

    private static String longestSubStr(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        int len = 0;
        int start = 0;
        int startIdx = 0;
        char[] charArray = str.toCharArray();
        Map<Character, Integer> char2Idx = new HashMap<>();
        for (int i = 0; i < charArray.length; i++) {
            if (char2Idx.containsKey(charArray[i])) {
                startIdx = Math.max(startIdx, char2Idx.get(charArray[i]) + 1);
            }
            if (i - startIdx + 1 > len) {
                len = i - startIdx + 1;
                start = startIdx;
            }
            char2Idx.put(charArray[i], i);
        }

        return String.valueOf(charArray, start, len);
    }


}
