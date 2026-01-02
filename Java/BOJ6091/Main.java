package Algorithm.Algorithm25.Java.BOJ6091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/6091">6091. 핑크 플로이드</a>
 * 최단거리 행렬이 주어질 때, 인접 리스트 형태로 원래 트리를 표현할 때,
 * 노드별 연결된 정점 수나 정점 번호를 얻어낼 때도 MST 알고리즘을 사용하면 유용하다.
 */
public class Main {
    private static int N;
    private static int[] root, rank;
    private static ArrayList<Edge> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = i + 1; j <= N; j++) {
                list.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
            }
        }

        root = new int[N+1];
        rank = new int[N+1];
        for (int i = 1; i <= N; i++) root[i] = i;
        List<Integer>[] res = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) res[i] = new ArrayList<>();

        Collections.sort(list);

        int cnt = 0;
        for (Edge e : list) {
            if (union(e.u, e.v)) {
                res[e.u].add(e.v);
                res[e.v].add(e.u);
                if (++cnt == N-1) break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(res[i].size());
            Collections.sort(res[i]);
            for (int k : res[i]) sb.append(" ").append(k);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        // 큰 쪽을 부모로
        if (rank[u] < rank[v]) root[u] = v;
        else if (rank[u] > rank[v]) root[v] = u;
        else {
            rank[u]++;
            root[v] = u;
        }
        return true;
    }

    private static int find(int x){
        if (x == root[x]) return x;
        return root[x] = find(root[x]);
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, d;
        public Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }
        @Override
        public int compareTo(Edge o) {
            return this.d - o.d;
        }
    }
}
