import java.util.*;

class Solution {
    public String[] solution(String[][] p) {
        
        PriorityQueue<Plan> pq = new PriorityQueue<>();
        for (int i = 0; i < p.length; i++){
            pq.add(new Plan(i, getIntTime(p[i][1]), Integer.parseInt(p[i][2])));
        }
        
        Queue<Integer> result = new LinkedList<>();
        Stack<Plan> stack = new Stack<>();
        
        int t = pq.peek().start;
        Plan cur = null;
        while (!pq.isEmpty() || !stack.isEmpty() || cur != null) {
            
            // 빈 타임: 하던 거 끄내기
            if (!stack.isEmpty() && cur == null) cur = stack.pop();
            
            // 새로운 거 시작
            if (!pq.isEmpty() && pq.peek().start == t) {
                if (cur != null && cur.left > 0) {  // 하던 거는 넣어두기
                    stack.push(cur);
                }
                cur = pq.poll();
            }
            
            // 지금 거 다 끝났으면 result에 넣기
            if (cur != null && --cur.left == 0) {
                result.add(cur.idx);
                cur = null;
            }
            
            t++;
        }
        
        // 하던 게 남아있으면 순서대로 끝내기
        while (!stack.isEmpty()) {
            result.add(stack.pop().idx);
        }
        
        String[] answer = new String[p.length];
        for (int i = 0; i < p.length; i++){
            answer[i] = p[result.poll()][0];
        }
        return answer;
    }
    
    private static int getIntTime(String s){
        String[] parts = s.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h*60+m;
    }
    
    private static class Plan implements Comparable<Plan>{
        int idx, start, left;
        public Plan(int idx, int start, int left){
            this.idx = idx;
            this.start = start;
            this.left = left;
        }
        @Override
        public int compareTo(Plan o){
            return this.start - o.start;
        }
    }
}