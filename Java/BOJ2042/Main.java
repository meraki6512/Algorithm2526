package Algorithm.Algorithm25.Java.BOJ2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기
 * N: 10^6, M, K: 10_000
 * -> N^2 (x) (M+K)logN (o)
 * -> Segment Tree
 * * 입력/정답 값 범위 주의
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N+1];
        for (int i = 1; i <= N; i++) arr[i] = Long.parseLong(br.readLine());

        SegTree segTree = new SegTree(N);
        segTree.init(arr, 1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                segTree.update(1, 1, N, b, c-arr[b]);
                arr[b] = c;
            }
            else {
                sb.append(segTree.sum(1, 1, N, b, (int)c)).append("\n");
            }
        }

        System.out.println(sb);

    }

    private static class SegTree {
        long[] tree;
        int size;
        public SegTree (int N) {
            int h = (int) Math.ceil(Math.log(N) / Math.log(2));
            size = (int) Math.pow(2, h+1);
            tree = new long[size];
        }

        public long init(long[] arr, int cur, int lo, int hi) {
            if (lo == hi) return tree[cur] = arr[lo];
            int mid = lo + (hi - lo) / 2;
            return tree[cur] = init(arr, cur*2, lo, mid) + init(arr, cur*2+1, mid+1, hi);
        }

        public void update(int cur, int lo, int hi, int idx, long diff) {
            if (idx < lo || idx > hi) return;   // out of range
            tree[cur] += diff;

            if (lo != hi) {                     // not leaf -> recursive update
                int mid = lo + (hi - lo) / 2;
                update(cur*2, lo, mid, idx, diff);
                update(cur*2+1, mid+1, hi, idx, diff);
            }
        }

        public long sum(int cur, int lo, int hi, int x, int y) {
            if (y < lo || x > hi) return 0;
            if (x <= lo && hi <= y) return tree[cur];
            int mid = lo + (hi - lo) / 2;
            return sum(cur*2, lo, mid, x, y) + sum(cur*2+1, mid+1, hi, x, y);
        }
    }
}
