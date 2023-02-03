import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        
        // 참조: https://school.programmers.co.kr/questions/21465
        // 여기서 bound에 답에 근접한 값을 계산.
        double vel = 0;
        for (int t : times) vel += 1D / t;
        double bound = n / vel;
        
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int t : times) {
            long cnt = (long) (bound / t);
            
            q.offer(new Node(t, (cnt+1) * t));
            n -= cnt;
        }
        
        while(n > 1){
            n--;
            
            Node node = q.poll();
            node.update();
            q.add(node);
        }
        
        return q.poll().totalTime;
    }
    
    class Node implements Comparable<Node>{
        long totalTime;
        long time;
        
        public Node(int time){
            this.time = time;
            this.totalTime = time;
        }
        
        public Node(int time, long totalTime){
            this.time = time;
            this.totalTime = totalTime;
        }
        
        public void update(){
            totalTime += time;
        }
        
        @Override
        public int compareTo(Node other){
            long dif = totalTime - other.totalTime;
            if(dif > 0) return 1;
            else if(dif == 0) return 0;
            else return -1;
        }
    }
}