// package Algorithm.Algorithm25.Java.BOJ1572;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 250_000
        int K = Integer.parseInt(st.nextToken());   // 5_000

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        long ans = 0;
        final int MIN = 0, MAX = 65536, MED = (K+1)/2;
        tree = new int[4*MAX+1];
        int i;
        for (i = 1; i < K; i++) update(1, MIN, MAX, arr[i], 1);

        // sliding window
        for (; i <= N; i++) {
            update(1, MIN, MAX, arr[i], 1);
            ans += query(1, MIN, MAX, MED);
            update(1, MIN, MAX, arr[i-K+1], -1);
        }

        System.out.println(ans);
    }

    private static void update(int cur, int l, int r, int idx, int v) {
        if (r < idx || l > idx) return;
        tree[cur] += v;
        if (l != r) {
            int m = l + (r-l)/2;
            update(cur*2, l, m, idx, v);
            update(cur*2+1, m+1, r, idx, v);
        }
    }

    private static long query(int cur, int l, int r, int med) {
        if (l == r) return l;   // 0 || 1

        int m = l + (r-l)/2;
        if (tree[cur*2] >= med) {
            return query(cur*2, l, m, med);
        }
        else {
            return query(cur*2+1, m+1, r, med-tree[cur*2]);
        }
    }
}
