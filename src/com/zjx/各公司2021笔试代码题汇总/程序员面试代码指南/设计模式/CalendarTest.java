package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.设计模式;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        // 获取一个 Calendar 实例
        Calendar calendar = Calendar.getInstance();

        // 获取当前年、月、日、小时、分钟和秒
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 月份从0开始，需要加1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        // 打印当前时间
        System.out.println("当前时间: " + year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒");
    }

}
