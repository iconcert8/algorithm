import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2)->a1[2]-a2[2]);
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);
        for(int[] cost : costs) {q.add(cost);}
        
        while(!q.isEmpty()){
            int[] route = q.poll();
            if(nodes[route[0]].find(nodes[route[1]])) continue;
            
            nodes[route[0]].add(nodes[route[1]]);
            nodes[route[1]].add(nodes[route[0]]);
            
            answer += route[2];
        }
        
        return answer;
    }
    
    class Node{
        int idx;
        List<Node> nextNodes = new LinkedList<>();
        
        public Node(int idx){
            this.idx = idx;
        }
        
        public void add(Node next){
            nextNodes.add(next);
        }
        
        public boolean find(Node node){
            Set<Node> visited = new HashSet<>();
            visited.add(node);
            return find(node, visited);
        }
        private boolean find(Node node, Set<Node> visited){
            for(Node next : nextNodes){
                if(next == node) return true;
                if(visited.contains(next)) continue;
                visited.add(next);
                if(next.find(node, visited)) return true;
            }
            
            return false;
        }
    }
}