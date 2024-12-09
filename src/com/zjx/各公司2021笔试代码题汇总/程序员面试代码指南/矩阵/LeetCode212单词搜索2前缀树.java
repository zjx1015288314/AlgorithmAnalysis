package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @see LeetCode212单词搜索2
 * 链接： https://leetcode.cn/problems/word-search-ii/description/
 */
public class LeetCode212单词搜索2前缀树 {
    static class TrieNode {
        int path;   //表示多少个单词共用这个节点
        int end;  //表示有多少个单词以这个节点为结尾
        HashMap<Character, TrieNode> children; //哈希表，key代表当前字符，value代表当前字符路径指向的节点
        String word;   //表示末尾节点存储的字符串
        public TrieNode() {
            path = 0;
            end = 0;
            children = new HashMap<>();
        }

        public void insert(String word) {
            TrieNode cur = this;
            for (char c : word.toCharArray()) {
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode());
                }
                cur = cur.children.get(c);
            }
            cur.word = word;
        }
    }
    static int[][] directs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int row = 0;
    static int col = 0;
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        row = Integer.parseInt(str[0]);
        col = Integer.parseInt(str[1]);
        char[][] matrix = new char[row][col];
        String s = null;
        for (int i = 0; i < row; i++) {
            s = br.readLine();
            for (int j = 0; j < col; j++) {
                matrix[i][j] = s.charAt(j);
            }
        }
        str = br.readLine().split(" ");
        System.out.println(findWords(matrix, str));
    }

    /**
     * 时间复杂度 O(m*n*3^(l - 1)), l为words中最长单词的长度
     * 空间复杂度：O(k×l) k 是 words 的长度
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words){
        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.insert(word);
        }
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                backtracking(board, row, col, root);
            }
        }
        return list;
    }

    private static void backtracking(char[][] board,int i, int j, TrieNode parent) {
        if (!parent.children.containsKey(board[i][j])) {
            return;
        }

        Character letter = board[i][j];
        TrieNode currNode = parent.children.get(letter);
        // check if there is any match
        if (currNode.word != null) {
            list.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        board[i][j] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        for (int[] direct : directs) {
            int newRow = i + direct[0];
            int newCol = j + direct[1];
            if (newRow < 0 || newRow >= board.length || newCol < 0
                    || newCol >= board[0].length) {
                continue;
            }
            backtracking(board,newRow, newCol, currNode);
        }

        // End of EXPLORATION, restore the original letter in the board.
        board[i][j] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}
