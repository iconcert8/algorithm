import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        
        Map<Integer, Node> nodes = new HashMap<>();
        for(int i = 1; i <= n; i++){
            nodes.put(i, new Node(i));
        }
        
        for(int[] result : results){
            Node winner = nodes.get(result[0]);
            Node loser = nodes.get(result[1]);
            
            winner.addLower(loser);
            loser.addUpper(winner);
        }
        
        int answer = 0;
        for(Node node : nodes.values()){
            // System.out.println(node.id + ": upper(" + node.upper.size() +"), lower("+node.lower.size()+")");
            
            if(node.getCount() == n-1){
                answer++;
            }
        }
        
        return answer;
    }
    
    class Node{
        int id;
        Set<Node> upper = new HashSet<>();
        Set<Node> lower = new HashSet<>();
        
        public Node(int id){
            this.id = id;
        }
        
        public void addUpper(Node node){
            upper.add(node);
            node.lower.addAll(lower);
            
            for(Node lNode : lower){
                lNode.upper.add(node);
                lNode.upper.addAll(node.upper);
            }
        }
        
        public void addLower(Node node){
            lower.add(node);
            node.upper.addAll(upper);
            
            for(Node uNode : upper){
                uNode.lower.add(node);
                uNode.lower.addAll(node.lower);
            }
        }
        
        public int getCount(){
            return upper.size() + lower.size();
        }
        
    }
}