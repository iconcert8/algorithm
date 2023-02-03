import java.util.*;
class Solution {
    static int result = Integer.MAX_VALUE;
    
    public int solution(String numbers) {
        
        int[][] pad = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {-1,0,-1}
        };
        
        // 최단거리들을 미리 구한다. floyd 알고리즘
        int[][] dp = new int[10][10];
        for(int[] d : dp) Arrays.fill(d, 1_000_000);
        for(int i = 0; i < dp.length; i++) dp[i][i] = 1;
        
        int[] rDir = new int[]{1,1,-1,-1, 1,0,-1,0};
        int[] cDir = new int[]{1,-1,1,-1, 0,1,0,-1};
        
        // 일단 기본거리 초기화
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 3; c++){
                if(pad[r][c] == -1) continue;
                int from = pad[r][c];
                for(int d = 0; d < 8; d++){
                    int nr = r + rDir[d];
                    int nc = c + cDir[d];
                    if(nr < 0 || nr >= 4 || nc < 0 || nc >= 3) continue;
                    int to = pad[nr][nc];
                    if(to == -1) continue;
                    if(d < 4){
                        dp[from][to] = 3; //대각선 초기화
                    }else{
                        dp[from][to] = 2; //상하좌우 초기화
                    }
                }
            }
        }
        
        // floyd 알고리즘
        for(int n = 0; n < 10; n++){
            for(int f = 0; f < 10; f++){
                for(int t = 0; t < 10; t++){
                    if(f == t) continue;
                    if(f == n || t == n) continue;
                    
                    if(dp[f][t] > dp[f][n] + dp[n][t]){
                        dp[f][t] = dp[f][n] + dp[n][t];
                        dp[t][f] = dp[f][n] + dp[n][t];
                    }
                }
            }
        }
        
        // 주어진 numbers 계산
        //Queue int[]{left, right, cost}
        Map<String, int[]> m = new HashMap<>();
        m.put("left,right", new int[]{4, 6, 0});
        
        String[] numberStrArray = numbers.split("");
        for(int i = 0; i < numberStrArray.length; i++){
            int to = Integer.parseInt(numberStrArray[i]);
            m = bfs(dp, m, to);
        }
        
        int minCost = Integer.MAX_VALUE;
        for(Map.Entry<String, int[]> e : m.entrySet()){
            minCost = Math.min(minCost, e.getValue()[2]);
        }
        
        return minCost;
    }
    
    Map<String, int[]> bfs(int[][] dp, Map<String, int[]> m, int to){
        Map<String, int[]> newM = new HashMap<>();
        for(Map.Entry<String, int[]> e : m.entrySet()){
            String key = e.getKey();
            int[] arr = e.getValue();

            int left = arr[0];
            int right = arr[1];
            int cost = arr[2];
            int leftCost = cost + dp[left][to];
            int rightCost = cost + dp[right][to];

            if(left == to){
                newM.merge(to+","+right, new int[]{to, right, leftCost}, (v1, v2)->{
                    if(v1[2] < v2[2]) return v1;
                    return v2;
                });
                newM.merge(right+","+to, new int[]{to, right, leftCost}, (v1, v2)->{
                    if(v1[2] < v2[2]) return v1;
                    return v2;
                });
                continue;
            }
            if(right == to){
                newM.merge(left+","+to, new int[]{left, to, rightCost}, (v1, v2)->{
                    if(v1[2] < v2[2]) return v1;
                    return v2;
                });
                newM.merge(to+","+left, new int[]{left, to, rightCost}, (v1, v2)->{
                    if(v1[2] < v2[2]) return v1;
                    return v2;
                });
                continue;
            }
            
            newM.merge(to+","+right, new int[]{to, right, leftCost}, (v1, v2)->{
                if(v1[2] < v2[2]) return v1;
                return v2;
            });
            newM.merge(right+","+to, new int[]{to, right, leftCost}, (v1, v2)->{
                if(v1[2] < v2[2]) return v1;
                return v2;
            });
            newM.merge(left+","+to, new int[]{left, to, rightCost}, (v1, v2)->{
                if(v1[2] < v2[2]) return v1;
                return v2;
            });
            newM.merge(to+","+left, new int[]{left, to, rightCost}, (v1, v2)->{
                if(v1[2] < v2[2]) return v1;
                return v2;
            });
        }
        
        return newM;
    }
}