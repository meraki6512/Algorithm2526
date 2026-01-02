class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        int l = 0;
        Set<Character> set = new HashSet<>();

        for (int r = 0; r < n; r ++) {
            char c = s.charAt(r);

            if (set.contains(c)) {
                while (set.contains(c)) set.remove(s.charAt(l++));
                set.add(c);
            }
            else {
                set.add(c);
                ans = Math.max(ans, r-l+1);
            }
        }

        return ans;
    }
}