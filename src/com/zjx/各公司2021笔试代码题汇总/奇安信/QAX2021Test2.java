package com.zjx.各公司2021笔试代码题汇总.奇安信;


/**
 * [3,2,4]
 * 5
 *
 * [4,1,3,3,3]
 * 7
 *
 *
 */
public class QAX2021Test2 {
    public int house (int[] person) {
        // write code here
        if(person == null || person.length == 0) return 0;
        if(person.length == 1) return 1;
        int[] res = new int[person.length];
        res[0] = 1;
        for(int i = 1; i < person.length; i++){
            if(person[i] > person[i - 1]){
                res[i] = res[i - 1] + 1;
            }else if(person[i] == person[i - 1]){
                res[i] = 1;
            }else{
                res[i] = 1;
                if(res[i - 1] <= res[i]){
                    res[i - 1]++;
                    int idx = i - 1;
                    while(idx > 0 && person[idx] < person[idx - 1]){
                        res[idx - 1] = res[idx] + 1;
                        idx--;
                    }
                }

            }
        }
        int num = 0;
        for(int i : res){
            num += i;
        }
        return num;
    }
}
