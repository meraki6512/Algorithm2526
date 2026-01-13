// package Algorithm.Algorithm25.Java.BOJ3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());    // 100
        StringBuilder sb = new StringBuilder();
        int n, m, a, b;
        int[] t, rank;
        List<Integer>[] graph;
        String input;

        while (T-- > 0) {

            // in

            n = Integer.parseInt(br.readLine());    // 500
            t = new int[n+1];
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int i = 1; i <= n; i++) t[i] = Integer.parseInt(st.nextToken());

            m = Integer.parseInt(br.readLine());    // 25_000
            if (m == 0) {
                sb.append(input).append("\n");
                continue;
            }

            rank = new int[n+1];
            graph = new List[n+1];
            for (int i = 1; i <= n; i++) graph[i] = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    graph[t[i]].add(t[j]);
                    rank[t[j]]++;
                }
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (graph[a].contains(b)) {
                    // a->b --> b->a
                    graph[a].remove(Integer.valueOf(b));
                    graph[b].add(a);
                    rank[b]--;
                    rank[a]++;
                }
                else if (graph[b].contains(a)) {
                    // b->a --> a->b
                    graph[b].remove(Integer.valueOf(a));
                    graph[a].add(b);
                    rank[a]--;
                    rank[b]++;
                }
            }

            // sort
            sb.append(sort(rank, graph, n)).append("\n");
        }

        System.out.println(sb);
    }

    private static String sort(int[] rank, List<Integer>[] graph, int n) {
        final String impsbl = "IMPOSSIBLE";
        StringBuilder res = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (rank[i] == 0) q.offer(i);
        }

        int check = n * (n + 1) / 2;

        while (!q.isEmpty()) {
            int u = q.poll();
            check -= u;
            res.append(u).append(" ");

            for (int v : graph[u]) {
                if (--rank[v] == 0) q.offer(v);
            }
        }

        if (check > 0) return impsbl;
        else return res.toString();
    }
}
