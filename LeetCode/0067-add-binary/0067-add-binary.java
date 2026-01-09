class Solution {
    public String addBinaryFirstSol(String a, String b) {

        Stack<Integer> A = new Stack<>();
        for (char c : a.toCharArray()) A.push(c-'0');
        Stack<Integer> B = new Stack<>();
        for (char c : b.toCharArray()) B.push(c-'0');

        Stack<Integer> ans = new Stack();
        int carry = 0, cur;

        while (!A.isEmpty() && !B.isEmpty()) {
            cur = A.pop() + B.pop() + carry;
            ans.push(cur % 2);
            carry = cur / 2;
        }

        while (!A.isEmpty()) {
            cur = A.pop() + carry;
            ans.push(cur % 2);
            carry = cur / 2;
        }

        while (!B.isEmpty()) {
            cur = B.pop() + carry;
            ans.push(cur % 2);
            carry = cur / 2;
        }

        if (carry == 1) ans.push(1);

        StringBuilder sb = new StringBuilder();
        while (!ans.isEmpty()) {
            sb.append(ans.pop());
        }
        
        return sb.toString();
    }

    public String addBinary(String a, String b) {
        
        if (a.length() < b.length()) return addBinary(b, a);

        int carry = 0, i = a.length(), j = b.length();
        char[] ans = new char[i+1];

        while (i > 0) {
            carry += a.charAt(--i) - '0';
            if (j > 0) carry += b.charAt(--j) - '0';
            ans[i+1] = (char)(carry % 2 + '0');
            carry /= 2;
        }

        if (carry == 1) {
            ans[0] = '1';
            return new String(ans);
        }
        
        return new String(ans, 1, a.length());
    }
}