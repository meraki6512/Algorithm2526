package Algorithm.Algorithm25.Java.BOJ1725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 나중에 다시 풀기
 * <a href="https://www.acmicpc.net/problem/1725">히스토그램</a>
 * 스택: O(N)
 * 세그먼트 트리: O(NlogN)
 */
public class Main {

    private static int N;
    private static int[] heights;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        heights = new int[N + 1];
        for (int i = 1; i <= N; i++) heights[i] = Integer.parseInt(br.readLine());
        tree = new int[N*4];

        init(1, 1, N);
        System.out.println(getMaxArea(1, N));
    }

    /**
     * minHeight의 "인덱스"
     * 값이 아닌 idx 저장이 핵심 포인트
     */
    private static long init(int cur, int l, int r) {
        if (l == r) return tree[cur] = l;

        int m = (l + r) / 2;
        init(cur * 2, l, m);
        init(cur * 2 + 1, m + 1, r);

        return tree[cur] = (heights[tree[cur*2]] <= heights[tree[cur*2+1]]) ? tree[cur*2] : tree[cur*2+1];
    }

    private static int query(int cur, int l, int r, int x, int y) {
        if (x > r || y < l) return -1;
        if (x <= l && r <= y) return tree[cur];

        int m = (l + r) / 2;
        int leftMinIdx = query(cur * 2, l, m, x, y);
        int rightMinIdx = query(cur * 2 + 1, m + 1, r, x, y);

        if (leftMinIdx == -1) return rightMinIdx;
        if (rightMinIdx == -1) return leftMinIdx;

        if (heights[leftMinIdx] <= heights[rightMinIdx]) return leftMinIdx;
        else return rightMinIdx;
    }

    private static long getMaxArea(int x, int y) {
        int minIdx = query(1, 1, N, x, y);
        long maxArea = (long) (y - x + 1) * heights[minIdx];

        // 최솟값 인덱스 기준 왼쪽 구간 탐색
        if (x <= minIdx - 1) {
            maxArea = Math.max(maxArea, getMaxArea(x, minIdx - 1));
        }

        // 최솟값 인덱스 기준 오른쪽 구간 탐색
        if (minIdx + 1 <= y) {
            maxArea = Math.max(maxArea, getMaxArea(minIdx + 1, y));
        }

        return maxArea;
    }

    private void print(int cur, int l, int r, int depth) {
        String indent = "|  ".repeat(depth);
        System.out.println(indent + "Node " + cur + " [" + l + ", " + r + "]: " + tree[cur]);

        if (l!=r) {
            int m = l + (r - l)/2;
            print(cur*2, l, m, depth+1);
            print(cur*2+1, m+1, r, depth+1);
        }
    }
}
