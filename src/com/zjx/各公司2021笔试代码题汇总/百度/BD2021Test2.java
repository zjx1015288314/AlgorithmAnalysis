package com.zjx.各公司2021笔试代码题汇总.百度;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 作者：HeyQiangZi
 * 链接：https://www.nowcoder.com/discuss/498167?type=all&order=time&pos=&page=1&channel=1009&source_id=search_all
 * 来源：牛客网
 *  给定一个数组，选取某几个“5”和“0”组合成可以被90整除的最大的数字
 * 符合条件的第一个非0数是5555555550，9个5,1个0；
 * 于是5的个数必须是9的整数倍，0至少1个；
 * 例如：18个5和1个0，可以写成（5555555550000000000+5555555550），它肯定能被90整除。
 */
public class BD2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] arr = new int[N];

        int numOf0 = 0;
        int numOf5 = 0;

        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] =  Integer.parseInt(str[i]);
            if (arr[i] == 5){
                numOf5++;
            }else{
                numOf0++;
            }
        }
        process(arr,numOf0,numOf5);
    }

    //90%
    public static void process(int[] arr, int num0, int num5){
        StringBuffer sb = new StringBuffer();
        if(num5 <= 0){
            System.out.println("0");
            return;
        }else if(num0 < 1){
            System.out.println("-1");
            return;
        }else{
            while (num5 > 0){
                int count = num5 / 2;
                boolean flag = num5 % 2 == 1;
                if(flag && count % 9 == 4){
                    while (num5 > 0){
                        sb.append("5");
                        num5--;
                    }
                    while (num0 > 0){
                        sb.append("0");
                        num0--;
                    }
                    System.out.println(sb.toString());
                    break;
                }else if (!flag && count % 9 == 0){
                    while (num5 > 0){
                        sb.append("5");
                        num5--;
                    }
                    while (num0 > 0){
                        sb.append("0");
                        num0--;
                    }
                    System.out.println(sb.toString());
                    break;
                }
                num5--;
            }
        }
    }

    /**
     * 5的个数大于等于9
     * @param arr
     * @param num0
     * @param num5
     */
    public static void process1(int[] arr, int num0, int num5){
        StringBuffer sb = new StringBuffer();
        int tmp = num5 / 9; //5的个数是否是9的倍数
        if (num0 < 1){
            System.out.println(-1);
        }else if (tmp == 0){
            System.out.println(0);
        }else{
            for (int i = 0; i < tmp * 9; i++) {
                sb.append(5);
            }
            for (int i = 0; i < num0; i++) {
                sb.append(0);
            }
            System.out.println(sb);
        }
    }
}
