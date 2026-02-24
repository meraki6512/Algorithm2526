// package Algorithm.Algorithm25.Java.BOJ2357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class SegTree {
        int[] minTree;
        int[] maxTree;

        public void init(int[] arr, int cur, int l, int r) {
            if (l == r) {
                minTree[cur] = maxTree[cur] = arr[l];
                return;
            }

            int m = l + (r - l)/2;
            init(arr, cur*2, l, m);
            init(arr, cur*2+1, m+1, r);

            minTree[cur] = Math.min(minTree[cur*2], minTree[cur*2+1]);
            maxTree[cur] = Math.max(maxTree[cur*2], maxTree[cur*2+1]);
        }

        public int getMax(int cur, int l, int r, int x, int y) {
            if (r < x || l > y) return 0;
            if (x <= l && r <= y) return maxTree[cur];

            int m = l + (r - l)/2;
            return Math.max(
                    getMax(cur*2, l, m, x, y),
                    getMax(cur*2+1, m+1, r, x, y)
            );
        }

        public int getMin(int cur, int l, int r, int x, int y) {
            if (r < x || l > y) return 1_000_000_001;
            if (x <= l && r <= y) return minTree[cur];

            int m = l + (r - l)/2;
            return Math.min(
                    getMin(cur*2, l, m, x, y),
                    getMin(cur*2+1, m+1, r, x, y)
            );
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());

        SegTree segTree = new SegTree();
        segTree.minTree = new int[4*N];
        segTree.maxTree = new int[4*N];
        segTree.init(arr, 1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(segTree.getMin(1, 1, N, x, y)).append(" ")
                    .append(segTree.getMax(1, 1, N, x, y)).append("\n");
        }

        System.out.println(sb);
    }
}
