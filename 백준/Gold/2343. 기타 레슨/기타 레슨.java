// package Algorithm.Algorithm25.Java.BOJ2343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 블루레이
 */
public class Main {

    private static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 100_000  강의 수
        int M = Integer.parseInt(st.nextToken());   // 블루레이 수

        // 최대 강의 길이: 10_000 -> 최대 합 10^9
        int l = 0, r = 0;  // 매개변수: time

        st = new StringTokenizer(br.readLine());
        time = new int[N];
        for (int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            if (l < time[i]) l = time[i];   // 가장 큰 강의 하나
            r += time[i];                   // 전체 강의를 하나의 블루레이로
        }

        // binary search (블루레이 시간)
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (getBRCount(mid) > M) l = mid + 1;
            else r = mid - 1;
        }

        System.out.println(l);
    }

    // x분짜리 블루레이가 되려면 몇 개로 쪼개야 하는가
    private static int getBRCount(int x) {
        int cnt = 1, sum = 0;

        for (int i = 0; i < time.length; i++) {
            if (sum + time[i] > x) {
                cnt ++;
                sum = 0;
            }
            sum += time[i];
        }

        return cnt;
    }
}
