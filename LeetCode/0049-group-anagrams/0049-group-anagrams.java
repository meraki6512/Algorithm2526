class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        // 2* 10^6
        
        List<Word> words = new ArrayList<>();
        int N = strs.length;
        for (String s : strs) {
            char[] a = s.toCharArray();
            Arrays.sort(a);
            words.add(new Word(s, new String(a)));
        }

        // sort - by rearranged strings
        Collections.sort(words);

        // // just to check sorted words
        // StringBuilder sb = new StringBuilder();
        // for (Word w : words) {
        //     sb.append(w.org).append("-").append(w.arrg).append("\n");
        // }
        // System.out.println(sb);

        // grouping by arranged strings

        List<List<String>> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int idx = -1;

        for (Word w : words) {
            if (map.containsKey(w.arrg)) {
                ans.get(map.get(w.arrg)).add(w.org);
            }
            else {
                map.put(w.arrg, ++idx);
                List<String> tmp = new ArrayList<>();
                tmp.add(w.org);
                ans.add(tmp);
            }
        }

        return ans;
    }

    private static class Word implements Comparable<Word>{
        String org, arrg;
        public Word(String org, String arrg) {
            this.org = org;
            this.arrg = arrg;
        }
        @Override
        public int compareTo(Word o){
            return this.arrg.compareTo(o.arrg);
        }
    }
}