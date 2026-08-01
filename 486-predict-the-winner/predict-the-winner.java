class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        for (int left = n - 1; left >= 0; left--) {
            dp[left] = nums[left];

            for (int right = left + 1; right < n; right++) {
                dp[right] = Math.max(
                    nums[left] - dp[right],
                    nums[right] - dp[right - 1]
                );
            }
        }

        return dp[n - 1] >= 0;
    }
}