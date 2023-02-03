class Solution {
    public int solution(int[][] dots) {
        int answer = 0;
        
        int[] dot0 = dots[0];
        int[] dot1 = dots[1];
        int[] dot2 = dots[2];
        int[] dot3 = dots[3];

        
        if (absGradient(dot0, dot1).equals(absGradient(dot2, dot3))) {
            answer = 1;
        }

        
        if (absGradient(dot0, dot2).equals(absGradient(dot1, dot3))) {
            answer = 1;
        }

        
        if (absGradient(dot0, dot3).equals(absGradient(dot1, dot2))) {
            answer = 1;
        }
        
        return answer;
    }
    
    Double absGradient(int[] dot1, int[] dot2) {
        return Math.abs(((double) dot2[1] - dot1[1]) / (dot2[0] - dot1[0]));
    }
}