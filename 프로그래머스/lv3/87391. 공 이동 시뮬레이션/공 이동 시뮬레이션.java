import java.util.*;
class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int sx = x, ex = x; 
        int sy = y, ey = y;
        int maxRow = n-1;
        int maxCol = m-1;
        for(int i = queries.length - 1; i >= 0; i--){
            int comm = queries[i][0];
            int dx = queries[i][1];
            
            if(comm == 0){
                if(isCollision(n, m, sx, sy, comm)){
                    ey = ey + dx > maxCol ? maxCol : ey+dx;
                }else{
                    if(sy + dx > maxCol && ey + dx > maxCol) return 0;
                    sy = sy + dx > maxCol ? maxCol : sy+dx;
                    ey = ey + dx > maxCol ? maxCol : ey+dx;
                }
            }else if(comm == 1){
                int offset = sy < dx ? sy : dx;
                if(isCollision(n, m, ex, ey, comm)){
                    sy = sy - dx < 0 ? 0 : sy - dx;
                }else{
                    if(sy - dx < 0 && ey - dx < 0) return 0;
                    sy = sy - dx < 0 ? 0 : sy - dx;
                    ey = ey - dx < 0 ? 0 : ey - dx;
                }
            }else if(comm == 2){
                int offset = (n-1) - ex < dx ? (n-1) - ex : dx; 
                if(isCollision(n, m, sx, sy, comm)){
                    ex = ex + dx > maxRow ? maxRow : ex+dx;
                }else{
                    if(sx + dx > maxRow && ex + dx > maxRow) return 0;
                    sx = sx + dx > maxRow ? maxRow : sx+dx;
                    ex = ex + dx > maxRow ? maxRow : ex+dx;
                }
            }else if(comm == 3){
                int offset = sx < dx ? sx : dx;
                if(isCollision(n, m, ex, ey, comm)){
                    sx = sx - dx < 0 ? 0 : sx-dx;
                }else{
                    if(sx - dx < 0 && ex - dx < 0) return 0;
                    sx = sx - dx < 0 ? 0 : sx-dx;
                    ex = ex - dx < 0 ? 0 : ex-dx;
                }
            }
        }
        
        long distX = ex - sx + 1;
        long distY = ey - sy + 1;
        return distX * distY;
    }
    
    public boolean isCollision(int n, int m, int x, int y, int comm){
        if(comm == 0){
            if(y==0) return true;
        }else if(comm == 1){
            if(y==m-1) return true;
        }else if(comm == 2){
            if(x==0) return true;
        }else if(comm == 3){
            if(x==n-1) return true;
        }
        return false;
    }
    
}   