package com.zjx.ConcurrentProgram;

import java.util.Calendar;
import java.util.Date;

public class ObjectCloneTest1 {

    static final int N = 100000;

    public static void main(String[] args) {
        final Date date = new Date();
        {
            final long startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++) {
                Date date2 = (Date) date.clone();
            }
            final long endTime = System.currentTimeMillis();
            System.out.println("clone:" + (endTime - startTime) + "ms");
        }
        {
            final long startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++) {
                final Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                final Date date2 = cal.getTime();
            }

            final long endTime = System.currentTimeMillis();
            System.out.println("Calender.setTime:" + (endTime - startTime) + "ms");

        }

    }

}
