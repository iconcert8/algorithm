import java.util.*;
class Solution {
    public int[] solution(int[][] arr) {
        boolean[][] merged = new boolean[arr.length][arr[0].length];
        dfs(arr, merged, 0, 0, arr.length-1, arr.length-1);
        int[] result = new int[]{0,0};
        for(int i = 0; i < merged.length; i++){
            for(int j = 0; j < merged[i].length; j++){
                if(merged[i][j]) continue;
                result[arr[i][j]]++;
            }
        }        
        return result;
    }
    
    public void dfs(int[][] arr, boolean[][] merged, int sr, int sc, int er, int ec){
        if(sr == er && sc == ec) return;
        if(equalsAllElements(arr, sr, sc, er, ec)){
            merge(merged, sr, sc, er, ec);
            return;
        }
        int mr = (er+sr)/2;
        int mc = (ec+sc)/2;
        dfs(arr, merged, sr, sc, mr, mc);
        dfs(arr, merged, sr, mc+1, mr, ec);
        dfs(arr, merged, mr+1, sc, er, mc);
        dfs(arr, merged, mr+1, mc+1, er, ec);
    }
    
    private boolean equalsAllElements(int[][] arr, int sr, int sc, int er, int ec){
        int num = arr[sr][sc];
        for(int i = sr; i <= er; i++){
            for(int j = sc; j <= ec; j++){
                if(arr[i][j] != num) return false;
            }
        }
        return true;        
    }
    
    private void merge(boolean[][] merged, int sr, int sc, int er, int ec){
        for(int i = sr; i <= er; i++){
            for(int j = sc; j <= ec; j++){
                if(i == sr && j == sc) continue;
                merged[i][j] = true;
            }
        }
    }
}