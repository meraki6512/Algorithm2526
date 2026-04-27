import java.util.*;

class Solution {
    
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    private int[] result;
    private int[][] land;
    private int n, m;
    
    public int solution(int[][] Land) {
        
        land = Land;
        n = land.length;
        m = land[0].length;
        result = new int[m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j]==1) {
                    bfs(i, j);
                }
            }
        }       
        
        int answer = 0;
        for (int res : result) {
            answer = Math.max(answer, res);
        }
        
        return answer;
    }
    
    private void bfs(int stx, int sty) {
        
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(stx, sty));
        land[stx][sty] = 0;
        Set<Integer> set = new HashSet<>();
        int amt = 0;
        
        while (!que.isEmpty()) {
            Point cur = que.poll();
            set.add(cur.y);
            amt++;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (inRange(nx, ny) && land[nx][ny]==1) {
                    que.add(new Point(nx, ny));
                    land[nx][ny] = 0;
                }
            }
        }
        
        for (Integer r : set) {
            result[r] += amt;
        }
    }
    
    private class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}