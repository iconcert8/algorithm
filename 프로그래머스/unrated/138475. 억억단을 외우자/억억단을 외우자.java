import java.util.*;
class Solution {
    
    public int[] solution(int e, int[] starts) {
        
        int[] result = new int[starts.length];
        int[] counter = new int[e+1];
        
        for(int i = 1; i <= e; i++){
            for(int j = 1; j <= e/i; j++){
                counter[i*j] += 1;
            }
        }
        
        for(int k = 0; k < starts.length; k++){
            int s = starts[k];
            
            //이전 값을 토대로 다시 구하지 않고 재활용
            boolean next = false;
            for(int i = 0; i < k; i++){
                int prevS = starts[i];
                int prevN = result[i];
                
                if(prevS <= s && s <= prevN) {
                    result[k] = prevN;
                    next = true;
                    break;
                }
            }
            if(next) continue;
            
            int maxCount = 0;
            int maxCountNum = 0;
            for(int i = s; i <= e; i++){
                if(maxCount < counter[i]){
                    maxCount = counter[i];
                    maxCountNum = i;
                }
            }
            result[k] = maxCountNum;
        }
        
        return result;
        
    }
}