import java.util.*;
class Solution {
    public int solution(int[] a) {
        if(a.length == 1){
            return 1;
        }
        
        int answer = 0;
        SortedMap<Integer, Integer> m = new TreeMap<>();
        for(int i = 0; i < a.length; i++){m.put(a[i], i);}
        
        int leftMin = Integer.MAX_VALUE;
        int rightMin = m.firstKey();
        
        for(int k = 0; k < a.length; k++){
            int stdNum = a[k];
            
            m.remove(stdNum);
            rightMin = m.isEmpty() ? Integer.MAX_VALUE : m.firstKey();
            // System.out.println("std: "+ stdNum + " lMin: " + leftMin + " rMin: " + rightMin);
            
            if(leftMin > stdNum || rightMin > stdNum){
                answer++;
                // System.out.println("true");
            }
            
            leftMin = Math.min(leftMin, stdNum);
        }
        
        return answer;
    }
}