import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        int n = genres.length;  // 10_000
        
        Map<String, Integer> gpMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            gpMap.put(genres[i], gpMap.getOrDefault(genres[i], 0) + plays[i]);
        }
                
        List<Music> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Music(genres[i], gpMap.get(genres[i]), plays[i], i));
        }
        Collections.sort(list, (x, y) -> 
            x.gp != y.gp ? y.gp - x.gp : 
            x.p != y.p ? y.p - x.p :
            x.idx - y.idx
        );
        
        // 각 장르를 순회하면서
        // 3번째부터는 remove set에 넣기
        Set<Integer> remove = new HashSet<>();
        Map<String, Integer> gn = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Music cur = list.get(i);
            int cnt = gn.getOrDefault(cur.genre, 0);
            if (cnt >= 2) remove.add(cur.idx);
            gn.put(cur.genre, cnt+1);
        }
        
        // System.out.println(gn.entrySet());
        // for (Map.Entry<String, Integer> entry : gn.entrySet()) {
            // System.out.println(entry.getKey() + "=" + entry.getValue());
        // }

        int i = 0;
        int[] album = new int[n-remove.size()];
        for (Music music : list) {
            int idx = music.idx;
            if (remove.contains(idx)) continue;
            album[i++] = idx;
        }
        
        return album;
    }
    
    class Music {
        String genre;
        int gp, p, idx;
        Music(String genre, int gp, int p, int idx) {
            this.genre = genre;
            this.gp = gp;
            this.p = p;
            this.idx = idx;
        }
    }
}

// 1. 같은 장르의 재생 수 많은 거
// 2. 재생 수 많은 거
// 3. 인덱스 작은 거