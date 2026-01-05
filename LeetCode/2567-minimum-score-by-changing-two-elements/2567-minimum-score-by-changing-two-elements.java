class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        if (n==3) return 0;

        Arrays.sort(nums);
        
        return Math.min((nums[n-1]-nums[2]), 
            Math.min((nums[n-2]-nums[1]), (nums[n-3]-nums[0])));
    }
}

// first, make it as sorted one
// [25,31,65,72,74,79]

// then, ans must be the min among these
// [n-1]-[2]
// [n-2]-[1]
// [n-3]-[0]