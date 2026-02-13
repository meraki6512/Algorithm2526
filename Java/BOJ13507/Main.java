package Algorithm.Algorithm25.Java.BOJ13507;

import java.util.Scanner;

// 13507
// partial sum || Hash || Trie...?
// Rolling Hash || Suffix Array + LCP

public class Main {

    static class Node {
        Node[] children = new Node[26];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();                       // 1500
        String goodBadStr = sc.nextLine();              // 26: 0=bad
        boolean[] isBad = new boolean[26];
        for (int i = 0; i < goodBadStr.length(); i++) isBad[i] = goodBadStr.charAt(i) == '0';
        int k = sc.nextInt();                           // bad num <= k

        // substr -> hash || Trie

        Node root = new Node();
        int ans = 0;

        // start point = i := 0 ... s.length-1
        for (int i = 0; i < s.length(); i++) {
            Node cur = root;
            int badCnt = 0;

            // from i to j
            for (int j = i; j < s.length(); j++) {
                int idx = s.charAt(j) - 'a';

                if (isBad[idx]) badCnt++;
                if (badCnt > k) break;

                if (cur.children[idx] == null) {
                    cur.children[idx] = new Node();     // distinct
                    ans ++;
                }

                cur = cur.children[idx];
            }
        }

        System.out.println(ans);
    }
}
