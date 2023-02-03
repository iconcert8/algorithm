import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
//         PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
//         for(int i = 0; i < works.length; i++) q.add(works[i]);
        
//         while(n-- > 0){
//             int v = q.poll();
//             if(v > 0) v--;
//             q.add(v);
//         }
        
//         long result = 0;
//         while(!q.isEmpty()){
//             int v = q.poll();
//             result += v*v;
//         }
        
//         return result;
        Arrays.sort(works);
        while(n > 0){
            int stdNum = works[works.length-1];
            if(stdNum == 0) break;
            for(int i = works.length-1; i >= 0; i--){
                if(n <= 0) break;
                if(stdNum != works[i]) break;
                works[i]--;
                n--;
            }
        }
        return Arrays.stream(works).asLongStream().map(i->(long)Math.pow(i, 2)).sum();
    }
}