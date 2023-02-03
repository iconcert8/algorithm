import java.util.*;
class Solution {
    public int[][] solution(int[][] nodeinfo) {
        
        
        Map<Integer, List<Node>> map = new HashMap<>();
        int maxLevel = 0;
        int minLevel = 100_000;
        for(int i = 0; i < nodeinfo.length; i++){
            Node node = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
            maxLevel = Math.max(maxLevel, node.y);
            minLevel = Math.min(minLevel, node.y);
            
            List<Node> list = null;
            if(map.containsKey(node.y)){
                list = map.get(node.y);
            }else{
                list = new LinkedList<>();
                map.put(node.y, list);
            }
            
            list.add(node);
        }
        
        List<Node> prevNodes = map.get(maxLevel);
        int level = maxLevel-1;
        while(level >= minLevel){
            for(int i = level; i >= minLevel; i--){
                if(!map.containsKey(i)) continue;
                else{
                    List<Node> curNodes = map.get(i);
                    link(prevNodes, curNodes);
                    
                    prevNodes = curNodes;
                    level = i;
                    break;
                }
            }
            
            level--;
        }
        
        
        int[][] result = new int[2][nodeinfo.length];
        int preIdx = 0;
        int aftIdx = 0;
        
        Set<Node> visited = new HashSet<>();
        Node root = map.get(maxLevel).get(0);
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        visited.add(root);
        
        result[0][preIdx++] = root.number;
        while(!stack.empty()){
            Node node = stack.peek();
            if(node.left == null && node.right == null){
                result[1][aftIdx++] = node.number;
                stack.pop();
                continue;
            }
            
            if(node.left != null && visited.contains(node.left) && node.right == null){
                result[1][aftIdx++] = node.number;
                stack.pop();
                continue;
            }
            
            if(node.right != null && visited.contains(node.right) && node.left == null){
                result[1][aftIdx++] = node.number;
                stack.pop();
                continue;
            }
            
            if(node.left != null && node.right != null
               && visited.contains(node.left) && visited.contains(node.right)){
                result[1][aftIdx++] = node.number;
                stack.pop();
                continue;
            }
            
            if(node.left != null && !visited.contains(node.left)){
                stack.push(node.left);
                result[0][preIdx++] = node.left.number;
                visited.add(node.left);
                continue;
            }
            
            if(node.right != null && !visited.contains(node.right)){
                stack.push(node.right);
                result[0][preIdx++] = node.right.number;
                visited.add(node.right);
                continue;
            }
        }
        
        return result;
    }
    
    public void link(List<Node> prevNodes, List<Node> curNodes){
        Set<Node> isSet = new HashSet<>();
        for(Node parent : prevNodes){
            for(Node child : curNodes){
                if(isSet.contains(child)) continue;
                if(parent.validLeft(child)){
                    parent.left = child;
                    child.parent = parent;
                    isSet.add(child);
                    continue;
                }
                
                if(parent.validRight(child)){
                    parent.right = child;
                    child.parent = parent;
                    isSet.add(child);
                }
            }
        }
    }
    
    class Node implements Comparable<Node>{
        int number;
        int x;
        int y;
        Node parent;
        Node left;
        Node right;

        
        public Node(int x, int y, int number){
            this.x = x;
            this.y = y;
            this.number = number;
        }
        
        public boolean validLeft(Node child){
            if(child.x > this.x) return false;
            
            int lowX = -1;
            int highX = this.x;
            
            Node p = parent;
            Node c = this;
            while(p != null){
                if(p.right == c){
                    lowX = Math.max(lowX, p.x);
                }
                c = p;
                p = p.parent;
            }
            
            if(child.x > lowX && child.x < highX){
                return true;
            }
            
            return false;
        }
        
        public boolean validRight(Node child){
            if(child.x < this.x) return false;
            
            int lowX = this.x;
            int highX = Integer.MAX_VALUE;
            
            Node p = parent;
            Node c = this;
            while(p != null){
                if(p.left == c){
                    highX = Math.min(highX, p.x);
                }
                c = p;
                p = p.parent;
            }
            
            if(child.x > lowX && child.x < highX){
                return true;
            }
            
            return false;
        }
        
        @Override
        public int compareTo(Node other){
            return this.x - other.x;
        }
        
        @Override
        public String toString(){
            return number+"";
        }
                
    }
}