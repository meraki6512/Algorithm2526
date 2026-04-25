import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        
        for (String[] clth : clothes) {
            String name = clth[0];
            String type = clth[1];
            
            map.put(type, map.getOrDefault(type, 0) + 1);
            set.add(type);
        }
        
        int answer = 1;
        List<String> list = new ArrayList<>(set);
        for (String s : list) {
            answer *= (map.get(s) + 1);
        }
        
        return answer-1;
    }
}

// 0 a b
// 0 c
// 3*2-1 = 5

// 0 a b c
// 0
// 4*1-1 = 3