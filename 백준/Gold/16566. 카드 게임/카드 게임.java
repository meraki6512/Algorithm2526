// package Algorithm.Algorithm25.Java.BOJ16566;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] hasCard;
    private static int[] cards, parent;
    private static int N, M, K;
    private static BufferedReader br;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        WithOutBS();
        // WithBS();
    }

    private static void WithOutBS() throws IOException {
        hasCard = new boolean[N+2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            hasCard[Integer.parseInt(st.nextToken())] = true;
        }

        parent = new int[N+2];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            if (!hasCard[i]) {
                union(i, i + 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int c = find(Integer.parseInt(st.nextToken()) + 1);
            sb.append(c).append("\n");
            union(c, c + 1);
        }

        System.out.println(sb.toString());
    }

    private static void WithBS() throws IOException {
        st = new StringTokenizer(br.readLine());
        cards = new int[M];
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        parent = new int[M+1];
        for (int i = 0; i < M+1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {

            // target x보다 큰 첫번째 오른쪽 idx
            int x = Integer.parseInt(st.nextToken());
            int rIdx = binarySearchRight(x);

            // 실제 사용할 수 있는 right most idx
            int eIdx = find(rIdx);
            sb.append(cards[eIdx]).append("\n");
            union(eIdx, eIdx+1);    // 사용 처리: 다음 인덱스와 연결
        }

        System.out.println(sb.toString());
    }

    // UpperBound BS
    private static int binarySearchRight(int t) {
        int l = 0, r = M-1;
        int res = 0;

        while (l <= r) {
            int m = l + (r - l) / 2;

            // mid 값이 target보다 큼 : 후보 업뎃 & 범위 줄여서 계속 탐색
            if (cards[m] > t) {
                res = m;
                r = m - 1;
            }
            // target보다 mid 값이 더 작거나 같음 : 범위를 큰 쪽으로 옮김 
            else {
                l = m + 1;
            }
        }

        // the right most idx -> res
        return res;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = find(parent[x]);    // 다음 빈 칸으로 바로 점프
    }

    // x 사용 후 y로 연결: x의 부모 = y
    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        parent[x] = y;
    }
}
