package Algorithm.Algorithm25.Java.BOJ1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N개의 수열의 버블 소트 시의 Swap 횟수 = ?
 * N: 500_000, A[i]: +-10^9
 * -> 최악의 경우 ans .. NC2
 * ** Inversion Counting
 */
public class Main {

    private static int N;
    private static long[] A, tmp;
    private static long ans;
    private static int[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) A[i] = Long.parseLong(st.nextToken());

        System.out.println(solWMergeSort());
        System.out.println(solWSegTree());
    }

    /**
     * 병합 정렬을 하면서,
     * 오른쪽 배열의 원소가 왼쪽 배열의 원소보다 작아서 먼저 배열에 들어간다면,
     * 남아있는 왼쪽 배열의 모든 원소들을 뛰어넘어야 한다.
     */
    private static long solWMergeSort() {
        ans = 0;
        tmp = new long[N + 1];

        mergeSort(1, N);

        return ans;
    }

    private static void mergeSort(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(l, m);
            mergeSort(m + 1, r);
            merge(l, m, r);
        }
    }

    private static void merge(int l, int m, int r) {
        int i = l, j = m + 1;   // A ptr (i: left, j: right side)
        int k = l;              // tmp ptr (merged)

        while (i <= m && j <= r) {
            if (A[i] <= A[j]) tmp[k++] = A[i++];
            else {
                // 오른쪽이 작으면 스왑 발생: 남은 왼쪽 개수만큼
                tmp[k++] = A[j++];
                ans += (m - i + 1);
            }
        }

        while (i <= m) tmp[k++] = A[i++];
        while (j <= r) tmp[k++] = A[j++];

        for (int t = l; t <= r; t++) A[t] = tmp[t];
    }

    /**
     * val, idx를 묶고, val을 기준으로 정렬 후
     * 세그먼트 트리의 idx 값으로 스왑 계산
     * : 좌표 압축
     * leaf = 원래 배열의 idx -> 0 또는 1
     * tree 구간합 = 해당 구간에 1이 채워진 노드의 개수
     */
    private static long solWSegTree() {
        ans = 0;
        tree = new int[4 * N + 1];
        Element[] arr = new Element[N];
        for (int i = 1; i <= N; i++) arr[i-1] = new Element(A[i], i);

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            int originalIdx = arr[i].idx;
            ans += query(1, 1, N, originalIdx+1, N); // 현재 위치 값보다 작으면서 오른쪽에 있는 원소 개수
            update(1, 1, N, originalIdx); // 원래 위치에 1++ (값이 들어왔음을 마킹)
        }

        return ans;
    }

    private static class Element implements Comparable<Element> {
        long val;
        int idx;

        public Element(long val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Element o) {
            return this.val == o.val ?
                    Integer.compare(this.idx, o.idx) :
                    Long.compare(this.val, o.val);
        }
    }

    // (들어온 개수의) 구간 합
    private static int query(int cur, int l, int r, int x, int y) {
        if (r < x || l > y) return 0;
        if (x <= l && r <= y) return tree[cur];

        int m = l + (r - l) / 2;
        return query(cur*2, l, m, x, y) + query(cur*2 + 1, m+1, r, x, y);
    }
    
    // 특정 idx에 1 더함 (해당 구간 노드에 전부)
    private static void update(int cur, int l, int r, int idx) {
        if (idx < l || r < idx) return;
        tree[cur] ++;
        if (l != r) {
            int m = l + (r-l)/2;
            update(cur*2, l, m, idx);
            update(cur*2 + 1, m+1, r, idx);
        }
    }
}

// 다시 풀기

// 정렬 후
//arr[0] = {val: 1, idx: 3}
//arr[1] = {val: 2, idx: 2}
//arr[2] = {val: 3, idx: 1}

// [0]번 처리 {val: 1, idx: 3}
//ans += 0 ... ans = 0
//tree = [0, 0, 1]

// [1]번 처리 {val: 2, idx: 2}
//ans += 1 ... ans = 0
//tree = [0, 1, 1]

// [2]번 처리 {val: 3, idx: 1}
//ans += 2 .. ans = 3
//tree = [1, 1, 1]

