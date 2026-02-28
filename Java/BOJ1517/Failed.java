package Algorithm.Algorithm25.Java.BOJ1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 수열의 버블 소트 시의 Swap 횟수 = ?
 * N: 500_000
 * A[i]: 10^9
 */
public class Failed {

    static int[] tree;  // idx
    static long[] A;    // val
    static int N;
    static final int MIN = -1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) A[i] = Long.parseLong(st.nextToken());
        tree = new int[4*N];
        init(1, 1, N);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            System.out.println();
            print(1, 1, N, 0);

            int idx = getMaxIdx(1, 1, N, 1, N);
            ans += (N - idx);
            update(1, 1, N, idx);

            System.out.println("[" + i + "]: " + idx + ", ans: " + ans );
        }
        System.out.println(ans);
    }

    private static void init(int cur, int l, int r) {
        if (l == r) {
            tree[cur] = l;
            return;
        }

        int m = l + (r-l)/2;
        init(cur*2, l, m);
        init(cur*2+1, m+1, r);

        tree[cur] = A[tree[cur*2]] > A[tree[cur*2+1]] ? tree[cur*2] : tree[cur*2+1];
    }

    private static void update(int cur, int l, int r, int idx) {
        if (idx < l || idx > r) return;
        if (l == r) {
            A[tree[cur]] = MIN;
            return;
        }

        int m = l + (r - l) / 2;
        update(cur*2, l, m, idx);
        update(cur*2+1, m+1, r, idx);

        tree[cur] = A[tree[cur * 2]] > A[tree[cur * 2 + 1]] ? tree[cur * 2] : tree[cur * 2 + 1];
    }

    private static int getMaxIdx(int cur, int l, int r, int x, int y) {
        if (r < x || l > y) { // out of range
            return -1;
        }

        if (x <= l && r <= y) { // targeted
            return tree[cur];
        }

        int m = l + (r-l)/2;
        int left = getMaxIdx(cur*2, l, m, x, y);
        int right = getMaxIdx(cur*2+1, m+1, r, x, y);

        if (left == -1) return right;
        if (right == -1) return left;

        return A[left] > A[right] ? left : right;
    }

    private static void print(int cur, int l, int r, int depth) {
        String indent = "|  ".repeat(depth);
        System.out.println(indent + "Node " + cur + " [" + l + ", " + r + "]: " + tree[cur]);

        if (l!=r) {
            int m = l + (r - l)/2;
            print(cur*2, l, m, depth+1);
            print(cur*2+1, m+1, r, depth+1);
        }
    }
}

// Max 값의 idx를 찾는 쿼리 -> N-idx만큼 더해주고, 크기만큼 빼줌..?

//35241
//32415 + ++
//23145 + +
//21345 +
//12345 +

// -> over counting ..!