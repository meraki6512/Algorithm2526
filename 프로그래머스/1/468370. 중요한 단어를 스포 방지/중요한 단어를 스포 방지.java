import java.util.HashSet;
import java.util.List;

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
        // still remain exposed
        HashSet<String> revealed 
            = new HashSet<>(List.of(spoiler_free_msg.toString().split(" ")));

        // reveal the spoiler one by one
        for (String word : message.split(" ")) {
            if (!revealed.contains(word)) { // hidden
                answer++;
                revealed.add(word);   // reveal
            }
        }

        return answer;
    }
}