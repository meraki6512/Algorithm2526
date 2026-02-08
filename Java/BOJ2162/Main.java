package Algorithm.Algorithm25.Java.BOJ2162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나중에 다시 시도
 */
public class Main {
    
    private static int[] parent;
    private static Line[] lines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        lines = new Line[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            lines[i] = new Line(x1, y1, x2, y2);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isOverLapped(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }
        
        int[] cnt = new int[N]; // 자식 노드 수
        for (int i = 0; i < N; i++) {
            cnt[find(i)]++;
        }

        int grp = 0, max = 0;
        for (int i = 0; i < N; i++) {
            if (cnt[i] > 0) grp++;
            max = Math.max(max, cnt[i]);
        }

        System.out.println(grp+"\n"+max);
    }

    private static boolean isOverLapped(Line l1, Line l2) {
        // 교점 X, Y
        // l1.x1,l2.x1 <= X <= l1.x2,l2.x2, ...<=Y<=...
        return true;
    }

    private static int find(int x){
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        else if (x < y) parent[x] = y;
        else  parent[y] = x;
    }

    private static class Line {
        int x1, x2, y1, y2;
        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}


/**
 *
 *     static boolean meet(Line l1, Line l2) {
 *         int res1 = ccw(l1, l2.x1, l2.y1) * ccw(l1, l2.x2, l2.y2);
 *         int res2 = ccw(l2, l1.x1, l1.y1) * ccw(l2, l1.x2, l1.y2);
 *
 *         if(res1 == 0 && res2 == 0) {
 *             // 일직선인 상황인데 겹치는지 안겹치는지 재확인 필요
 *             if(Math.min(l1.x1, l1.x2) <= Math.max(l2.x1, l2.x2) && Math.min(l2.x1, l2.x2) <= Math.max(l1.x1, l1.x2)
 *             && Math.min(l1.y1, l1.y2) <= Math.max(l2.y1, l2.y2) && Math.min(l2.y1, l2.y2) <= Math.max(l1.y1, l1.y2)) {
 *                 return true;
 *             }
 *             else return false;
 *         } else if(res1 <= 0 && res2 <= 0) {
 *             return true;
 *         } else {
 *             return false;
 *         }
 *     }
 *
 *     static int ccw(Line l1, int x3, int y3) {
 *         int x1 = l1.x1;
 *         int y1 = l1.y1;
 *         int x2 = l1.x2;
 *         int y2 = l1.y2;
 *
 *         int result = ( x1*y2 + x2*y3 + x3*y1 ) - ( x1*y3 + x2*y1 + x3*y2 );
 *
 *         if(result == 0) return 0;       // 일직선
 *         else if(result > 0) return 1;   // 반시계 방향
 *         else return -1;                 // 시계 방향
 *     }
 */