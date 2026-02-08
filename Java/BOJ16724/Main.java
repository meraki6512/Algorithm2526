package Algorithm.Algorithm25.Java.BOJ16724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 16724. 피리부는 사나이 (1s, 256MB)
 */
public class Main {

    private static int N, M, ans = 0;
    private static int[] map;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N*M];
        parent = new int[N*M];
        for (int i = 0; i < N*M; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = s.charAt(j);
                if (ch == 'D') map[i*M+j] = +M;
                else if (ch == 'U') map[i*M+j] = -M;
                else if (ch == 'L') map[i*M+j] = -1;
                else if (ch == 'R') map[i*M+j] = +1;
            }
        }

        for (int i = 0; i < N*M; i++) {
            int nxt = i + map[i];
            if (inRange(nxt)) union(i, nxt);
        }

        for (int i = 0; i < N*M; i++) {
            if (parent[i] == i) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static boolean inRange(int x) {
        return x >= 0 && x < N*M;
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        // parent = larger one
        if (x < y) parent[x] = y;
        else parent[y] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
