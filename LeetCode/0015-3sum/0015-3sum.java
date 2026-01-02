class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
    
        int n = nums.length, l, r, t, s;
        Arrays.sort(nums);

        // take i first
        for (int i = 0; i < n-2; i++) {

            if (i > 0 && nums[i] == nums[i-1]) continue;
            
            l = i + 1;
            r = n - 1;
            t = -nums[i];  
            
            while (l < r) {
                s = nums[l] + nums[r];
                if (s == t) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    
                    while (l < r && nums[l]==nums[l-1]) l++;
                    while (l < r && nums[r]==nums[r+1]) r--;
                }
                else if (s < t) l++;
                else r--;
            }
        }

        return ans;
    }
}