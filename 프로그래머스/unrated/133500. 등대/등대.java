import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        Map<Integer, Set<Integer>> c = new HashMap<>();
        for (int [] l : lighthouse) {
            c.computeIfAbsent(l[0], x -> new HashSet<>()).add(l[1]);
            c.computeIfAbsent(l[1], x -> new HashSet<>()).add(l[0]);
        }
        int [] solve = solve(c, 1, 0);
        int answer = Math.min(solve[0], solve[1]);
        return answer;
    }

    private int [] solve(Map<Integer, Set<Integer>> c, int node, int ignore) {
        int no = 0;
        int yes = 1;
        for (int adj : c.get(node)) {
            if (adj != ignore) {
                int [] s = solve(c, adj, node);
                no += s[1];
                yes += Math.min(s[0], s[1]);
            }
        }
        return new int [] {no, yes};
    }
}


// import java.util.*;
// class Solution {
//     static Node[] nodes;

//     public int solution(int n, int[][] lighthouse) {
//         nodes = new Node[n+1];
//         for(int i = 1; i <= n; i++) nodes[i] = new Node(i);
        
//         for(int[] light : lighthouse){
//             int from = light[0], to = light[1];
//             nodes[from].add(to);
//             nodes[to].add(from);
//         }
        
//         int count = 0;
//         int pathCount = n-1;
//         int duplicateCount = 0;
        
//         while(pathCount > 0){
//             Set<Integer> visited = new HashSet<>();
//             for(int i = 1; i <= n; i++){
//                 if(nodes[i].size() == 1){
//                     int to = nodes[i].getFirst();
//                     if(visited.contains(to)) continue;
//                     if(nodes[to].size() == 1) duplicateCount++;
//                     visited.add(to);
//                     count++;
//                 }
//             }
            
//             for(int i : visited){
//                 pathCount -= nodes[i].size();
//                 nodes[i].remove();
//             }
//         }
            
//         return count - (duplicateCount/2);
//     }
    
//     class Node{
//         int number;
//         Set<Integer> links = new HashSet<>();
        
//         public Node(int number){
//             this.number = number;
//         }
        
//         public void remove(){
//             for(int link : links) nodes[link].links.remove(number);
//             links = new HashSet<>();
//         }
        
//         public void add(int node){
//             links.add(node);
//         }
        
//         public int size(){
//             return links.size();
//         }
        
//         public int getFirst(){
//             for(int i : links) return i;
//             return 0;
//         }
//     }
    
// }