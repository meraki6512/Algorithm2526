// package Algorithm.Algorithm25.Java.BOJ1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N, S;
    private static int[] arr;
    private static List<Integer> leftList = new ArrayList<>(), rightList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 40
        S = Integer.parseInt(st.nextToken());   // +- 1_000_000

        st = new StringTokenizer(br.readLine());
        arr = new int[N]; // 100_000
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        // 합이 S가 되는 부분 수열의 개수 = ?
        // 원소 40개짜리 부분 수열의 개수: 2^40(10^12~13)
        // 20개 짜리: 2^20(10^6~7)
        // : 20개짜리 2개로 나눠서 부분수열 구하고 투 포인터를 사용한다.
        makeSumList(0, N/2, 0, leftList);
        makeSumList(N/2, N, 0, rightList);
        Collections.sort(leftList);
        Collections.sort(rightList);
        sol();
    }

    private static void sol() {
        long ans = 0;
        int pl = 0, pr = rightList.size()-1;

        while (pl < leftList.size() && pr >= 0) {
            int vl = leftList.get(pl), vr = rightList.get(pr);

            if (vl + vr == S) {
                int cl = 0, cr = 0;
                while (pl < leftList.size() && leftList.get(pl) == vl) {
                    pl++;
                    cl++;
                }
                while (pr >= 0 && rightList.get(pr) == vr) {
                    pr--;
                    cr++;
                }
                ans += (long) cl * cr;
            }
            else if (vl + vr < S) pl++;
            else if (vl + vr > S) pr--;
        }

        System.out.println(S == 0? ans-1 : ans);
    }

    private static void makeSumList(int cur, int end, int sum, List<Integer> list) {
        if (cur == end) {
            list.add(sum);
            return;
        }
        makeSumList(cur + 1, end, sum, list);
        makeSumList(cur + 1, end, sum + arr[cur], list);
    }
}
