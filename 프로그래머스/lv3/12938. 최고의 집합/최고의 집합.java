import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int n, int s) {

        // 조건이 안될 때
        if (s - n < 1) {
          return new int[] { -1 };
        }
    
        int[] result = new int[n];
        int value = s/n;
        for(int i = 0; i < n; i++) result[i] = value;
        
        for(int remain = s%n, i = n-1; remain > 0; remain--, i--){
            result[i] += 1;            
        }
        
        return result;
  }
}