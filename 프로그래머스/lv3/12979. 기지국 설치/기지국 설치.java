import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int answer = 0;
        int coverSize = (2*w)+1;
        int prev = 1;
        for(int station : stations){
            int start = station-w;
            int end = station+w;
            
            if(prev >= start){
                prev = end + 1;
                continue;
            }
            
            int range = start - prev;
            int count = range/coverSize;
            count = count + (range%coverSize != 0 ? 1 : 0);
            answer += count;
            
            prev = end+1;
        }
        
        if(prev < n+1){
            int range = n+1 - prev;
            int count = range/coverSize;
            count = count + (range%coverSize != 0 ? 1 : 0);
            answer += count;
        }
        
        return answer;
    }
}