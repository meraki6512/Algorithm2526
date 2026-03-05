// package Algorithm.Algorithm25.Java.BOJ1561;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());   // 인원 2*10^9
        int M = Integer.parseInt(st.nextToken());   // 놀이기구 종류 10^4 ... 운행시간
        st = new StringTokenizer(br.readLine());
        int[] time = new int[M];                    // 기구별 운행 시간 1-30
        for (int i = 0; i < M; i++) time[i] = Integer.parseInt(st.nextToken());

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long T = binarySearch(N, time); // 최소 T 시간일 때 N명 탈 수 있음
        long cnt = getHeadCount(T-1, time); // T-1 시간일 때 놀이기구 탄 인원 수

        for (int i = 0; i < M; i++) {
            if (T % time[i] == 0) { // T 시간일 때 i번째 놀이기구 비어있음
                if (++cnt == N) {
                    System.out.println(i+1);
                    break;
                }
            }
        }
    }

    // N번 아이가 타는 놀이기구 번호 = ?
    // -> 특정 시간 T에서 몇 명이 탔나 ? (매개변수 = T)
    private static long binarySearch(long N, int[] time) {
        long l = 0, r = 30 * N;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            int cmp = Long.compare(getHeadCount(mid, time), N);

            if (cmp < 0) l = mid + 1;
            else r = mid - 1;
        }

        return l;
    }

    // 조건 판별 (특정 시간 T일 때, 현재까지 놀이기구를 탄 아이들의 총합)
    // T = 0: M명 동시 탑승
    // T > 0: 각 놀이기구마다 T분마다 몇 명을 태웠는지 계산: 1 + floor(T/time[i])
    private static long getHeadCount(long T, int[] time) {
        long res = 0;
        for (int i = 0; i < time.length; i++) {
            res += (1 + T/time[i]);
        }
        return res;
    }
}
