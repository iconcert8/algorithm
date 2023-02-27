class Solution {
    public int solution(String[] board) {
        // 개수 확인
        int oCount = 0;
        int xCount = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i].charAt(j) == 'O') oCount++;
                else if(board[i].charAt(j) == 'X') xCount++;
            }
        }
        if(oCount < xCount) return 0;
        if(oCount - xCount > 1) return 0;
        
        // 승부 확인
        int oSucCount = 0;
        int xSucCount = 0;
        for(int i = 0; i < 3; i++){
            String row = board[i];
            if(row.charAt(0) == '.') continue;
            if(row.charAt(0) == row.charAt(1) && row.charAt(0) == row.charAt(2)){
                if(row.charAt(0) == 'O') oSucCount++;
                else xSucCount ++;
            }
        }
        for(int i = 0; i < 3; i++){
            if(board[0].charAt(i) == '.') continue;
            if(board[0].charAt(i) == board[1].charAt(i) && board[0].charAt(i) == board[2].charAt(i))
                {
                if(board[0].charAt(i) == 'O') oSucCount++;
                else xSucCount ++;
            }
        }
        if(board[0].charAt(0) != '.'){
            if(board[0].charAt(0) == board[1].charAt(1) && board[0].charAt(0) == board[2].charAt(2)){
                if(board[0].charAt(0) == 'O') oSucCount++;
                else xSucCount ++;
            }
        }
        
        if(board[0].charAt(2) != '.'){
            if(board[0].charAt(2) == board[1].charAt(1) && board[0].charAt(2) == board[2].charAt(0)){
                if(board[0].charAt(2) == 'O') oSucCount++;
                else xSucCount ++;
            }
        }
        
        if(oCount == xCount && oSucCount > 0) return 0;
        if(oCount > xCount && xSucCount > 0) return 0;
        
        return 1;
    }
}