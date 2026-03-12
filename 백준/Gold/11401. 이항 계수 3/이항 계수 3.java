// package Algorithm.Algorithm25.Java.BOJ11401;

import java.util.Scanner;

/**
 * 모듈러 연산에서 ...
 * 나눗셈 연산은 '없다' -> 역원 계산 필요
 * 곱셈 연산은 '있다'
 */
public class Main {

    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 4_000_000
        int K = sc.nextInt();

        // NCK
        //  y: N!
        // x: K!(N-K)!
        // ... NCK ... y * x^(-1) mod p

        long y = factorial(N);
        long x = factorial(K) * factorial(N-K) % MOD;

        // ... Fermat's ...
        // p: 소수
        // a^(p-1) = 1 (mod p)
        // a^(p-2) = a^(-1) (mod p)
        // ...
        // ... NCK ... y * x^(p-2) mod p

        System.out.println(y * pow(x, MOD-2) % MOD);
    }

    private static long factorial(int N) {
        long fac = 1L;
        while (N > 1) {
            fac = (fac * N) % MOD;
            N--;
        }
        return fac;
    }

    // 분할 정복
    // x^10 = x^5 * x^5
    // x^5 = x^2 * x^2 * x^1
    // b^e
    private static long pow(long b, long e) {
        if (e == 1) return b % MOD;

        long hb = pow(b, e >> 1);

        if (e % 2 == 1) return (hb * hb % MOD) * b % MOD;
        return (hb * hb % MOD);
    }
}
