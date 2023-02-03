import java.util.*;
class Solution {
    public int[] solution(int target) {
        Set<Integer> scoreSet = new HashSet<>();
        scoreSet.add(50);
        for(int n = 1; n <= 20; n++){
            scoreSet.add(n);
            scoreSet.add(n*2);
            scoreSet.add(n*3);
        }
        
        int[][] dp = new int[target+1][2];
        for(int n = 1; n <= target; n++) Arrays.fill(dp[n], 1_000_000);
        for(int n = 1; n <= target; n++){
            if(n <= 60){
                if(isSingle(n)){
                    dp[n][0] = 1;
                    dp[n][1] = 1;
                    continue;
                }
                if(scoreSet.contains(n)){
                    dp[n][0] = 1;
                    dp[n][1] = 0;
                    continue;
                }
            }
            
            for(int i = 1; i <= n/2; i++){
                if(dp[n][0] == dp[i][0] + dp[n-i][0]){
                    if(dp[n][1] < dp[i][1] + dp[n-i][1]){
                        dp[n][1] = dp[i][1] + dp[n-i][1];
                    }
                    continue;
                }
                if(dp[n][0] > dp[i][0] + dp[n-i][0]){
                    dp[n][0] = dp[i][0] + dp[n-i][0];
                    dp[n][1] = dp[i][1] + dp[n-i][1];
                }
            }
        }
        
        return dp[target];
    }
    
    public boolean isSingle(int n){
        if(n >= 1 && n <= 20) return true;
        if(n == 50) return true;
        return false;
    }
}