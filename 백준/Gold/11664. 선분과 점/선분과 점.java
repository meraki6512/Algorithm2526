// package Algorithm.Algorithm25.Java.BOJ11664;

import java.util.Scanner;

public class Main {

    private static class Point {
        double x, y, z;    // 0-10000
        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point A, B, C;
        // 선분 AB, 점 C
        A = new Point(sc.nextInt(), sc.nextInt(), sc.nextInt());
        B = new Point(sc.nextInt(), sc.nextInt(), sc.nextInt());
        C = new Point(sc.nextInt(), sc.nextInt(), sc.nextInt());

        Point Ans = ternarySearch(A, B, C);
        System.out.printf("%.10f\n", getDistance(Ans, C));
    }

    private static Point ternarySearch(Point A, Point B, Point C) {

        // AB max: 10000 root3: ~~ 17320
        // ternarySearch -> 2/3
        // 17320 * (2/3)^k < 10^(-6)
        // k >= ~~ 76

        for (int i = 0; i < 100; i++) {

            // divide AB into 3 parts
            // A M N B
            // M: A + 1/3AB
            // N: B - 1/3AB

            double Dx = (B.x - A.x) / 3.0;
            double Dy = (B.y - A.y) / 3.0;
            double Dz = (B.z - A.z) / 3.0;

            Point M = new Point(A.x + Dx, A.y + Dy, A.z + Dz);
            Point N = new Point(B.x - Dx, B.y - Dy, B.z - Dz);

            // cmp: M,C vs N,C
            // choose closer one

            int cmp = Double.compare(getDistance(M, C), getDistance(N, C));
            if (cmp >= 0) A = M;
            else B = N;
        }

        return A;
    }

    private static double getDistance(Point P, Point Q) {
        return Math.sqrt(Math.pow(P.x - Q.x, 2) + Math.pow(P.y - Q.y, 2) + Math.pow(P.z - Q.z, 2));
    }

    /**
     * O(1) 벡터 이용 풀이도 가능
     * - C가 A 외쪽일 경우: AC
     * - C가 B 외쪽일 경우: BC
     * - C가 AB 사이일 경우: 수직 거리 (AC를 AB에 투영한 길이 p를 구한 후 피타고라스 정리)
     */
    private static void geometricSol(Point A, Point B, Point C) {
        // Vector 정의
        Point AB = getVector(A, B);
        Point AC = getVector(A, C);
        Point BA = getVector(B, A);
        Point BC = getVector(B, C);

        if (getDistance(A, B) == 0) {
            System.out.printf("%.10f\n", getDistance(A, C));
            return;
        }

        // Case별 처리
        double ans;
        if (dotProduct(AB, AC) <= 0) ans = getDistance(A, C);
        else if (dotProduct(BA, BC) <= 0) ans = getDistance(B, C);
        else {
            double p = dotProduct(AB, AC) / getDistance(A, B);
            ans = Math.sqrt(Math.abs(
                    Math.pow(getDistance(A, C), 2) - Math.pow(p, 2)));
        }

        System.out.printf("%.10f\n", ans);
    }

    // 3차원 벡터 (p1 -> p2)
    private static Point getVector(Point p1, Point p2) {
        return new Point(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
    }

    // 3차원 벡터 내적 (Dot Product = |v1||v2| cosX )
    // <= 0 : obtuse
    // > 0 : acute
    private static double dotProduct(Point v1, Point v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
}
