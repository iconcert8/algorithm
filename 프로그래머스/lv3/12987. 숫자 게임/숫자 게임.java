import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int k = 0;
        for(int i = 0; i < n; i++){
            for(int j = k; j < n; j++){
                if(A[i] < B[j]){
                    k = j+1;
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}