package Algorithm.Algorithm25.Java.BOJ9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static Node root = new Node();
    private static char[][] map = new char[4][4];
    private static boolean[][] visited = new boolean[4][4];

    private static final int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    private static final int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};
    private static final int[] scoreTable = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    private static int maxScore;
    private static String maxWord;
    private static int foundCount;
    private static int boardIdx;    // Trie Node ... hit

    private static class Node {
        Node[] child = new Node[26];    // Capital letter: 26
        boolean isEnd;
        String word;
        int hit = -1;   // HashSet ... -> visited

        void insert(String word) {
            Node cur = this;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'A';
                if (cur.child[idx] == null) cur.child[idx] = new Node();
                cur = cur.child[idx];
            }
            cur.isEnd = true;
            cur.word = word;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Dictionary
        int w = Integer.parseInt(br.readLine());    // 300_000
        for (int i = 0; i < w; i++) {
            root.insert(br.readLine());
        }
        br.readLine();

        StringBuilder sb = new StringBuilder();
        int b = Integer.parseInt(br.readLine());    // 30
        // b개의 Boggle 보드 (4x4)
        for (boardIdx = 0; boardIdx < b; boardIdx++) {
            for (int i = 0; i < 4; i ++) {
                String str = br.readLine();
                for (int j = 0; j < 4; j ++) {
                    map[i][j] = str.charAt(j);
                }
            }
            // last board -> no \n
            if (boardIdx < b-1) br.readLine();

            maxScore = 0;
            maxWord = "";
            foundCount = 0;

            // boggle
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Node cur = root.child[map[i][j] - 'A'];
                    if (cur != null) find(i, j, cur);
                }
            }

            // Result
            sb.append(maxScore).append(" ").append(maxWord).append(" ").append(foundCount).append("\n");
        }

        System.out.print(sb);
    }

    private static void find(int x, int y, Node cur) {
        visited[x][y] = true;

        // first time found in this board
        if (cur.isEnd && cur.hit != boardIdx) {
            cur.hit = boardIdx; // visited -> HashSet
            foundCount++;
            maxScore += scoreTable[cur.word.length()];
            maxWord = (cur.word.length() > maxWord.length())
                    || ((cur.word.length() == maxWord.length()) && (cur.word.compareTo(maxWord) < 0))
                    ? cur.word : maxWord;
        }

        // dfs
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && !visited[nx][ny]) {
                Node nxt = cur.child[map[nx][ny] - 'A'];
                if (nxt != null) find(nx, ny, nxt);
            }
        }

        visited[x][y] = false;
    }
}
