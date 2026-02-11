// package Algorithm.Algorithm25.Java.BOJ4151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Opt: O(N * L): Reverse Trie
public class Main {

    static class Node {
        Node[] child = new Node[26];
        boolean isEnd;

        void insert(String str) {
            Node cur = this;
            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';
                if (cur.child[idx] == null) cur.child[idx] = new Node();
                cur = cur.child[idx];
            }
            cur.isEnd = true;
        }
    }

    static Node rootForward = new Node();
    static Node rootBackward = new Node(); // Reversed word Trie
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            list.add(str);
            rootForward.insert(str);
            rootBackward.insert(new StringBuilder(str).reverse().toString());
        }

        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            if (isCompound(word)) {
                sb.append(word).append("\n");
            }
        }
        System.out.println(sb);
    }

    static boolean isCompound(String word) {
        int len = word.length();
        boolean[] validPrefixEnd = new boolean[len];

        // prefix
        Node cur = rootForward;
        for (int i = 0; i < len - 1; i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.child[idx] == null) break;
            cur = cur.child[idx];

            // i에서 끝나는 단어가 있다
            if (cur.isEnd) validPrefixEnd[i] = true;
        }

        // backward. suffix
        cur = rootBackward;
        for (int i = len - 1; i > 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (cur.child[idx] == null) break;
            cur = cur.child[idx];
            
            // 뒤로 읽어 i에서 끝난다면, i-1에서 끝나는 단어가 있는지 확인
            if (cur.isEnd && validPrefixEnd[i-1]) return true;
        }

        return false;
    }
}

/**
 * // O(N * L^2)
public class Main {

    static class Node {
        Node[] child = new Node[26];
        boolean isEnd;

        void insert(String str) {
            Node cur = this;
            for (int i = 0; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';
                if (cur.child[idx] == null) cur.child[idx] = new Node();
                cur = cur.child[idx];
            }
            cur.isEnd = true;
        }

        // check suffix
        boolean containsFrom(String str, int startIdx) {
            Node cur = this;
            for (int i = startIdx; i < str.length(); i++) {
                int idx = str.charAt(i) - 'a';
                if (cur.child[idx] == null) return false;
                cur = cur.child[idx];
            }
            return cur.isEnd;
        }
    }

    static List<String> list = new ArrayList<>();
    static Node root = new Node();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            list.add(str);
            root.insert(str);
        }

        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            if (isCompound(word)) {
                sb.append(word).append("\n");
            }
        }

        System.out.print(sb);
    }

    static boolean isCompound(String word) {
        Node cur = root;
        for (int i = 0; i < word.length()-1; i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.child[idx] == null) return false;

            cur = cur.child[idx];
            
            // end at i && begin with i+1
            if (cur.isEnd && root.containsFrom(word, i+1)) {
                return true;
            }
        }
        return false;
    }
}
 */

// or Using HashSet & substring() would work.