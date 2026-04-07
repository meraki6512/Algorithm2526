import java.util.*;

class Solution {
    
    private int[][] q;
    private int[] ans;
    private int n, m, answer = 0;
    
    public int solution(int N, int[][] Q, int[] Ans) {
        
        // n까지의 서로 다른 정수 5개: 30C5: 대충 10만 정도
        // q의 길이가 최대 10 = m
        q = Q;
        ans = Ans;
        n = N;
        m = q.length;
        
        dfs_comb(1, 0, new int[5]);
        
        return answer;
    }
    
    private void dfs_comb(int num, int depth, int[] code) {
        if (depth == 5) {   // 5개 뽑음
            checkValid(code);
            return;
        }
        
        for (int i = num; i <= n; i++) {
            code[depth] = i;    // depth번째 숫자 i 저장
            dfs_comb(i+1, depth+1, code);
        }
    }
    
    private void checkValid(int[] code) {
        
        for (int k = 0; k < m; k++) {
            
            int[] cur = q[k];
            int cnt = 0;
            
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    
                    if (cur[i] == code[j]) {
                        cnt++;
                    }
                }
            }
            
            if (cnt != ans[k]) {
                return;
            }
        }
        
        answer++;
    }
}