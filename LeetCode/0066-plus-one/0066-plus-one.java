class Solution {
    public int[] plusOne(int[] digits) {

        int n = digits.length, carry = 0, cur;
        List<Integer> list = new ArrayList<>();
        digits[n-1]++;

        for (int i = n-1; i >=0; i--) {
            cur = digits[i] + carry;
            list.add(cur % 10);
            carry = cur / 10;     
        }

        if (carry > 0) list.add(carry);

        n = list.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(n-1-i);
        }
        
        return ans;
    }
}