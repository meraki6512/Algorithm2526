package Algorithm.Algorithm25.Java.BOJ1450;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int C, arrIdx;
    private static int[] weights;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 30
        C = Integer.parseInt(st.nextToken());   // ^9

        st = new StringTokenizer(br.readLine());
        weights = new int[N];
        for (int i = 0; i < N; i++) weights[i] = Integer.parseInt(st.nextToken()); // ^9

        // make sum list

        long[] leftSum = new long[1<<(N/2)];
        long[] rightSum = new long[1<<(N-N/2)];

        arrIdx = 0;
        dfs(0, N/2, 0, leftSum);
        int leftCnt = arrIdx;

        arrIdx = 0;
        dfs(N/2, N, 0, rightSum);
        int rightCnt = arrIdx;

        // binary search

        Arrays.sort(rightSum, 0, rightCnt);

        long ans = 0;   // 2^30

        for (int i = 0; i < leftCnt; i++) {
            long x = C - leftSum[i];
            if (x >= 0) ans += upperBound(rightSum, rightCnt, x); // 남은 무게 활용 가능
        }

        System.out.println(ans);
    }

    private static void dfs(int idx, int end, long sum, long[] arr) {
        if (sum > C) return;

        if (idx == end) {
            arr[arrIdx++] = sum;
            return;
        }

        dfs(idx + 1, end, sum, arr);
        dfs(idx + 1, end, sum + weights[idx], arr);
    }

    private static long upperBound(long[] list, int n, long x) {
        int left = 0;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list[mid] <= x) left = mid + 1;
            else right = mid;
        }

        return left;    // 개수 리턴
    }
}
