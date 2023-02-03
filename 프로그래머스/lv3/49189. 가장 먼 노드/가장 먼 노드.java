import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        Node[] nodes = new Node[n+1];
        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i);
        }
        
        for(int[] e : edge){
            Node n1 = nodes[e[0]];
            Node n2 = nodes[e[1]];
            
            n1.add(n2);
            n2.add(n1);
        }
        
        int[] edgeCount = new int[n+1];
        boolean[] visited = new boolean[n+1];
        int maxEdgeCount = 0;
        Queue<Map.Entry<Node, Integer>> q = new LinkedList<>();
        visited[1] = true;
        q.add(new AbstractMap.SimpleEntry<>(nodes[1], 0));
        while(!q.isEmpty()){
            Map.Entry<Node, Integer> e = q.poll();
            Node node = e.getKey();
            int count = e.getValue();
            
            count++;
            for(Node next : node.nodes){
                if(visited[next.id]) continue;
                visited[next.id] = true;
                edgeCount[next.id] = count;
                maxEdgeCount = Math.max(maxEdgeCount, count);
                q.add(new AbstractMap.SimpleEntry<>(next, count));
            }
        }
        
        int answer = 0;
        for(int i = 2; i <= n; i++){
            if(edgeCount[i] == maxEdgeCount) answer++;
        }
        
        
        return answer;
    }
    
    class Node{
        int id;
        List<Node> nodes = new LinkedList<>();
        
        public Node(int id){
            this.id = id;
        }
        
        public void add(Node node){
            nodes.add(node);
        }
    }
}