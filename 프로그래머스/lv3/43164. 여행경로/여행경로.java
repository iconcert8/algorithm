import java.util.*;
class Solution {
    public String[] solution(String[][] tickets) {
        Map<String, Node> nodes = new HashMap<>();
        for(String[] ticket : tickets){
            Node from;
            Node to;
            if(!nodes.containsKey(ticket[0])){
                from = new Node(ticket[0]);
                nodes.put(ticket[0], from);
            }else{
                from = nodes.get(ticket[0]);
            }
            
            if(!nodes.containsKey(ticket[1])){
                to = new Node(ticket[1]);
                nodes.put(ticket[1], to);
            }else{
                to = nodes.get(ticket[1]);
            }
            
            from.add(to);
        }
        
        for(Node node : nodes.values()){
            Collections.sort(node.next);
            node.createVisited();
        }
        
        int targetCount = tickets.length;
        dfs(nodes.get("ICN"), "ICN", 0, targetCount);
        
        return result.split(" ");
    }
    
    static String result = null;
    void dfs(Node from, String paths, int count, int targetCount){
        if(result != null) return;
        
        if(count == targetCount){
            result = paths;
            return;
        }
        
        for(int i = 0; i < from.next.size(); i++){
            if(from.visited[i] == 1) continue;
            Node node = from.next.get(i);
            from.visited[i] = 1;
            dfs(node, paths+" "+node.value, count+1, targetCount);
            from.visited[i] = 0;
        }
    }
    
    class Node implements Comparable<Node>{
        String value;
        List<Node> next = new ArrayList<>();
        int[] visited;
        
        public Node(String value){
            this.value = value;
        }
        
        public void add(Node node){
            next.add(node);
        }
        
        public void createVisited(){
            visited = new int[next.size()];
        }
        
        @Override
        public int compareTo(Node other){
            return value.compareTo(other.value);
        }
    }
}