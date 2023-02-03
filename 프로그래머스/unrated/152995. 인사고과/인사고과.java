import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        Arrays.sort(scores, (arr1, arr2)->arr2[0]+arr2[1]-arr1[0]-arr1[1]);
        
        // int prevSum = scores[0][0] + scores[0][1];
        int wanhoSum = wanho[0] + wanho[1];
        int rank = 1;
        for(int i = 0; i < scores.length; i++) {
            int[] score = scores[i];
            int sum = score[0] + score[1];
            
            boolean out = false;
            for(int j = 0; j < i; j++){
                int[] upperScore = scores[j];
                if(upperScore[0] > score[0] && upperScore[1] > score[1]){
                    out = true;
                    break;
                }
            }
            
            if(sum == wanhoSum && out) return -1;
            else if(sum == wanhoSum && !out) return rank;
            else if(sum != wanhoSum && !out) rank++;
            else ;
        }
        
        
        return -1;
    }
}