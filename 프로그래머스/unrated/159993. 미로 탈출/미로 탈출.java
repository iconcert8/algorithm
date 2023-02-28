import java.util.*;
class Solution {
    static int[][] direction = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    public int solution(String[] maps) {
        int[] start = null;
        searchStart : for(int i = 0; i < maps.length; i++){
            for(int j = 0; j < maps[i].length(); j++){
                char c = maps[i].charAt(j);
                if(c == 'S'){
                    start = new int[]{i, j};
                    break searchStart;
                }
            }
        }
        
        // Search to Lever. 
        // Object[row, col, time]
        Object[] lever = search(start[0], start[1], 0, 'L', maps);
        if(lever == null) return -1;
        
        // Search to Exit. 
        // Object[row, col, time]
        Object[] exit = search((int)lever[0], (int)lever[1], (int)lever[2], 'E', maps);
        if(exit == null) return -1;
        return (int)exit[2];
    }
    
    private Object[] search(int startRow, int startCol, int startTime, char dest, String[] maps){
        Queue<Object[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(new Object[]{startRow, startCol, startTime});
        visited.add(startRow+"_"+startCol);
        Object[] destObj = null;
        while(!q.isEmpty()){
            Object[] e = q.poll();
            int row = (int)e[0], col = (int)e[1], time = (int)e[2];
            if(maps[row].charAt(col) == dest){
                destObj = e;
                break;
            }
            
            for(int d = 0; d < 4; d++){
                int nextRow = row + direction[d][0];
                int nextCol = col + direction[d][1];
                if(nextRow > maps.length-1 || nextRow < 0) continue;
                if(!validateIdx(nextRow, nextCol, maps.length-1, maps[nextRow].length()-1)) continue;
                if(maps[nextRow].charAt(nextCol) == 'X') continue;
                if(visited.contains(nextRow+"_"+nextCol)) continue;
                visited.add(nextRow+"_"+nextCol);
                
                q.add(new Object[]{nextRow, nextCol, time+1});
            }
        }
        return destObj;
    }
    
    private boolean validateIdx(int row, int col, int maxRow, int maxCol){
        if(row < 0 || col < 0) return false;
        if(row > maxRow || col > maxCol) return false;
        return true;
    }
    
}