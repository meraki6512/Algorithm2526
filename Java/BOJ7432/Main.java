package Algorithm.Algorithm25.Java.BOJ7432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 다시 풀기
 */
public class Main {

    private static StringBuilder sb = new StringBuilder();

    private static class TrieNode {

        HashMap<String, TrieNode> child = new HashMap<>();

        void insert(String[] arr) {
            TrieNode node = this;
            for (String str : arr) {
                node = node.child.computeIfAbsent(str, k -> new TrieNode());
            }
        }

        void print(TrieNode cur, int depth) {
            TrieNode node = cur;
            if (node.child != null) {
                List<String> list = new ArrayList<>(node.child.keySet());
                Collections.sort(list);

                for (String str : list) {
                    sb.append(" ".repeat(depth)).append(str).append("\n");
                    print(node.child.get(str), depth + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TrieNode trie = new TrieNode();

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("\\\\");
            trie.insert(arr);
        }

        trie.print(trie, 0);
        System.out.println(sb);
    }
}
