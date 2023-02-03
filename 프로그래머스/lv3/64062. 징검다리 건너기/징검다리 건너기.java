import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        
        int start = 0;
        int end = 200_000_000;
        int mid = (start + end) / 2;
        while(start <= end){
            if(check(stones, mid, k)){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
            mid = (start + end) / 2;
            
            // System.out.println("s: "+start+", m: "+mid+", e: "+ end);
        }
        
        return mid;
    }
    
    boolean check(int[] stones, int value, int k){
        int jump = 0;
        for(int i = 0; i < stones.length; i++){
            if(stones[i] < value){
                jump++;
                if(jump >= k) break;
                continue;
            }
            
            jump = 0;
        }
        
        return jump < k;
    }
}