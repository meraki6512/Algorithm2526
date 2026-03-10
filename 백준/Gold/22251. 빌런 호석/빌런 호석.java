// package Algorithm.Algorithm25.Java.BOJ22251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static int[][] display = {
            {1, 1, 1, 0, 1, 1, 1}, // 0
            {0, 0, 1, 0, 0, 1, 0}, // 1
            {1, 0, 1, 1, 1, 0, 1}, // 2
            {1, 0, 1, 1, 0, 1, 1}, // 3
            {0, 1, 1, 1, 0, 1, 0}, // 4
            {1, 1, 0, 1, 0, 1, 1}, // 5
            {1, 1, 0, 1, 1, 1, 1}, // 6
            {1, 0, 1, 0, 0, 1, 0}, // 7
            {1, 1, 1, 1, 1, 1, 1}, // 8
            {1, 1, 1, 1, 0, 1, 1}  // 9
    };

    private static int[][] diff = new int[10][10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 숫자 제한 (^6)
        int K = Integer.parseInt(st.nextToken());   // 자릿수 제한 (6)
        int P = Integer.parseInt(st.nextToken());   // 온오프 수 제한 (42)
        int X = Integer.parseInt(st.nextToken());   // 실제 층 수 (N)

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int cnt = 0;
                for (int k = 0; k < 7; k++) if (display[i][k] != display[j][k]) cnt++;
                diff[i][j] = diff[j][i] = cnt;
            }
        }

        // brute force

        int[] digitX = getDigits(X, K);
        int ans = 0;

        for (int t = 1; t <= N; t++) {  // 10^6
            if (t == X) continue;

            int[] digitTo = getDigits(t, K);
            int cnt = 0;
            for (int i = 0; i < K; i++) cnt += diff[digitX[i]][digitTo[i]];
            if (cnt <= P) ans++;
        }

        System.out.println(ans);
        
    }
    
    // 숫자 n을 k자리의 배열로 변환 (빈자리: 0)
    private static int[] getDigits(int n, int k) {
        int[] digits = new int[k];
        for (int i = k-1; i >= 0; i--) {
            digits[i] = n % 10;
            n /= 10;
        }
        return digits;
    }
}
