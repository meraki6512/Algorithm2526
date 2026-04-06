import java.util.*;

class Solution {
    
    private int N, M, L, INF = Integer.MAX_VALUE;
    private int[] elev;
    private String[] grid;
    
    public int solution(int h, String[] _grid, int[][] panels, int[][] seqs) {
        
        grid = _grid;
        N = grid.length;
        M = grid[0].length();
        L = panels.length;
        elev = null;
        
        for (int i = 0; i < N && elev == null; i++) {
            for (int j = 0; j < M; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    elev = new int[]{i, j};
                }
            }
        }
        
        // 1. 미리 거리 구해두기
        // 패널과 엘레베이터 간
        int[][] elevDist = getDist(elev[0], elev[1]);
        // 패널 간
        int[][][] panelDist = new int[L][N][M]; // 패널 l부터의 모든 거리
        for (int i = 0; i < L; i++) { 
            panelDist[i] = getDist(panels[i][1]-1, panels[i][2]-1);
        }

        // 2. 층계산 포함 최종 거리 구하기
        int[][] dist = new int[L][L];
        for (int i = 0; i < L; i++) {       // 출발
            for (int j = i+1; j < L; j++) { // 도착
                
                // 층
                int fi = panels[i][0] - 1;
                int fj = panels[j][0] - 1;
                // 도착 행, 열
                int rj = panels[j][1] - 1;
                int cj = panels[j][2] - 1;
                
                if (fi == fj) {
                    // 같은 층이면
                    dist[i][j] = dist[j][i] = panelDist[i][rj][cj];
                } else {
                    // 다른 층이면 start->elv + ff + elv->end
                    dist[i][j] = dist[j][i] = 
                        panelDist[i][elev[0]][elev[1]] 
                        + Math.abs(fi-fj) 
                        + elevDist[rj][cj];
                }
            }
        }
        
        // 3. 비트 마스크 - 선행 조건
        int[] req = new int[L];
        for (int[] seq : seqs) {    // a->b
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            req[b] |= (1<<a);       // b를 켜려면 a가 필요함
        }
        
        // 4. DP
        // dp[mask][u] = 현재 활성화 패널 mask, 마지막 위치가 u일때 최소 시간
        int[][] dp = new int[1<<L][L];
        for (int i = 0; i < (1<<L); i++) {
            Arrays.fill(dp[i], INF);
        }
        
        // 초기: 0번위치(패널1)에서 시작, mask = 0 (켜진 패널 없음)
        dp[0][0] = 0;
        
        for (int mask = 0; mask < (1<<L); mask++) { // 2^15
            for (int u = 0; u < L; u++) {           // 15
                if (dp[mask][u] == INF) continue;   // 도달 불가
                
                for (int v = 0; v < L; v++) {
                    if ((mask & (1<<v)) != 0) continue; // 이미 활성화한 패널이면 X
                    if ((mask & req[v]) != req[v]) continue; // 선행 조건 불만족이면 X (*일반 TSP에서 추가된 부분)
                    
                    int nmask = mask | (1<<v);   // 이동 가능하면 v번 패널 켠 후
                    dp[nmask][v] = Math.min(dp[nmask][v], dp[mask][u] + dist[u][v]);    // 최소 시간 업데이트
                }
            }
        }
        
        // 5. 모든 패널을 켰을 때 (mask = 2^L-1) 최솟값 탐색
        int ans = INF;
        for (int i = 0; i <L; i++) {
            ans = Math.min(dp[(1<<L)-1][i], ans);
        }
        return ans;
        
    }
    
    // start로부터 다른 모든 점까지의 거리를 구하는 함수
    private int[][] getDist(int sttr, int sttc){
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        int[][] res = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(res[i], INF);
        res[sttr][sttc] = 0;
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{sttr, sttc});
    
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int r = cur[0], c = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];
                
                if (inRange(nr, nc) && canMove(nr, nc)) {
                    if (res[nr][nc] > res[r][c] + 1){   // 더 빠르게 갈 경우만
                        res[nr][nc] = res[r][c] + 1;
                        que.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return res;
    }
    
    private boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
    
    private boolean canMove(int x, int y) {
        return grid[x].charAt(y) != '#';
    }
}

// 외판원 순회(TSP): "모든 도시를 한 번씩 다 방문하는데 걸리는 최소 비용 찾기"

// 추가된 부분: if ((mask & req[v]) != req[v]) ...
// : 내가 지금까지 켠 패널들(mask) 중에, v번 패널을 켜기 위해 꼭 필요한 패널들(req[v])이 전부 포함되어 있는가?

// ex.
//   mask    : 0 1 1 1  (내가 켠 패널들: 0, 1, 2번)
// & req[3]  : 0 0 1 1  (3번을 위해 필요한 패널들: 0, 1번)
// -------------------
//   결과     : 0 0 1 1  (겹치는 부분)