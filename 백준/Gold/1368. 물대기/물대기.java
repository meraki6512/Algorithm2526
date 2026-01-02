// package Algorithm.Algorithm25.Java.BOJ1368;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1368">1368. 물대기</a>
 * MST. 가상의 0번 노드를 만들어 N+1개의 노드를 모두 연결.
 */
public class Main {

    private static int[] root, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 300
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) edges.add(new Edge(0, i, Integer.parseInt(br.readLine())));

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int c = Integer.parseInt(st.nextToken());
                if (j > i) {
                    edges.add(new Edge(i, j, c));
                }
            }
        }

        root = new int[N+1];
        rank = new int[N+1];
        for (int i = 0; i <= N; i++) {
            root[i] = i;
        }

        Collections.sort(edges);

        int cnt = 0, cost = 0;
        for (Edge e : edges) {
            if (union(e.x, e.y)) {
                cost += e.c;
                if (++cnt == N) break;
            }
        }

        System.out.println(cost);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;

        if (rank[x] > rank[y]) root[y] = x;
        else if (rank[x] < rank[y]) root[x] = y;
        else {
            rank[x]++;
            root[y] = x;
        }
        return true;
    }

    private static int find(int x) {
        if (root[x] == x) return x;
        return root[x] = find(root[x]);
    }

    private static class Edge implements Comparable<Edge> {
        int x, y, c;
        Edge(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
}
