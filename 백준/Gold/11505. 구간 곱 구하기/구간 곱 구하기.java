// package Algorithm.Algorithm25.Java.BOJ11505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 곱
 * N: 10^6, M: 10^4, K: 10^4 -> SegTree: (M+K)logN
 */
public class Main {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        SegTree segTree = new SegTree(N);
        segTree.init(arr, 1, 1, N);

//        System.out.println();
//        segTree.print(1, 1, N, 0);

        for (int i = 0; i < M+K; i++) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) segTree.update(1,1, N, b, c);
            else sb.append(segTree.mul(1, 1, N, b, c) % MOD).append("\n");
        }

        System.out.println(sb);
    }

    public static class SegTree {
        long[] tree;
        int size;

        public SegTree(int N) {
            int high = (int) Math.ceil(Math.log(N) / Math.log(2));
            size = (int) Math.pow(2, high + 1); // just 4N is ok
            tree = new long[size];
        }

        public long init(int[] arr, int cur, int low, int high) {
            if (low == high) return tree[cur] = arr[low];

            int mid = (low + high) / 2;
            return tree[cur]
                    = (init(arr, cur*2, low, mid)
                    * init(arr, cur*2 + 1, mid + 1, high))
                    % MOD;
        }

        // arr[idx] := val ... -> update tree
        // diff (x) recursive update (o)
        public long update(int cur, int low, int high, int idx, int val) {
            if (idx < low || idx > high) return tree[cur];  // out of range
            if (low == high) return tree[cur] = val;        // leaf

            int mid = (low + high) / 2;
            return tree[cur] =
                    (update(cur*2, low, mid, idx, val)
                    * update(cur*2 + 1, mid + 1, high, idx, val))
                    % MOD;
        }

        // range product: x ~ * ~ y
        public long mul(int cur, int low, int high, int x, int y) {
            if (y < low || x > high) return 1;
            if (x <= low && high <= y) return tree[cur];

            int mid = (low + high) / 2;
            return (mul(cur*2, low, mid, x, y)
                    * mul(cur*2 + 1, mid + 1, high, x, y))
                    % MOD;
        }

        public void print(int cur, int low, int high, int depth) {
            String indent = "|  ".repeat(depth);
            System.out.println(indent + "Node " + cur + " [" + low + ", " + high + "]: " + tree[cur]);

            if (low != high) {
                int mid = (low + high) / 2;
                print(cur*2, low, mid, depth + 1);
                print(cur*2 + 1, mid + 1, high,depth + 1);
            }
        }
    }
}