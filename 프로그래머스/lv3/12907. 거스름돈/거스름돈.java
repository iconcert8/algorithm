import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        Arrays.sort(money);
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int i = 0; i < money.length; i++){
            int m = money[i];
            for(int k = m; k <= n; k++){
                dp[k] = dp[k] + dp[k-m];
            }
        }
        
        return dp[n] % 1_000_000_007;
    }
}