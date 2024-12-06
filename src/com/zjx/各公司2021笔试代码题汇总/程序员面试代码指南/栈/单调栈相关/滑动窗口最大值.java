package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.栈.单调栈相关;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
 *
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * 窗口大于数组长度或窗口长度为0的时候，返回空。
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 *
 * @link https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 *       https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 *  思路：如果要求一段范围的最大值很简单，但当这个范围是动态变化的时候，可能不只需要保存最大值，
 *  而且需要保存次大值...，这就需要一个"单调栈/队列",当栈(队列)的头部或者尾部移除时，我们可以快
 *  速得到剩余元素的最大值,至于是选栈还是队列，则和原始元素的进出方式相关，毕竟单调栈/队列只是辅助空间
 *
 */
public class 滑动窗口最大值 {
    public ArrayList<Integer> maxInWindows(int [] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0 || k == 0) return res;

        Deque<Integer> deque = new LinkedList<>();  //这里的队列用来存索引如果存元素的话也可以

        for(int end = 0; end < nums.length; end++) {
            while(!deque.isEmpty() && nums[deque.peekLast()] <= nums[end]) {
                deque.pollLast();
            }
            deque.addLast(end);

            if(end - k == deque.peekFirst()) {   //出队时机
                deque.pollFirst();
            }

            if(end >= k - 1) {   //入队时机
                res.add(nums[deque.peekFirst()]);
            }
        }
        return res;
    }
}
