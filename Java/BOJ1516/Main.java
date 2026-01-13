package Algorithm.Algorithm25.Java.BOJ1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, t;
        int[] rank, time;
        List<Integer>[] graph;
        StringTokenizer st;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rank = new int[N+1];
        time = new int[N+1];
        graph = new List[N+1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            t = Integer.parseInt(st.nextToken());
            while (t != -1) {
                // t -> i
                rank[i]++;
                graph[t].add(i);
                t = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[N+1];
        for (int i = 1; i <= N; i++) {
            ans[i] = time[i];
            if (rank[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            // 기존 계산된 시간 vs 현재 건물 완료 시간 + 다음 건물 짓는 시간
            for (int n : graph[cur]) {
                ans[n] = Math.max(ans[n], ans[cur] + time[n]);

                if (--rank[n] == 0) q.add(n);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(ans[i]).append("\n");
        System.out.println(sb);
    }
}
