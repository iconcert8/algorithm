import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        if(k >= enemy.length) return enemy.length;

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> cuponQ = new PriorityQueue<>();
        for(int i = 0; i < k; i++) cuponQ.add(enemy[i]);
        
        long sum = 0;
        for(int i = k; i < enemy.length; i++){
            q.add(enemy[i]);          
            sum += enemy[i];
            
            while(!cuponQ.isEmpty() && !q.isEmpty() 
                  && cuponQ.peek() < q.peek()){
                int cuponValue = cuponQ.poll(), value = q.poll();
                cuponQ.add(value); q.add(cuponValue);

                sum += cuponValue; sum -= value;
            }
            if(sum > n) return i;
        }
        
        return enemy.length;
    }
}