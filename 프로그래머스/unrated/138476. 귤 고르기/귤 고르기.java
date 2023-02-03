import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Size> m = new HashMap<>();
        for(int tang : tangerine){
            m.merge(tang, new Size(), (o1, o2) -> {o1.count += o2.count; return o1;});
        }
        PriorityQueue<Size> q = new PriorityQueue<>(Collections.reverseOrder());
        q.addAll(m.values());
        
        while(!q.isEmpty()){
            if(k <= 0) break;
            
            answer++;
            k -= q.poll().count;
        }
        
        return answer;
    }
    
    class Size implements Comparable<Size>{
        int count = 1;
        
        @Override
        public int compareTo(Size other) {
            if (this.count > other.count)
                return 1;
            else if (this.count < other.count)
                return -1;
            return 0;
        }
    }
}