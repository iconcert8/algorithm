import java.util.*;
class Solution {
    static int[][] matrix;
    public int solution(int[][] matrix_sizes) {
        matrix = matrix_sizes;
        int[][] dp = new int[matrix.length][matrix.length];
        
        Arrays.stream(dp).forEach(arr->Arrays.fill(arr, 100_000_000));
        for(int i = 0; i < dp.length; i++) dp[i][i] = 0;
        for(int i = 0; i < dp.length-1; i++){
            dp[i][i+1] = count(i, i, i+1);
        }
        
        for(int r = 2; r < matrix.length; r++){
            for(int s = 0; s < dp.length; s++){
                int e = s+r;
                if(e >= dp.length) break;
                
                for(int m = s; m < e; m++){
                    if(dp[s][e] > dp[s][m] + dp[m+1][e] + count(s, m, e)){
                        dp[s][e] = dp[s][m] + dp[m+1][e] + count(s, m, e);
                    }
                }
            }
        }
        
        return dp[0][matrix.length-1];
    }
    
    public int count(int s, int m, int e){
        return matrix[s][0] * matrix[m][1] * matrix[e][1];
    }
}