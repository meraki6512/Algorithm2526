import java.util.*;

class Solution {
    
    private int[] diffs, times;
    private long limit;
    
    public int solution(int[] Diffs, int[] Times, long Limit) {
        diffs = Diffs;
        times = Times;
        limit = Limit;
        
        return binarySearch();
    }
    
    private int binarySearch() {
        
        int l = 1;
        int r = 100_000;
        int m;
        
        while (l < r) {
            m = l + (r-l)/2;    // 현재 숙련도 기준값
            
            if (getTotalTime(m) <= limit) r = m;  // 가능 시 숙련도 줄임
            else l = m+1;
        }
        
        return r;
    }
    
    private long getTotalTime(int level) {
        long cur_t = 0L;
        long res = 
            diffs[0] <= level ? times[0]
            : times[0]*(diffs[0]-level)+times[0];
        
        for (int i = 1; i < diffs.length; i++) {    // 300,000
            if (diffs[i] <= level) {
                cur_t = times[i];
            }
            else {
                cur_t = ((times[i]+times[i-1]) * (diffs[i]-level) + times[i]);
            }
            res += cur_t;
        }
        
        return res;
    }
}

// 퍼즐을 모두 해결하기 위한 숙련도의 최솟값? .. 하한 구하기
// 매개변수 탐색
// 1 <= 숙련도 <= diff의 최대면 제일 빠름: 300,000

// case: diff <= level
// time_cur
// case: diff > level
// (time_cur+time_entire_prev)*(diff-level)+time_cur
