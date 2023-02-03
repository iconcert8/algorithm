import java.util.*;
class Solution {
    public long solution(int[] a, int[][] edges) {
        int tmpSum = 0;
        boolean isAllZero = true;
        Map<Integer, Node> nodes = new HashMap<>();
        
        for(int i = 0; i < a.length; i++){
            nodes.put(i, new Node(i, a[i]));
            tmpSum += a[i];
            if(a[i] != 0) isAllZero = false;
        }
        
        if(tmpSum != 0) return -1;
        if(isAllZero) return 0;
        
        for(int[] edge : edges){
            Node node1 = nodes.get(edge[0]);
            Node node2 = nodes.get(edge[1]);
            
            node1.link(node2);
            node2.link(node1);
        }
        
        Set<Integer> oneEdgeNodes = new HashSet<>();
        for(int i = 0; i < a.length; i++){
            if(nodes.get(i).nodes.size() == 1){
                oneEdgeNodes.add(i);
            }
        }
        
        Set<Integer> removeNodes = new HashSet<>();
        while(true){
            removeNodes = new HashSet<>();
            for(int n : oneEdgeNodes){
                nodes.get(n).move(removeNodes);
            }
            
            oneEdgeNodes = new HashSet<>();
            for(int n : removeNodes){
                Node node = nodes.get(n);
                for(Node pNode : node.nodes){
                    pNode.value += node.value;
                    Node.moveCount += Math.abs(node.value);
                    pNode.unLink(node);
                    node.unLink(pNode);
                    
                    if(pNode.nodes.size() == 1){
                        oneEdgeNodes.add(pNode.id);
                    }
                }
            }
            
            if(removeNodes.size() == 0){
                break;
            }
        }
        
        return Node.moveCount;
    }
    
    public static class Node{
        static long moveCount = 0;
        int id;
        Set<Node> nodes = new HashSet<>();
        long value;
        
        public Node(int id, long value){
            this.id = id;
            this.value = value;
        }
        
        public void link(Node node){
            nodes.add(node);
        }
        
        public void unLink(Node node){
            nodes.remove(node);
        }
        
        public void move(Set<Integer> removeNodes){
            if(this.nodes.size() == 1){
                for(Node pNode : this.nodes){
                    removeNodes.add(this.id);
                }
                return;
            }
        }
    }
}