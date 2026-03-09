package Algorithm.Algorithm25.Java.BOJ1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 50   (원래)
        int M = Integer.parseInt(st.nextToken());   // 100  (새롭게)
        int L = Integer.parseInt(st.nextToken());   // 1000 (전체)

        int[] resting = new int[N+2];
        resting[0] = 0;
        resting[N+1] = L;

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) resting[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(resting);
        
        // 탐색: 휴게소 간격
        int l = 1, r = L-1;
        while (l <= r) {
            int mid = l + (r-l)/2;  

            int cnt = 0;
            for (int i = 1; i < N+2; i++) {
                cnt += (((resting[i] - resting[i-1]) - 1) / mid);     // 1, 3 -> 1개 ((3-1)-1)
            }

            // 지어야할 개수가 실제보다 적으면: 너무 길다
            if (cnt <= M) r = mid - 1;
            else l = mid + 1;
        }

        System.out.println(l);
    }
}
