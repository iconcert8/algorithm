import java.util.*;
class Solution {
    
    static int totalCost = Integer.MAX_VALUE;
    //dir 0,1,2,3 하,우,상,좌
    static int[][] directions = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    static int INF = 1_000_000;
    
    public int solution(int[][] board) {
        
        int[][][] dp = new int[4][board.length][board.length];
        Arrays.stream(dp).forEach(
            arr1->{
                Arrays.stream(arr1).forEach(arr2->{Arrays.fill(arr2, INF);});
            }
        );
        
        // {r, c, cost, dir}
        Queue<int[]> q = new LinkedList<>();
        // dir -> 하,우,상,좌
        for(int d = 0; d < 4; d++){
            dp[d][0][0] = 0;
        }
        if(board[0][1] != 1){
            q.add(new int[]{0, 1, 100, 1});
            dp[1][0][1] = 100;
        }
        if(board[1][0] != 1){
            q.add(new int[]{1, 0, 100, 0});
            dp[0][1][0] = 100;
        }
        
        while(!q.isEmpty()){
            int[] qValue = q.poll();
            int r = qValue[0]; int c = qValue[1];
            int cost = qValue[2]; int dir = qValue[3];
            
            for(int d = 0; d < 4; d++){
                int nr = r + directions[d][0];
                int nc = c + directions[d][1];
                int nCost = dir != d ? cost + 600 : cost + 100;
                
                if(isInnerIdx(board.length, nr, nc)){
                    if(board[nr][nc] == 1) continue;
                    if(dp[d][nr][nc] <= nCost) continue;
                    
                    q.add(new int[]{nr, nc, nCost, d});
                    dp[d][nr][nc] = nCost;
                }
            }
        }
        
        int answer = dp[0][board.length-1][board.length-1];
        
        for(int d = 1; d < 4; d++){
            answer = Math.min(answer, dp[1][board.length-1][board.length-1]);
        }
        
        return answer;
    }
            
    public boolean isInnerIdx(int len, int r, int c){
        return r > -1 && r < len && c > -1 && c < len;
    }
}