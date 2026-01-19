package Algorithm.Algorithm25.Java.BOJ2611;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2611. 자동차 경주
 * 위상정렬. dp.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        List<Edge>[] graph;
        int N, M, p, q, r, point = 0;
        int[] rank, dist, parent;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());    // points. 1~N      // 1000
        M = Integer.parseInt(br.readLine());    // edges. p->q: r   // r: 100, p!=q
        rank = new int[N+1];
        dist = new int[N+1];
        parent = new int[N+1];
        graph = new List[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            graph[p].add(new Edge(q, r));
            if (q != 1) rank[q]++;
        }

        Queue<Integer> que = new LinkedList<>();
        que.offer(1);

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (Edge nxt : graph[cur]) {
                // updatable
                if (dist[nxt.x] < dist[cur] + nxt.w) {
                    dist[nxt.x] = dist[cur] + nxt.w;
                    parent[nxt.x] = cur;
                }

                // ! goal
                if (nxt.x != 1) {
                    if (--rank[nxt.x] == 0) {
                        que.offer(nxt.x);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[1]).append("\n");

        // track
        Stack<Integer> stk = new Stack<>();
        stk.push(1);
        int cur = parent[1];

        while (cur != 1) {
            stk.push(cur);
            cur = parent[cur];
        }
        stk.push(1);

        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static class Edge implements Comparable<Edge> {
        int x, w;
        public Edge(int x, int w) {
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return o.w - this.w;
        }
    }
}

/**
 * 잘못된 방법 ... 다익스트라
 *
 *
 PriorityQueue<Edge> pq = new PriorityQueue<>();
 int[] dist, parent, ans;
 * ...
 *
 * pq.offer(new Edge(1, 0));
 *
 *         while (!pq.isEmpty()) {
 *             Edge cur = pq.poll();
 *             if (cur.w < dist[cur.x]) continue;
 *
 *             for (Edge nxt : graph[cur.x]) {
 *                 if (nxt.x == 1) {
 *                     if (point < cur.w + nxt.w) {
 *                         point = cur.w + nxt.w;
 *                         parent[1] = cur.x;
 *                     }
 *                     continue;
 *                 }
 *                 if (dist[nxt.x] < dist[cur.x] + nxt.w) {
 *                     dist[nxt.x] = dist[cur.x] + nxt.w;
 *                     pq.offer(new Edge(nxt.x, dist[nxt.x]));
 *                     parent[nxt.x] = cur.x;
 *                 }
 *             }
 *         }
 *
 *         int idx = parent[1];
 *         ans[idx] = 1;
 *         while (idx != 1) {
 *             ans[parent[idx]] = idx;
 *             idx = parent[idx];
 *         }
 *
 *         StringBuilder sb = new StringBuilder();
 *         sb.append(point).append("\n");
 *         idx = 1;
 *         while (ans[idx] != 1) {
 *             sb.append(idx).append(" ");
 *             idx = ans[idx];
 *         }
 *         sb.append(idx).append(" 1\n");
 *         System.out.println(sb.toString());
 */
