import java.util.*;

// 같은 종류 파이프끼리 한번에 열었다 닫을 수 있음
// 종류는 총 세 개 (A10, B21, C32)
// 파이프를 여닫는 행동을 k번 반복해 최대한 많이 감염시키기 -> 감염된 수 return
class Solution {
    
    private static int answer = 0, N, K;
    private static List<Node>[] graph;
    
    // n: 배양체 개수 100
    // infection: 감염 배양체의 노드 번호
    // edges: [x, y, type]의 리스트: n-1개 (트리)
    // 3^10 = 10^5정도 -> 2^10 중복 없이 = 10^3정도
    public int solution(int n, int infection, int[][] edges, int k) {
        
        N = n;
        K = k;
        graph = new List[n+1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<Node>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new Node(edge[1], edge[2]));
            graph[edge[1]].add(new Node(edge[0], edge[2]));
        }
        
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;
        
        dfs(0, infected, -1, 1);
        
        return answer;
    }
    
    // 종류별로 재귀 탐색
    private void dfs(int depth, boolean[] curInfected, int lastPipe, int count) {
        
        answer = Math.max(answer, count);
        if (depth == K || count == N) return;
        
        // A, B, C
        for (int pipe = 1; pipe <= 3; pipe++) {
            if (pipe == lastPipe) continue;
            
            boolean[] nxtInfected = curInfected.clone();       // backtracking 대신
            
            int nextCount = spread(nxtInfected, pipe, count);
            if (nextCount > count) dfs(depth + 1, nxtInfected, pipe, nextCount);   // pruning
        }
    }
    
    // pipe를 열어 감염시킴
    private int spread(boolean[] infected, int pipe, int curCnt) {
        
        // 큐에 현재 감염된 모든 노드를 넣고 시작
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (infected[i]) q.add(i);
        }
        
        int newCnt = curCnt;
        
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node n : graph[cur]) {
                // 파이프 종류가 일치하고, 아직 감염되지 않은 노드라면 감염시킴
                if (n.p == pipe && !infected[n.n]) {
                    infected[n.n] = true;
                    newCnt++;
                    q.add(n.n);
                }
            }
        }
        
        return newCnt;
    }
    
    private static class Node {
        int n, p;
        public Node(int n, int p) {
            this.n = n;
            this.p = p;
        }
    }
}