package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.删除总结;


import java.util.Arrays;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，
 * 其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * 提示：
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC1047删除字符串中的所有相邻重复项 {
    /**
     * 消消乐回退
     * @ref{LC1209删除字符串中的所有相邻重复项II.removeDuplicates2}
     */
    public String removeDuplicates(String S) {
        if(S == null || S.isEmpty()) return S;
        int idx = 0; //写一个待写的位置
        char[] ss = S.toCharArray();
        for (int i = 0; i < ss.length; ++i, ++idx) {
            ss[idx] = ss[i];
            if (idx > 0 && ss[idx] == ss[idx - 1]) {  //idx == -1不能省略  因为idx--可能一直减小至0
                idx -= 2; //回退
            }
        }
        return String.valueOf(Arrays.copyOfRange(ss, 0, idx));
    }

    /**
     * 栈思想
     * @param S
     * @return
     */
    public String removeDuplicates1(String S) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for (char character : S.toCharArray()) {
            if (sbLength != 0 && character == sb.charAt(sbLength - 1)) {
                sb.deleteCharAt(sbLength - 1);
                sbLength--;
            }else {
                sb.append(character);
                sbLength++;
            }
        }
        return sb.toString();
    }
}
