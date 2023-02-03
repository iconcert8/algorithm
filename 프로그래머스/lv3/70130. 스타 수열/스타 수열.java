import java.util.*;
class Solution {
    
    public int solution(int[] a) {
        if(a.length < 2){
            return 0;
        }
        
        int result = 0;
        
        Map<Integer, Integer> cntMap = new HashMap<>();
        
        for(int i = 0; i < a.length; i++){
            cntMap.merge(a[i], 1, Integer::sum);
        }
        
        for(Map.Entry<Integer, Integer> e : cntMap.entrySet()){
            int n = e.getKey();
            int cnt = e.getValue();
            
            if(cnt * 2 <= result) continue;
            
            int need = 0; // 0:needAll, 1:needN, 2:needNotN
            int star = 0;
            for(int i = 0; i < a.length; i++){
                if(need == 0){
                    need = n == a[i] ? 2 : 1;
                }else if(need == 1){
                    need = n == a[i] ? 0 : 1;
                    if(need == 0) star += 2;
                }else{
                    need = n != a[i] ? 0 : 2;
                    if(need == 0) star += 2;
                }
            }
            
            result = Math.max(result, star);
        }
                
        
        return result;
    }
    
    
    
    
    
}