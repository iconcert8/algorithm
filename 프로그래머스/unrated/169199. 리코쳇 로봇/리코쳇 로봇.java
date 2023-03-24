import java.util.*;
class Solution {
    public int solution(String[] board) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        int maxRow = board.length;
        int maxCol = board[0].length();
        int startR = 0;
        int startC = 0;
        
        searchStart:for(int i = 0; i < maxRow; i++){
            for(int j = 0; j < maxCol; j++){
                if(board[i].charAt(j) == 'R'){
                    startR = i;
                    startC = j;
                    break searchStart;
                }
            }
        }
        
        // {r, c, dist}
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(new int[]{startR, startC, 0});
        visited.add(idxToString(startR, startC));
        while(!q.isEmpty()){
            int[] e = q.poll();
            int r = e[0], c = e[1], dist = e[2];
            if(board[r].charAt(c) == 'G') return dist;
            
            for(int i = 0; i < direction.length; i++){
                int nr = r;
                int nc = c;
                do{
                    if(!validateIdx(maxRow, maxCol, nr + direction[i][0], nc + direction[i][1])
                      || board[nr+direction[i][0]].charAt(nc+direction[i][1]) == 'D') break;
                    nr = nr + direction[i][0]; 
                    nc = nc + direction[i][1];
                }while(true);
                
                if(visited.contains(idxToString(nr, nc))) continue;
                visited.add(idxToString(nr, nc));
                q.add(new int[]{nr, nc, dist+1});
            }
        }
        
        return -1;
    }
    
    private String idxToString(int r, int c){
        return r+"_"+c;
    }
    
    private boolean validateIdx(int maxRow, int maxCol, int r, int c){
        if(r < 0 || c < 0) return false;
        if(r >= maxRow || c >= maxCol) return false;
        return true;
    }
}