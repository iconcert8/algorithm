import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);
        
        for(int k = 0; k < n; k++){
            for(int i = k+1; i < n; i++){
                if(computers[k][i] == 1){
                    nodes[k].add(nodes[i]);
                    nodes[i].add(nodes[k]);
                }
            }
        }
        
        Set<Integer> visited = new HashSet<Integer>();
        for(Node node : nodes){
            if(visited.contains(node.id)) continue;
            answer++;
            node.recursiveSet(visited);
        }
        
        
        return answer;
    }
    
    class Node{
        int id;
        List<Node> conn = new LinkedList<>();
        
        public Node(int id){
            this.id = id;
        }
        
        public void add(Node node){
            conn.add(node);
        }
        
        public void recursiveSet(Set<Integer> set){
            set.add(id);
            for(Node node : conn){
                if(set.contains(node.id)) continue;
                node.recursiveSet(set);
            }
        }
    }
}