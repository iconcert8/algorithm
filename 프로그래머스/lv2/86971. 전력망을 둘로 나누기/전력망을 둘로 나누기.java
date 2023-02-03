import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        
        Node[] nodes = new Node[n+1];
        for(int i = 1; i < n+1; i++){
            nodes[i] = new Node(i, n);
        }
        for(int[] wire : wires){
            int nodeId1 = wire[0];
            int nodeId2 = wire[1];
            
            Node node1 = nodes[nodeId1];
            Node node2 = nodes[nodeId2];
            node1.addChild(node2);
            node2.addChild(node1);
        }
        
        int result = Integer.MAX_VALUE;
        for(int i = 1; i < n+1; i++){
            Node pNode = nodes[i];
            for(Node cNode : pNode.children){
                cNode.removeChild(pNode);
                int extraCount = cNode.getCount();
                cNode.addChild(pNode);
                    
                int diff = Math.abs((n - extraCount) - extraCount); 
                result = Math.min(result, diff);
            }
        }
        
        return result;
    }
    
    public class Node{
        int id;
        int n;
        Set<Node> children = new HashSet<>();
        public Node(int id, int n){
            this.id = id;
            this.n = n;
        }
        
        int getCount(){
            int count = 1;
            boolean[] visited = new boolean[n+1];
            visited[id] = true;
            for(Node child : children){
                count += child.getCount(visited);
            }
            
            return count;
        }
        
        private int getCount(boolean[] visited){
            if(visited[id]){
                return 0;
            }
            int count = 1;
            visited[id] = true;
            for(Node child : children){
                if(visited[child.id]) continue;
                count += child.getCount(visited);
            }
            
            return count;
        }
        
        public void addChild(Node node){
            children.add(node);
        }
        
        public void removeChild(Node node){
            children.remove(node);
        }
    }
}