import java.util.*;
class Solution {
    //[r, c] : d,l,r,u
    static int[][] DIR = {{1,0},{0,-1},{0,1},{-1,0}};
    static char[] CDIR = {'d','l','r','u'};
    static String IMPOSSIBLE = "impossible";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int diff = Math.abs(r-x) + Math.abs(c-y);
        if(k%2 + diff%2 == 1) return IMPOSSIBLE;
        if(k - diff < 0) return IMPOSSIBLE;
        
        LinkedList<Character> paths = new LinkedList<>();
        dfs(n, m, x, y, r, c, k, paths);
        StringBuilder sb = new StringBuilder(paths.size());
        for(char p : paths) sb.append(p);
        return sb.toString();
    }
    
    public boolean dfs(int n, int m, int x, int y, int r, int c, int k, LinkedList<Character> paths){
        if(k == 0){
            if(x == r && y == c) return true;
            return false;            
        }
        
        int diff = Math.abs(r-x) + Math.abs(c-y);
        if(diff > k) return false;
        
        for(int d = 0; d < 4; d++){
            int nx = x + DIR[d][0];
            int ny = y + DIR[d][1];
            if(!validXY(n, m, nx, ny)) continue;
            paths.add(CDIR[d]);
            if(dfs(n, m, nx, ny, r, c, k-1, paths)) return true;
            paths.removeLast();
        }
        return false;
    }
    
    public boolean validXY(int n, int m, int x, int y){
        if(x < 1 || y < 1 || x > n || y > m) return false;
        return true;
    }
}