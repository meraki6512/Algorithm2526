public class Solution {

    private static int ans = 0;
    private static int n, k;

    public int solution(int[][] cost, int[][] hint) {

        n = cost.length;    // stage num
        k = hint[0].length; // hint num of each bundle
        int[] hint_got = new int[n+1];
        ans = 0;
        for (int i = 0; i < n; i++) ans += cost[i][0];

        dfs(0, 0, cost, hint, hint_got);
        return ans;
    }

    private static void dfs(int idx, int sum, int[][]cost, int[][] hint, int[] hint_got) {

        if (sum >= ans) return;
        if (idx == hint.length) {
            ans = Math.min(ans, sum + cost[idx][Math.min(hint_got[idx + 1], n - 1)]);
            return;
        }

        int cur = sum + cost[idx][Math.min(hint_got[idx + 1], n - 1)];

        // x get current bundle
        dfs(idx + 1, cur, cost, hint, hint_got);

        // get current bundle
        for (int i = 1; i < k; i++) hint_got[hint[idx][i]]++;
        dfs(idx + 1, cur + hint[idx][0], cost, hint, hint_got);
        for (int i = 1; i < k; i++) hint_got[hint[idx][i]]--;
    }
}