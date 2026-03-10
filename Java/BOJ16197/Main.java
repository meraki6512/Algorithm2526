package Algorithm.Algorithm25.Java.BOJ16197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Coins {
        int c1X, c1Y, c2X, c2Y;
        int cnt;
        Coins(int c1X, int c1Y, int c2X, int c2Y, int cnt) {
            this.c1X = c1X;
            this.c1Y = c1Y;
            this.c2X = c2X;
            this.c2Y = c2Y;
            this.cnt = cnt;
        }
    }

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 20
        M = Integer.parseInt(st.nextToken());   // 20

        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'o') {
                    if (x1 < 0) {
                        x1 = i; y1 = j;
                    }
                    else {
                        x2 = i; y2 = j;
                    }
                }
            }
        }

        // 코인 두 개가 동시에 움직이기 때문에 하나의 객체로 관리
        Coins start = new Coins(x1, y1, x2, y2, 0);
        Queue<Coins> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Coins cur = que.poll();

            if (cur.cnt >= 10) {
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < 4; i++) {

                int nx1 = cur.c1X + dx[i];
                int ny1 = cur.c1Y + dy[i];
                int nx2 = cur.c2X + dx[i];
                int ny2 = cur.c2Y + dy[i];

                boolean c1Out = out(nx1, ny1);
                boolean c2Out = out(nx2, ny2);

                if (c1Out && c2Out) continue;
                else if (c1Out || c2Out) {
                    System.out.println(cur.cnt + 1);
                    return;
                }

                if (board[nx1][ny1] == '#') {
                    nx1 = cur.c1X;
                    ny1 = cur.c1Y;
                }
                if (board[nx2][ny2] == '#') {
                    nx2 = cur.c2X;
                    ny2 = cur.c2Y;
                }

                que.add(new Coins(nx1, ny1, nx2, ny2, cur.cnt + 1));
            }
        }
    }

    private static boolean out(int x, int y) {
        return x < 0 || N <= x || y < 0 || M <= y;
    }
}

/**
 * 5 12
 * ############
 * #o..........
 * ############
 * #o.#########
 * ############
 * -1
 * : cur에서 10 이상으로 걸러야 함
 */