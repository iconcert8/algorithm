import java.util.*;
class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] map = new int[m+1][n+1]; map[1][1] = 1;
        for(int[] puddle : puddles) map[puddle[0]][puddle[1]] = -1;
        
        for(int i = 2; i <= m; i++){
            if(map[i-1][1] > 0 && map[i][1] != -1)
                map[i][1] = 1;
            else
                break;
        }
        
        for(int i = 2; i <= n; i++){
            if(map[1][i-1] > 0 && map[1][i] != -1)
                map[1][i] = 1;
            else
                break;
        }
        
        // Arrays.stream(map).forEach(arr->{
        //    System.out.println(Arrays.toString(arr)); 
        // });
        
        for(int y = 2; y <= n; y++){
            for(int x = 2; x <=m; x++){
                if(map[x][y] == -1) continue;
                
                int up = map[x][y-1] == -1 ? 0 : map[x][y-1];
                int left = map[x-1][y] == -1 ? 0 : map[x-1][y];
                
                map[x][y] = (up+left) % 1_000_000_007;
            }
        }
        
//         Queue<int[]> q = new LinkedList<>();
//         q.add(new int[]{1, 1});
        
//         while(!q.isEmpty()){
//             int[] e = q.poll();
//             int x = e[0];
//             int y = e[1];
//             if(x == m && y == n){
//                 answer++;
//                 continue;
//             }
            
//             //right
//             if(x+1 <= m){
//                 if(map[x+1][y] == 0) q.add(new int[]{x+1, y});
//             }
            
//             //down
//             if(y+1 <= n){
//                 if(map[x][y+1] == 0) q.add(new int[]{x, y+1});
//             }
//         }
        
        return map[m][n];
    }
}