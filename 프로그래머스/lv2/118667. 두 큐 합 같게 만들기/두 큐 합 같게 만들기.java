import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Q q1 = new Q(queue1);
        Q q2 = new Q(queue2);
        
        int count = 0;
        for(int i = 0; i < queue1.length+queue2.length+queue2.length-1; i++){
            if(q1.getSum() > q2.getSum()){
                q2.add(q1.poll());
                count++;
            }else if(q1.getSum() < q2.getSum()){
                q1.add(q2.poll());
                count++;
            }else{
                return count;
            }
        }
        return -1;
    }
    
    class Q{
        private long sum;
        private Queue<Integer> q = new LinkedList<>();
        
        public Q(int[] queue){
            for(int e : queue) add(e);
        }
        
        public long getSum(){
            return sum;
        }
        
        public void add(int e){
            sum += e;
            q.add(e);
        }
        
        public int poll(){
            if(!isEmpty()){
                sum -= q.peek();
                return q.poll();
            } 
            return 0;
        }
        
        public boolean isEmpty(){
            return q.isEmpty();
        }
    }
}