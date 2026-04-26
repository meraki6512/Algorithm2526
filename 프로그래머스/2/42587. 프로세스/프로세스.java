import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int n = priorities.length;
        
        Queue<Process> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            que.add(new Process(priorities[i], i));
        }

        int ptr = n-1, cnt = 1;
        Arrays.sort(priorities);
        
        while (!que.isEmpty()) {
            Process cur = que.poll();
            if (cur.prio < priorities[ptr]) que.add(cur);
            else {
                if (cur.idx == location) return cnt;
                ptr--;
                cnt++;
            }
        }
        
        return cnt;
    }
    
    class Process {
        int prio, idx;
        Process(int prio, int idx) {
            this.prio = prio;
            this.idx = idx;
        }
    }
}

// 우선순위