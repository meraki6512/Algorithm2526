package Algorithm.Algorithm25.Java.BOJ1790;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();    // ^8
        long k = scanner.nextLong();    // ^9

        // 예외 처리
        if (getDigit(N) < k) {
            System.out.println(-1);
            return;
        }

        // n까지만 나열
        long n = binarySearch(N, k);

        // 문자열 n에서의 인덱스 구하기
        String s = String.valueOf(n);
        int idx = s.length() - 1;
        int diff = (int) (getDigit(n) - k);
        idx -= diff;

        System.out.println(s.charAt(idx));

    }

    // ret: "n까지 나열한 수의 길이" >= k인 최솟값 n
    private static long binarySearch(long N, long k) {
        long l = 1, r = N;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            int cmp = Long.compare(getDigit(mid), k);

            if (cmp < 0) l = mid + 1;
            else r = mid - 1;
        }

        return l;
    }

    // return: "n까지 나열한 수의 길이"
    private static long getDigit(long n) {
        long res = 0;
        long x = 1; // 1, 10, 100, ...

        while (x <= n) {
            res += (n - x + 1);
            x *= 10;
        }

        return res;
    }
}

// k번째 수 = k보다는 작은 숫자 구성 수

// 가로로?
// 1의 자릿수: 9개
// 2(10-99): 90*2 = 180개
// 3(-999): 900*3 = 2700개 ...
// 8: 9*10^7*8 = 720_000_000개
// 자릿수 x로 만들 수 있는 수의 길이 = 9 * pow(10, x-1) * x
//
// 세로로!
// 1234567891011121314151617181920212223...
// 16
// 1의 자릿수짜리: 16 - 1 + 1 = 16
// 2의 자릿수짜리: 16 - 1-9 + 1 = 7
// 120
// 1의 자릿수짜리: 120개 : n - x + 1
// 2의 자릿수짜리: 120 - 9 = 111개: 120 - 10 + 1
// 3의 자릿수짜리: 111 - 90 = 21개: 120 - 100 + 1