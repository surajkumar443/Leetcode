class Solution {
    // right-to-left; s = stack of segment starts (equal prefix max); shrink the window's right end while cost > k
    public long countNonDecreasingSubarrays(int[] a, int k) {
        int n = a.length, lo = 0, hi = 0, r = n - 1, s[] = new int[n];
        long cost = 0, res = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int p; hi > lo && a[p = s[hi - 1]] <= a[i]; hi--)
                cost += (long) (a[i] - a[p]) * ((hi - 1 > lo ? s[hi - 2] : r + 1) - p);
            s[hi++] = i;
            while (cost > k) { cost -= a[s[lo]] - a[r]; if (--r < s[lo]) lo++; }
            res += r - i + 1;
        }
        return res;
    }
}