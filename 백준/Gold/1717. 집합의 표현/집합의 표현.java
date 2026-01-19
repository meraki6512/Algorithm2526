// package Algorithm.Algorithm25.Java.BOJ1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] root, rank;
    public static void main(String[] args) throws IOException {
        int n, m, e, a, b;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 1_000_000
        m = Integer.parseInt(st.nextToken());   // 100_000
        root = new int[n+1]; // 0 ~ n
        rank = new int[n+1];
        for (int i = 1; i <= n; i++) root[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            e = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (e == 0) { // union a b
                union(a, b);
            }
            else if (e == 1) {  // find a == b ?;
                sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static int find(int x) {
        if (x == root[x]) return x;
        else return root[x] = find(root[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (rank[a] < rank[b]) root[a] = b;
        else if (rank[a] > rank[b]) root[b] = a;
        else {
            rank[a]++;
            root[b] = a;
        }
    }
}
