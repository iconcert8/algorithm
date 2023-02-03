import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (arr1, arr2)->{
            int diff = arr1[col-1] - arr2[col-1];
            if(diff != 0) return diff;
            return arr2[0] - arr1[0];
        });
        
        int result = 0;
        for(int r = row_begin-1; r <= row_end-1; r++){
            int[] row = data[r];
            int S = 0;
            for(int e : row) S = S + (e%(r+1));
            result = result ^ S;
        }
        
        return result;
    }
}