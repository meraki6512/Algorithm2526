class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        // 서버 반납 시간을 저장 (24+24)
        int[] ret = new int[48];
        int cur = 0;
        
        for (int i = 0; i <24; i++) {
            // 반납
            cur -= ret[i];
            
            int need = players[i] / m - cur;    // 추가 필요
    
            // 증설            
            if (need > 0) {
                answer += need;
                cur += need;
                ret[i+k] += need;
            }
        }
        
        return answer;
    }
}