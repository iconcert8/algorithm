import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        List<List<Integer>> boardList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            boardList.add(new ArrayList<>());
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                if (board[row][column] != 0) {
                    boardList.get(column).add(board[row][column]);
                }
            }
        }


        List<Integer> bucket = new ArrayList<>();
        int clearCount = 0;
        for (int move : moves) {
            if (boardList.get(move - 1).size() != 0) {
                bucket.add(boardList.get(move - 1).remove(0));
            }

            if (bucket.size() > 1) {
                if (bucket.get(bucket.size() - 2).equals(bucket.get(bucket.size() - 1))) {
                    bucket.remove(bucket.size() - 1);
                    bucket.remove(bucket.size() - 1);
                    clearCount += 2;
                }
            }
        }

        return clearCount;
    }
}