package Algorithm.Algorithm25.Java.BOJ5670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        int N, cnt;
        String input;
        String[] words;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while ((input = br.readLine()) != null && ! input.isEmpty()) {
            N = Integer.parseInt(input);
            Node root = new Node();

            words = new String[N];
            for (int i = 0; i < N; i++) {
                words[i] = br.readLine();
                root.insert(words[i]);
            }

            cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += root.getPressCnt(words[i]);
            }
            sb.append(String.format("%.2f\n", (double)cnt / N));

        }

        System.out.println(sb);
    }

    private static class Node {
        HashMap<Character, Node> children;
        boolean isEnd;

        public Node() {
            children = new HashMap<>();
        }

        public void insert(String word) {
            Node cur = this;

            for (char c : word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node());
                cur = cur.children.get(c);
            }

            cur.isEnd = true;
        }

        /**
         * child != distinct || child == isEnd
         * : have to press
         */
        public int getPressCnt(String word) {
            Node cur = this.children.get(word.charAt(0));
            int cnt = 1;

            for (int i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children.size() > 1 || cur.isEnd) {
                    cnt++;
                }
                cur = cur.children.get(c);
            }

            return cnt;
        }
    }

}
