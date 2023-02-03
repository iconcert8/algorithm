import java.util.*;
class Solution {
    public long solution(int[] weights) {
        int[] dists = new int[]{2,3,4};
        Map<Integer, Long> multiplyWeight = new HashMap<>();
        Map<Integer, Long> duplicateWeight = new HashMap<>();
        for(int w : weights){
            duplicateWeight.put(w, duplicateWeight.getOrDefault(w, 0L)+1L);
            for(int d : dists) multiplyWeight.put(w*d, (long)multiplyWeight.getOrDefault(w*d, 0L)+1L);
        }
        long result = 0;
        for(long count : multiplyWeight.values()){
            if(count <= 1) continue;
            // nCr(n=count, r=2)
            result += count*(count-1)/2;
        }
        for(long count : duplicateWeight.values()){
            if(count <= 1) continue;
            result -= count*(count-1);
        }
        return result;
    }
}