// package Algorithm.Algorithm25.Java.BOJ3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입국심사대 N개 (100_000)
 * k번 심사대의 심사 시간 = Tk (^9)
 * 한 번 심사대에 오르면 계속 그 심사대에서 심사 받아야 함
 *
 * M(^9)명이 심사받는 최소 시간 = ?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long[] T = new long[N];
        long minT = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            T[i] = Long.parseLong(br.readLine());
            minT = Math.min(minT, T[i]);
        }

        System.out.println(binarySearch(N, M, T, minT));
    }

    /**
     * mid: 최대 시간
     * compare: sum (mid시간 동안 심사 가능한 최대 인원 수) vs M
     */
    private static long binarySearch(int N, long M, long[] T, long minT) {
        long l = 0, r = minT * M;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            int cmp = Long.compare(getMax(T, mid, M), M);

            if (cmp < 0) l = mid + 1;   // sum < M: 시간 부족
            else r = mid - 1;           // sum >= M: 가능
        }

        return l;   // 가능한 최대값
    }

    private static long getMax(long[] T, long mid, long M) {
        long sum = 0;               // sum: 10^5 * MAX_LONG
        for (long t : T) {
            sum += mid / t;         // += 각 심사대별 mid초동안 심사가능한 인원
            if (sum >= M) break;    // M명을 이미 다 심사할 수 있으면 break (overflow 방지)
        }
        return sum;
    }
}
