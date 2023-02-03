import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int INF = 10_000_000;
        int maxAlp = alp;
        int maxCop = cop;
        
        for(int[] problem : problems){
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        int[][] dp = new int[maxAlp+2][maxCop+2];
        for(int[] arr : dp) Arrays.fill(arr, INF);
        
        dp[alp][cop] = 0;
        for(int a = alp; a <= maxAlp; a++){
            for(int c = cop; c <= maxCop; c++){
                if(dp[a][c+1] > dp[a][c] + 1){
                    dp[a][c+1] = dp[a][c] + 1;
                }
                
                // if(c == maxCop){
                    if(dp[a+1][c] > dp[a][c] + 1){
                        dp[a+1][c] = dp[a][c] + 1;
                    } 
                // }
                
                for(int[] problem : problems){
                    if(a >= problem[0] && c >= problem[1]){
                        int _aLeng = Math.min(maxAlp, a+problem[2]);
                        int _cLeng = Math.min(maxCop, c+problem[3]);
                        if(dp[_aLeng][_cLeng] > dp[a][c] + problem[4]){
                            dp[_aLeng][_cLeng] = dp[a][c] + problem[4];
                        }
                    }
                }    
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}