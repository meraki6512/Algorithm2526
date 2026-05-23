// 10개의 화살 -> 어디에 배치하느냐?
// 각 위치별로 0개 또는 info[i]+1개까지 배치할 수 있음
// bitmask || dfs

import java.util.*;

class Solution {
    
    private static int n, max = -1;
    private static int[] apeach, lion, answer = {-1};
    
    public int[] solution(int N, int[] info) {
        n = N;
        apeach = info;
        
        dfs(0, 0, new int[11]);
        
        return answer;
    }
    
    private void dfs(int idx, int cnt, int[] lion) {
        
        int left = n - cnt;
        
        if (left == 0 || idx == 10) {
            
            if (left > 0) lion[idx] = left;

            int scoreA = 0, scoreL = 0;
            for (int i = 0; i <= 10; i++) {
                if (apeach[i] == 0 && lion[i] == 0) continue; 
                else if (apeach[i] >= lion[i]) scoreA += (10-i); 
                else scoreL += (10-i);
            } 
            
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i <= 10; i++) {
            //     sb.append(apeach[i]).append(" ");
            // }
            // sb.append(": score=").append(scoreA).append("\n");
            // for (int i = 0; i <= 10; i++) {
            //     sb.append(lion[i]).append(" ");
            // }
            // sb.append(": score=").append(scoreL).append("\n");
            // System.out.println(sb.toString());
            
            int diff = scoreL - scoreA;
            if (diff > 0) {
                
                if (diff > max) {
                    answer = Arrays.copyOf(lion, 11);
                    max = diff;
                }
                else if (diff == max) {
                    // 낮은 걸 더 많이 맞힌 순
                    for (int k = 10; k >= 0; k--) {
                        if (lion[k] > answer[k]) {
                            answer = Arrays.copyOf(lion, 11);
                            max = diff;
                            break;
                        } else if (lion[k] < answer[k]) {
                            break;
                        }
                    }
                }
            }           
            
            if (left > 0) lion[idx] = 0;
            
            return;
        }
        
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i <= 10; i++) {
        //     sb.append(lion[i]).append(" ");
        // }
        // System.out.println(sb.toString());
        
        // 현재 idx에 맞혀서 점수를 따는 경우
        int toWin = apeach[idx] + 1;
        if (cnt + toWin <= n) {
            lion[idx] += toWin;
            dfs(idx + 1, cnt + toWin, lion);
            lion[idx] -= toWin;
        }
        
        // 현재 idx에 안 맞히고 점수를 주는 경우
        dfs(idx + 1, cnt, lion);
    } 
}
