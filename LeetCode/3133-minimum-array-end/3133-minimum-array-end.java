class Solution {
    public long minEnd(int n, int x) {
        char[] X = Integer.toBinaryString(x).toCharArray();
        String N = Integer.toBinaryString(n-1);
        int nl = N.length()-1;

        for (int i = X.length-1; i >= 0; i--) {
            if (X[i] == '0') {
                X[i] = N.charAt(nl);
                if (--nl < 0) break; 
            }
        }

        String res = N.substring(0, nl+1) + new String(X);
        return Long.parseLong(res, 2);
    }

    // standard answer is like...
    // x <<= 1, n >>= 1
    public long stdAns(int n, int x) {
        long ans = x;
        long pos = 1;
        long nl = n-1;

        while (nl > 0) {
            if ((x & pos) == 0) {
                ans |= (nl & 1) * pos;
                nl >>= 1;
            }
            pos <<= 1;
        }

        return ans;
    }
}