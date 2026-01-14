// package Algorithm.Algorithm25.Java.BOJ2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, M, X, Y, K;
        int[][] partsNum;
        int[] rank;
        List<Integer>[] graph;
        Queue<Integer> basicParts;
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    // 100  : fin num
        graph = new List[N+1];
        for (int i = 0; i <= N; i++) graph[i] = new LinkedList<>();
        partsNum = new int[N+1][N+1];
        rank = new int[N+1];
        basicParts = new LinkedList<>();

        M = Integer.parseInt(br.readLine());    // 100  : rel
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            // Y -> X : K
            graph[Y].add(X);
            rank[X]++;
            partsNum[Y][X] = K;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (rank[i] == 0) {
                q.offer(i);
                basicParts.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int nxt : graph[cur]) {
                // [1][6] += [1][5] * [5][6]
                for (int i = 1; i <= N; i++) partsNum[i][nxt] += partsNum[i][cur] * partsNum[cur][nxt];
                if (--rank[nxt] == 0) q.offer(nxt);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!basicParts.isEmpty()) {
            int p = basicParts.poll();
            sb.append(p).append(" ").append(partsNum[p][N]).append("\n");
        }
        System.out.println(sb.toString());
    }
}

//   1 2 3 4 5 6 7
// 1 _ _ _ _ 2 _ _
// 2 _ _ _ _ 2 _ _
// 3 _ _ _ _ _ 3 _
// 4 _ _ _ _ _ 4 5
// 5 _ _ _ _ _ 2 2
// 6 _ _ _ _ _ _ 3
// 7 _ _ _ _ _ _ _