import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] array = new int[rows+1][columns+1];
        
        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= columns; j++){
                array[i][j] = (j)+((i-1)*columns);
            }
        }
        
        for(int k = 0; k < queries.length; k++){
            int[] query = queries[k];
            int sr = query[0]; int sc = query[1];
            int er = query[2]; int ec = query[3];
            
            Queue<Integer> q = new LinkedList<>();
            int minNum = Integer.MAX_VALUE;
            for(int c = sc; c < ec; c++){q.add(array[sr][c]); minNum = Math.min(minNum, array[sr][c]);}
            for(int r = sr; r < er; r++){q.add(array[r][ec]); minNum = Math.min(minNum, array[r][ec]);}
            for(int c = ec; c > sc; c--){q.add(array[er][c]); minNum = Math.min(minNum, array[er][c]);}
            for(int r = er; r > sr; r--){q.add(array[r][sc]); minNum = Math.min(minNum, array[r][sc]);}
            
            for(int c = sc+1; c <= ec; c++){array[sr][c] = q.poll();}
            for(int r = sr+1; r <= er; r++){array[r][ec] = q.poll();}
            for(int c = ec-1; c >= sc; c--){array[er][c] = q.poll();}
            for(int r = er-1; r >= sr; r--){array[r][sc] = q.poll();}
            
            // Arrays.stream(array).forEach(
            //     arr->{
            //         System.out.println(Arrays.toString(arr));
            //     }
            // );
            // System.out.println();    
            
            
            answer[k] = minNum;
        }
        
        return answer;
    }
}