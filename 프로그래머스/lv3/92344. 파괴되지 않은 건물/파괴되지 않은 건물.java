import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] sub = new int[board.length + 1][board[0].length + 1];
        for (int[] sk : skill) {
            Skill s = new Skill(sk);
            s.applyPrefixSum(sub);
        }

        for (int i = 1; i < sub.length; i++) {
            for (int j = 0; j < sub[i].length; j++) {
                sub[i][j] += sub[i - 1][j];
            }
        }

        for (int j = 1; j < sub[0].length; j++) {
            for (int i = 0; i < sub.length; i++) {
                sub[i][j] += sub[i][j - 1];
            }
        }


        int result = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += sub[i][j];
                if (board[i][j] > 0) {
                    result++;
                }
            }
        }


        return result;
    }
    static class Skill {
        final static int TYPE_ATTACK = 1;
        final static int TYPE_HEAL = 2;
        int r1;
        int c1;
        int r2;
        int c2;
        int degree;

        public Skill(int[] array) {
            this.r1 = array[1];
            this.c1 = array[2];
            this.r2 = array[3];
            this.c2 = array[4];
            this.degree = array[0] == TYPE_ATTACK ? -array[5] : array[5];
        }

//        public void apply(int[][] board) {
//            for (int i = r1; i <= r2; i++) {
//                for (int j = c1; j <= c2; j++) {
//                    board[i][j] += degree;
//                }
//            }
//        }

        /**
         * n 0 0 -n
         * 0 0 0 0
         * 0 0 0 0
         * 0 0 0 0
         * -n 0 0 n
         */
        public void applyPrefixSum(int[][] sub) {
            sub[r1][c1] += degree;
            sub[r2 + 1][c2 + 1] += degree;
            sub[r1][c2 + 1] += -degree;
            sub[r2 + 1][c1] += -degree;
        }
    }
}