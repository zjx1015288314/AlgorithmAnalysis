package com.itzjx.mmall_test;

/**
 * 某一个大文件被拆成了 N 个小文件，每个小文件编号从 0 至 N-1，相应大小分别记为 S(i)。
 * 给定磁盘空间为 C ，试实现一个函数从 N 个文件中连续选出若干个文件拷贝到磁盘中，使得磁盘剩余空间最小。
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class FindMinSpace {
    public static void main(String[] args) {
        int[] res = findMin(new int[]{1, 2, 7, 4, 5, 8}, 15);
        //int[] res = findNearest(new int[]{1, 2, 7, 4, 5, 8}, 15);  //错误
        for(int i : res){
            System.out.println(i);
        }
    }
    public static int[] findMin(int[] s, int c){
        if (s == null || s.length == 0) return null;
        int i = 0 , j = 0;
        int minValue = Integer.MAX_VALUE;
        int sum = s[0];
        int leftIdx = 0;
        int rightIdx = -1;  //防止s中所有元素都大于的情况下，返回不必要的值
        while (j < s.length){
            if(sum <= c){
                minValue = Math.min(minValue,c - sum);
                if(minValue == c - sum){
                    leftIdx = i;
                    rightIdx = j;
                }
                if(++j < s.length) sum += s[j];
            }else{
                sum -= s[i++];
            }
        }
        int[] res = new int[rightIdx - leftIdx + 1];
        for (int idx = leftIdx; idx <= rightIdx; idx++){
            res[idx - leftIdx] = s[idx];
        }
        return res;
    }

    public static int[] findNearest(int[] s, int target) {
        int head = 0, tail = 0;
        int rH = 0, rT = 0;
        int res = s[0];
        int temp = 0;
        while(head<s.length && tail<s.length) {
            if(temp < target) {
                temp += s[tail];
                tail ++;
            }else if(temp > target){
                temp -= s[head];
                head++;
            }else {
                break;
            }
            if(Math.abs(res-target) >= Math.abs(temp - target)) {
                res = temp;
                rH = head;
                rT = tail;
            }
        }
        int[] ans = new int[rT - rH];
        for(int i=0;i<ans.length;i++) {
            ans[i] = s[rH+i];
        }
        return ans;
    }
}
