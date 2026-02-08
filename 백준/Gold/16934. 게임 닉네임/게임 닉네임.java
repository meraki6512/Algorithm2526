// package Algorithm.Algorithm25.Java.BOJ16934;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// refactored
public class Main {
    static class Node {
        Node[] children = new Node[26]; // lower-case only ... -> cutoff hashing, memory overheads
        int cnt = 0;
    }

    static Node root = new Node();

    static String insertAndGetAlias(String str) {
        Node cur = root;
        String alias = null;

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            // new branch
            if (cur.children[idx] == null) {
                cur.children[idx] = new Node();
                if (alias == null) {
                    alias = str.substring(0, i+1);
                }
            }

            cur = cur.children[idx]; //mv
        }

        cur.cnt++;
        if (alias != null) return alias;    // new branch
        if (cur.cnt == 1) return str;       // first time fully used
        return str + cur.cnt;               // same nickname
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(insertAndGetAlias(br.readLine())).append("\n");
        }
        System.out.println(sb);
    }
}

/**
public class Main {
    private static Trie root = new Trie();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            sb.append(root.findNicknameAndInsert(line)).append("\n");
        }
        System.out.println(sb);
    }

    private static class Trie {
        HashMap<Character, Trie> children = new HashMap<Character, Trie>();
        int endCnt = 0;

        String findNicknameAndInsert(String str) {
            Trie cur = root;
            int df = str.length()-1;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (df == str.length()-1 && !cur.children.containsKey(c)) {
                    df = i;
                }
                cur = cur.children.computeIfAbsent(c, k -> new Trie());
            }

            String pre = str.substring(0, df+1);
            String suf = ++cur.endCnt > 1 ? cur.endCnt+"" : "";
            return pre + suf;
        }
    }
}
 */
