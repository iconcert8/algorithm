import java.util.*;
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
               
        int[][] key90 = rotate(key);
        int[][] key180 = rotate(key90);
        int[][] key270 = rotate(key180);
        
        for(int r = -key.length+1; r < lock.length; r++){
            for(int c = -key.length+1; c < lock.length; c++){
                if(validate(lock, key, r, c)) return true;
                if(validate(lock, key90, r, c)) return true;
                if(validate(lock, key180, r, c)) return true;
                if(validate(lock, key270, r, c)) return true;
            }
        }
        
        return false;
    }
    
    boolean validate(int[][] lock, int[][] key, int keyStartRow, int keyStartCol){
        for(int r = 0; r < lock.length; r++){
            for(int c = 0; c < lock.length; c++){
                int keyR = -keyStartRow + r;
                int keyC = -keyStartCol + c;
                if(keyR < 0 || keyR >= key.length || keyC < 0 || keyC >= key.length){
                    if(lock[r][c] == 0) return false;
                    continue;
                }
                if(lock[r][c] == 1 && key[keyR][keyC] == 1) return false;
                if(lock[r][c] == 0 && key[keyR][keyC] == 0) return false;
            }
        }
        
        return true;
    }
    
    int[][] rotate(int[][] arr){
        int[][] result = new int[arr.length][arr.length];
        for(int r = 0; r < arr.length; r++){
            for(int c = 0; c < arr.length; c++){
                result[r][c] = arr[arr.length - 1 - c][r];              
            }
        }
        return result;
    }
    
}