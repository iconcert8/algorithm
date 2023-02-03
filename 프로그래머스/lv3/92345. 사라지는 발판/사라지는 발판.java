import java.util.*;
class Solution {
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int[] result = aturn(board, aloc, bloc, 0);    
        return result[1];
    }
    
    public int[] aturn(int[][] board, int[] a, int[] b, int count){
        if(board[a[0]][a[1]] == 0) return new int[]{1, count};
        
        boolean isExist = false;
        List<Integer> winner = new ArrayList<>();
        List<Integer> loser = new ArrayList<>();
        for(int[] d : dir){
            int[] next = new int[]{a[0]+d[0], a[1]+d[1]};
            if(!isBoundedIdx(board, next)) continue;
            if(board[next[0]][next[1]] == 0) continue;
            isExist = true;
            board[a[0]][a[1]] = 0;
            int[] result = bturn(board, next, b, count+1);
            board[a[0]][a[1]] = 1;
            
            if(result[0] == 1)//isWin
                winner.add(result[1]);
            else
                loser.add(result[1]);
        }
        
        if(isExist){
            if(!winner.isEmpty()) return new int[]{0, winner.stream().mapToInt(i->i).min().getAsInt()};
            else return new int[]{1, loser.stream().mapToInt(i->i).max().getAsInt()};
        }
        
        return new int[]{1, count};
    }
    
    public int[] bturn(int[][] board, int[] a, int[] b, int count){
        if(board[b[0]][b[1]] == 0) return new int[]{1, count};
        
        boolean isExist = false;
        List<Integer> winner = new ArrayList<>();
        List<Integer> loser = new ArrayList<>();
        for(int[] d : dir){
            int[] next = new int[]{b[0]+d[0], b[1]+d[1]};
            if(!isBoundedIdx(board, next)) continue;
            if(board[next[0]][next[1]] == 0) continue;
            isExist = true;
            board[b[0]][b[1]] = 0;
            int[] result = aturn(board, a, next, count+1);
            board[b[0]][b[1]] = 1;
            
            if(result[0] == 1)//isWin
                winner.add(result[1]);
            else
                loser.add(result[1]);
        }
        
        if(isExist){
            if(!winner.isEmpty()) return new int[]{0, winner.stream().mapToInt(i->i).min().getAsInt()};
            else return new int[]{1, loser.stream().mapToInt(i->i).max().getAsInt()};
        }
        
        return new int[]{1, count};
    }
    
    public boolean isBoundedIdx(int[][] board, int[] loc){
        if(loc[0] < 0 || loc[1] < 0 || loc[0] >= board.length || loc[1] >= board[0].length) return false;
        return true;
    }
}