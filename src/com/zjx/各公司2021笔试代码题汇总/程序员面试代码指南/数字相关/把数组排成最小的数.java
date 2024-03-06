package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数字相关;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 输入一个正整数数组numbers，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组[3，32，321]，则打印出这三个数字能排成的最小数字为321323。
 *
 * 数据范围:
 * 0<=len(numbers)<=100
 *
 * @link https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 * 思路：
 * 比较两个字符串s1, s2大小的时候，先将它们拼接起来，比较s1+s2,和s2+s1那个大，如果s1+s2大，
 * 那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面
 */
public class 把数组排成最小的数 {
    public String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0)return "";
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                int sum1 = Integer.valueOf(numbers[i]+""+numbers[j]);
                int sum2 = Integer.valueOf(numbers[j]+""+numbers[i]);
                if(sum1 > sum2){
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
        }
        return sb.toString();
    }

    /**
     * 比较简洁的写法
     * @param numbers
     * @return
     */
    public String PrintMinNumber1(int [] numbers) {
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i : numbers){
            arrayList.add( i + "" );
        }
        //想让o1排在前面的话，就使得compareTo返回的结果排在前面
        arrayList.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder stringBuilder2 = new StringBuilder();
        for(String s : arrayList){
            stringBuilder2.append(s);
        }
        return stringBuilder2.toString();
    }
}
