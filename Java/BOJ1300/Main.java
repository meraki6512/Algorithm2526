package Algorithm.Algorithm25.Java.BOJ1300;

import java.io.IOException;
import java.util.Scanner;

// k번째로 큰 수
// A[i][j] = i*j
// 1 2 3... N
// 2 4 6... 2N
// 3 6 9... 3N
// ...
// N ...... N*N

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();



        System.out.println(binarySearch(N, k));
    }


    /**
     * 매개변수 탐색
     * : 1 ~ k 사이의 수 하나를 정해 조건에 맞는지 검사
     * : mid = k번째 수라고 가정한 임의 값
     * : 조건을 만족하는 최솟값까지 범위를 좁혀나간다.
     */
    // - k번째 수는 k를 넘을 수 없다.
    // - i번째 행에서 mid보다 작거나 같은 수의 개수는 min(mid/i, N).
    // - 그 개수의 총합이 k보다 작으면 mid가 작다는 뜻.

    private static int binarySearch(int N, int k) {
        int l = 1, r = k;

        while (l <= r) {
            int m = l + (r-l)/2;
            int cmp = Integer.compare(getLessThanMid(N, m), k);

            if (cmp < 0) l = m + 1;
            else r = m - 1;
        }

        return l;
    }

    private static int getLessThanMid(int N, int mid) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Math.min(mid/i, N);
        }
        return sum;
    }


    /**
     * N = 3, k = 7 (3x3 배열에서 7번째로 큰 수 찾기)
     * [1, 2, 3]
     * [2, 4, 6]
     * [3, 6, 9]
     * [1, 2, 2, 3, 3, 4, 6, 6, 9]
     *
     * l=1, r=7
     * (1) m = 4 ... 3+2+1=6 < 7 ... l+: l=5, r=7
     * (2) m = 6 ... 3+3+2=8 > 7 ... r+: l=5, r=5
     * (3) m = 5 ... 3+2+1=6 < 7 ... l+: l=6, r=5
     *
     * ret: 6
     */
}
