class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // what xor does? 0 ^ 1 = 1 
        int n = nums.length; 

        if(n <= 2) return n; 
        // since I have permutation of n
        // 1
        // 01
        // 11 
        // 1000 
        // so basically for any `n` I can have all comnbinations possible for the max no of set bits in any n. 
        // so If i have been given 1000 -> then I can make 1111 using 1000 & 111 
        // so final ans is what ? [0, (2 ^ (pow of 2 <= n) + 1) - 1]

        int bitWidth = 31; 
        for(int i = 31; i>= 0; i--) {
            if(((n >> i) & 1) == 1) {
                break; 
            }
            bitWidth--; 
        }

        return (1 << (bitWidth + 1)); 
    }
}