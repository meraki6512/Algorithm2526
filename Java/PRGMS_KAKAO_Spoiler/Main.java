package Algorithm.Algorithm25.Java.PRGMS_KAKAO_Spoiler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String message_ex1 = "here is muzi here is a secret message";
        String message_ex2 = "my phone number is 01012345678 and may i have your phone number";
        int[][] spoiler_ranges_ex1 = {{0,3},{23,28}};
        int[][] spoiler_ranges_ex2 = {{5,5},{25,28},{34,40},{53,59}};

        Solution sol = new Solution();
        System.out.println(sol.solution(message_ex1, spoiler_ranges_ex1));
        System.out.println(sol.solution(message_ex2, spoiler_ranges_ex2));
    }
}

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;

        // keep the spoiler free
        StringBuilder spoiler_free_msg = new StringBuilder(message);
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                if (spoiler_free_msg.charAt(i) != ' ') {
                    spoiler_free_msg.setCharAt(i, '_');
                }
            }
        }

        HashSet<String> revealed = new HashSet<>(List.of(spoiler_free_msg.toString().split(" ")));

        for (String word : message.split(" ")) {
            if (!revealed.contains(word)) { // hidden
                answer++;
                revealed.add(word);   // reveal
            }
        }

        return answer;
    }
}