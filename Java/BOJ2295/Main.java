package Algorithm.Algorithm25.Java.BOJ2295;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 1000
        long[] U = new long[N];
        for (int i = 0; i < N; i++) U[i] = Long.parseLong(br.readLine());

        // 0. sort (U: 최댓값 찾기 위해서)
        Arrays.sort(U);

        // 1. 합의 집합 미리 만들어둠 (a+b)
        long[] sumsTmp = new long[N*N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumsTmp[idx++] = U[i] + U[j];
            }
        }

        // 2. sort (sums: 이분 탐색을 위해서)
        long[] sums = Arrays.copyOf(sumsTmp, idx);
        Arrays.sort(sums);

        // 3. 탐색
        // a+b+c = [k] -> a+b = [k]-c
        for (int k = N-1; k >= 0; k--) {

            for (int i = 0; i <= k; i++) {

                if (Arrays.binarySearch(sums, U[k] - U[i]) >= 0) { // [k] - c in sums
                    System.out.println(U[k]);
                    return;
                }

            }
        }

    }


    /**
     * sum, target 비교해 포인터 이동하는 부분이 문제
     * ex) U: {2, 10, 15, 20, 35}
     * 실제 정답: 35 (10+10+15)
     */
    private static void wrongSol(int N, long[] U) {

        HashSet<Long> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(U[i]);
        }

        for (int k = N-1; k >= 0; k--) {

            long target = U[k];
            int l = 0, r = k-1;

            while (l <= r) {

                long sum = U[l] + U[r];

                if (set.contains(target - sum)) {
                    System.out.println(target);
                    return;
                }

                if (sum >= target) r--;
                else l++;

            }
        }
    }
}