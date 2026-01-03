package Algorithm.Algorithm25.Java.BOJ2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * rank를 도입.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 32_000   // N명
        int M = Integer.parseInt(st.nextToken());   // 100_000  // M번 키 비교

        int[] rank = new int[N+1];
        List<Integer>[] lists = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // A < B
            rank[B]++;
            lists[A].add(B);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (rank[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");

            for (int n : lists[cur]) {
                if (--rank[n] == 0) q.offer(n);
            }
        }

        System.out.println(sb.toString().trim());
    }
}

// 1
// 2
// 3 [1,]
// 4 [2,]

// 1 2 3 4
// 1 1 0 0 [3, 4] -> 3
// 0 1 0 0 [4, 1] -> 3 4
// 0 0 0 0 [1, 2] -> 3 4 1
//         [2]    -> 3 4 1 2
