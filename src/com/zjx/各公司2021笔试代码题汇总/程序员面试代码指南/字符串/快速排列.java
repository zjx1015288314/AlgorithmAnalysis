package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.util.Arrays;

/**
 * @author zjx
 * @date 2026/5/20
 */
public class 快速排列 {

    /**
     *
     *
     * 场次  票价  库存
     *
     * user
     *
     * product (
     *
     *   id
     *   name  idx
     *   product_type  idx
     *   create_time update_time
     *
     * )
     *
     * product_info create_time update_time
     *
     * ticket/ticket_num {
     *      product_id idx
     *      create_time update_time
     * }
     *
     * order {
     *     product_id  idx,
     *     user_id   idx,
     *     create_time update_time
     * }
     *
     * order_log {
     *     create_time update_time
     * }
     *
     * explain mysql idx + rows + file sort
     *  1. 太小
     *  2. 最左
     *  3. num = amount + 1  date()
     *  4. varchar   = 1
     *  5. is not null  not in ()
     *
     *  redis : str  ticker_xxxxx : 10000
     *      hash : {
     *          id:
     *          num:
     *          info:
     *      }
     *
     *  ticker num: 100000  idNo 1
     *  redis:
     *  str: 100000
     *  zset : value idNo  score timestamp
     *  lua : redis.call(str) > 100000 + zset idNo
     *
     *
     * order  order_status : ordered / payed / canceled
     *
     * select * from order where now() > ordered_time + xxx and order_status = ordered and deleted = 0 order by id limit 1000;
     * update order set order_status = canceled, update_time = now() where user_id = xx and order_id = xxx and now() > ordered_time + xxx and order_status = ordered;
     * update order set order_status = canceled, update_time = now() where user_id = xx and order_status = ordered and order_id = xxx;
     *
     * ticker_num + redis_num : delete redis_num 、update mysql num 、 delete redis_num
     *
     * 场次  票价+库存  订单
     * DTO、Service
     * productMaper、Service；  TickerMapper  ； OrderMapper OrderService
     *
     * product/info -> ProductController -> productService + TickerService :
     *  {
     *
     *     if (redis.has()) {
     *
     *     }
     *     mapper -> fill redis
     *     return ;
     *  }
     *
     *
     *
     * order -> product_id  OrderController -> productService + TickerService + OrderService
     * {
     *     productService
     *     if (redis.has()) {
     *         redis+ lua : ticker num 、 order user
                MQ: 延迟消息
                MQ: redis num -> mysql num
     *     }
     * }
     *
     * Schedule:    1、batchUpdateGt30Order
     *              2、核对 redis mysql
     *
     *
     * payed ->
     *
     *
     *
     */
    /**
     * 快速排列 基于递归的形式
     */
    public static void main(String[] args) {
        int[] arrs = {4,2,1,6,7,8};
        sort(arrs, 0, arrs.length-1);
        System.out.println(Arrays.toString(arrs));
    }

    private static void sort(int[] arrs, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        sort(arrs, left, mid);
        sort(arrs, mid, right);
        doSort(arrs, left, right);
    }

    private static void doSort(int[] arrs, int left, int right) {
        int pivotVal = arrs[left];
        for (int i = left; i <= right; i++) {
            if (arrs[i] < pivotVal) {
                int tmp = arrs[i];
                arrs[i] = arrs[left];
                arrs[left] = tmp;
            }
        }
    }

}
