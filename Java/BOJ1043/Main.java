package Algorithm.Algorithm25.Java.BOJ1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, M, K;
        boolean[] personNoticed, visitedParty;
        List<Integer> personNoticedIdx = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // ppl
        M = Integer.parseInt(st.nextToken());   // prty
        personNoticed = new boolean[N+1];
        visitedParty = new boolean[M+1];
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());   // knows the truth
        for (int i = 0; i < K; i++) {
            personNoticedIdx.add(Integer.parseInt(st.nextToken()));
        }

        int n, p;
        List<Integer>[] people = new List[N+1];
        for (int i = 0; i <= N; i++) people[i] = new ArrayList<>();
        List<Integer>[] parties = new List[M+1];
        for (int i = 0; i <= M; i++) parties[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            for (int j = 0; j <n; j++) {
                p = Integer.parseInt(st.nextToken());
                parties[i].add(p);
                people[p].add(i);
            }
        }

        Queue<Integer> que = new LinkedList<>();
        for (Integer cur : personNoticedIdx) {
            for (Integer party : people[cur]) {
                que.add(party);
            }
        }

        while (!que.isEmpty()) {
            int curParty = que.poll();
            visitedParty[curParty] = true;

            for (Integer curPerson : parties[curParty]) {
                if (!personNoticed[curPerson]) {
                    personNoticed[curPerson] = true;

                    for (Integer party : people[curPerson]) {
                        if (!visitedParty[party]) {
                            que.add(party);
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= M; i++) if (!visitedParty[i]) ans ++;
        System.out.println(ans);

    }
}
