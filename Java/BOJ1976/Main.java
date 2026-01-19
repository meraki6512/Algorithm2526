package Algorithm.Algorithm25.Java.BOJ1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] root, rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        root = new int[N+1];
        rank = new int[N+1];
        for (int i = 1; i <= N; i++) root[i] = i;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1 && i < j) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = find(Integer.parseInt(st.nextToken()));
        while (M-->1) if (x != find(Integer.parseInt(st.nextToken()))) break;
        System.out.println(M < 1 ? "YES" : "NO");

    }
    private static int find(int x) {
        if (x == root[x]) return x;
        else return root[x] = find(root[x]);
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (rank[a] < rank[b]) root[a] = b;
        else if (rank[b] < rank[a]) root[b] = a;
        else {
            rank[a] ++;
            root[b] = a;
        }
    }
}
