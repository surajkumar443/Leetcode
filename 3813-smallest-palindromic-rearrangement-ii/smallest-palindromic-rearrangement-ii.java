class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length()/2;
        int arr[] = new int[26];
        for(char ch : s.toCharArray()){
            int idx = ch-'a';
            arr[idx]++;
        }
        
        String mid="";

        for(int i=0;i<26;i++){
            if((arr[i]&1)==1){
                mid=(char)(i+'a')+"";
            }

            arr[i]/=2;
        }
        long tot = ways(n, arr, k);
        if(tot<k)return "";

        StringBuilder left = new StringBuilder();

        while(n>0){
            for(int i=0;i<26;i++){
                if(arr[i]==0)continue;

                arr[i]--;

                long way = ways(n-1, arr, k);

                if(way>=k){
                    left.append((char)('a'+i));
                    n--;
                    break;
                }
                else{
                    k-=way;
                    arr[i]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        ans.append(mid);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();

    }

    public long ncr(int n, int r, int k){
        long res=1;
        r = Math.min(r, n-r);

        for(int i=1;i<=r;i++){
            res = res*(n-i+1)/i;

            if(res>k){
                return k+1;
            }
        }

        return res;
    }

    public long ways(int n, int f[], int k){
        long tot=1;
        for(int i=0; i<26; i++){
            tot*=ncr(n, f[i], k);
            if(tot>k)return k+1;
            n-=f[i];
        }

        return tot;
    }

}