class Solution {

    public int mySqrt(int x) {
        if (x <= 1) return x;

        int s = 1, e = x-1, m;

        while (s <= e) {
            m = s + (e-s)/2;
            if ((long)m*m > (long)x) e = m-1;
            else if (m*m == x) return m;
            else s = m+1;
        }

        return e;
    }

    public int linearSqrt(int x) {
        int ans = 0;
        for (long i = 1; i*i <= x; i++) {
            ans = (int)i;
        }
        return ans;
    }
}