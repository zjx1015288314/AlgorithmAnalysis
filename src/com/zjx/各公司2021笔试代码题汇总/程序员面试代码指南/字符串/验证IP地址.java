package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

/**
 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址

 IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。

 IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,
 2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，
 字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址
 (即，忽略 0 开头，忽略大小写)。

 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如，
 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的，因为02001是5位。

 说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。

 数据范围：字符串长度满足5≤n≤50
 进阶：空间复杂度O(n)，时间复杂度O(n)

 https://www.nowcoder.com/practice/55fb3c68d08d46119f76ae2df7566880?tpId=295&tqId=44664&ru=%2Fexam%2Foj&qru=%2Fta%2Fformat-top101%2Fquestion-ranking&sourceUrl=%2Fexam%2Foj
 */
public class 验证IP地址 {

    public static void main(String[] args) {
        String solve = solve("2001:0db8:85a3:0000:0:8A2E:0370:7334");
        System.out.println(solve);
    }

    public static String solve (String IP) {
        // write code here
        String res = null;
        if (IP == null || IP.length() == 0) {
            return res;
        }
        if (IP.contains(".")) {
            res = ipv4(IP) ? "IPv4" : "Neither";
        } else if (IP.contains(":")) {
            res = ipv6(IP) ? "IPv6" : "Neither";
        }
        return res;
    }

    private static boolean ipv4(String IP) {
        String[] addrs = IP.split("\\.");
        if (addrs.length != 4) {
            return false;
        }

        for (String addr : addrs) {
            if (addr.isEmpty()) {
                return false;
            }
            if (!isDigit(addr) || (addr.startsWith("0") && addr.length() > 1)) {
                return false;
            }

            int i = Integer.parseInt(addr);
            if (i < 0 || i > 255) {
                return false;
            }
        }
        return true;
    }

    private static boolean ipv6(String IP) {
        // 如果 n > 0，则模式将被最多应用 n - 1 次，数组的长度将不会大于 n，而且数组的最后一项将包含所有超出最后匹配的定界符的输入。
        // 如果 n < 0，那么模式将被应用尽可能多的次数，而且数组可以是任何长度。
        // 如果 n = 0，那么模式将被应用尽可能多的次数，数组可以是任何长度，并且结尾空字符串将被丢弃。
        // 以防这样的情况 2001:0db8:85a3:0000:0000:8a2e:0370:7334:

        String[] addrs = IP.split(":", -1);
        if (addrs.length != 8) {
            return false;
        }

        for (String addr : addrs) {
            if (addr.length() != 1 && addr.length() != 4) {
                return false;
            }
            if ("0000".equals(addr)) {
                return false;
            }
            for (int j = 0; j < addr.length(); j++) {
                //不能出现a-fA-F以外的大小写字符
                char c = addr.charAt(j);
                boolean expr = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' &&
                        c <= 'F') ;
                if (!expr) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

}
