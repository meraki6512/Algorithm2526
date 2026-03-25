package Algorithm.Algorithm25.Java.PRGMS_KAKAO_HintStage;

public class Solution {

    private static int ans = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] cost = {{160, 140, 120, 110, 60},
                {290, 270, 260, 120, 10},
                {160, 130, 120, 60, 20},
                {160, 120, 80, 70, 20},
                {110, 70, 60, 30, 20}};
        int[][] hint = {{40, 2, 3}, {40, 5, 3}, {20, 5, 4}, {50, 5, 5}};

        int[][] cost1 = {{110, 100, 90, 80, 70, 50, 10}, {170, 160, 150, 140, 130, 110, 30}, {260, 250, 190, 180, 170, 130, 100}, {170, 150, 120, 90, 60, 40, 30}, {220, 140, 110, 100, 70, 60, 50}, {290, 180, 150, 130, 100, 20, 10}, {110, 100, 90, 70, 50, 40, 30}};
        int[][] hint1 = {{40, 3, 4, 3}, {80, 3, 7, 4}, {40, 7, 6, 5}, {40, 7, 5, 5}, {50, 6, 7, 6}, {30, 7, 7, 7}};

        int[][] cost2 = {{290, 270, 170, 100, 60, 20}, {210, 190, 180, 170, 120, 80}, {200, 180, 170, 80, 40, 20}, {100, 90, 70, 60, 40, 10}, {230, 200, 170, 150, 90, 80}, {150, 100, 80, 60, 40, 10}};
        int[][] hint2 = {{230, 2, 2, 2, 2}, {330, 5, 3, 4, 3}, {180, 5, 4, 4, 6}, {280, 5, 5, 6, 6}, {260, 6, 6, 6, 6}};

        int[][] cost3 = {{49250, 42271, 40724, 36310, 32560, 30670, 24100, 10378}, {58510, 56101, 54078, 32864, 31443, 19451, 18098, 7187}, {68812, 66112, 65024, 60529, 53992, 39865, 31325, 17700}, {13768, 12866, 11379, 10425, 6853, 6176, 5655, 2556}, {51748, 48647, 41478, 39756, 25302, 18081, 16504, 811}, {52690, 34113, 32370, 29555, 19343, 11763, 7566, 5962}, {9306, 9190, 8196, 7573, 6275, 4723, 1316, 212}, {40713, 40158, 31449, 22349, 20956, 20377, 19489, 14450}};
        int[][] hint3 = {{0, 2, 4, 6, 2}, {0, 6, 3, 3, 4}, {0, 6, 4, 5, 5}, {0, 7, 5, 8, 7}, {0, 7, 7, 7, 7}, {0, 7, 7, 7, 8}, {0, 8, 8, 8, 8}};

        int[][] cost4 = {{330, 300, 277, 162, 126}, {640, 392, 358, 345, 231}, {960, 872, 678, 392, 113}, {210, 195, 176, 42, 37}, {980, 964, 623, 327, 154}};
        int[][] hint4 = {{976, 2, 2, 3, 5, 3, 3}, {746, 3, 3, 3, 3, 4, 3}, {319, 5, 4, 4, 4, 4, 4}, {966, 5, 5, 5, 5, 5, 5}};

        int[][] cost5 = {{230, 223, 192, 93, 59, 33}, {290, 240, 193, 117, 113, 42}, {950, 908, 796, 598, 574, 328}, {720, 692, 588, 237, 102, 61}, {780, 569, 522, 450, 337, 293}, {850, 837, 768, 568, 382, 124}};
        int[][] hint5 = {{158, 2, 2, 3}, {260, 5, 4, 3}, {268, 4, 4, 4}, {426, 5, 5, 6}, {330, 6, 6, 6}};

        System.out.println(solution.solution(cost, hint));
        System.out.println(solution.solution(cost1, hint1));
        System.out.println(solution.solution(cost2, hint2));
        System.out.println(solution.solution(cost3, hint3));
        System.out.println(solution.solution(cost4, hint4));
        System.out.println(solution.solution(cost5, hint5));
    }

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