// You must implement a solution with a linear runtime complexity and use only constant extra space.
class Solution {
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int n : nums) x ^= n;
        return x;
    }
}