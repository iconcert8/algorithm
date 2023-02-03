import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int[] dp = new int[triangle.length];
        dp[0] = triangle[0][0];
        for(int i = 1; i < triangle.length; i++){
            for(int j = triangle[i].length - 1; j >= 0; j--){
                if(j == triangle[i].length - 1){
                    dp[j] = dp[j-1] + triangle[i][j];
                }else if(j == 0){
                    dp[j] += triangle[i][j];
                }else{
                    dp[j] = Math.max(dp[j-1]+triangle[i][j], dp[j]+triangle[i][j]);
                }
                
                // System.out.println(Arrays.toString(dp));
                
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < dp.length; i++){
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
    
    
}

