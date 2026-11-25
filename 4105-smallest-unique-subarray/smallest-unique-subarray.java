class Solution {
    private long modPow(long base, int exp, long modulus) {
        long res = 1;

        while (exp > 0) {
            if ((exp & 1) != 0) {
                res = res * base % modulus;
            }
            base = base * base % modulus;
            exp >>= 1;
        }

        return res;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int low = 1;
        int high = nums.length + 1;
        int ans = -1;

        final long base = 100003;
        final long modulus = 1000000007;

        while (low < high) {
            int mid = low + (high - low) / 2;

            Set<Long> unique = new HashSet<>();
            Set<Long> non_unique = new HashSet<>();

            long subarray = 0;
            long pow1 = modPow(base, mid - 1, modulus);

            for (int i = 0; i + mid <= nums.length; i++) {
                if (i == 0) {
                    for (int j = i; j < i + mid; j++) {
                        subarray = (subarray * base + nums[j]) % modulus;
                    }
                } else {
                    subarray = (subarray - nums[i - 1] * pow1 % modulus + modulus) % modulus;
                    subarray = (subarray * base + nums[i + mid - 1]) % modulus;
                }

                if (non_unique.contains(subarray)) {
                    continue;
                } else if (unique.contains(subarray)) {
                    unique.remove(subarray);
                    non_unique.add(subarray);
                } else {
                    unique.add(subarray);
                }
            }

            if (!unique.isEmpty()) {
                high = mid;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}