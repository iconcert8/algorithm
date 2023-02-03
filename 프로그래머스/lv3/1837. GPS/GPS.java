import java.util.*;
class Solution {
    static int INF = 1_000_000;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        Node[] nodes = new Node[n+1];
        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i);
        }
        
        for(int[] edge : edge_list){
            nodes[edge[0]].add(nodes[edge[1]]);
            nodes[edge[1]].add(nodes[edge[0]]);
        }
        
        // dp[순서][노드]
        int[][] dp = new int[k+1][n+1];
        for(int[] arr : dp) Arrays.fill(arr, INF);
        dp[1][gps_log[0]] = 0;
        
        for(int t = 2; t <= k; t++){
            int[] prevNodes = dp[t-1];
            for(int p = 1; p < prevNodes.length; p++){
                if(prevNodes[p] >= INF) continue;
                for(int i = 1; i <= n; i++){
                    if(!nodes[p].contains(i)) continue;
                    
                    int change = 1;
                    if(gps_log[t-1] == i) change = 0;
                    dp[t][i] = Math.min(dp[t][i], dp[t-1][p] + change);
                }
            }
        }
        
        return dp[k][gps_log[k-1]] >= INF ? -1 : dp[k][gps_log[k-1]];
    }
    
    class Node{
        int value;
        Set<Node> paths = new HashSet<>();
        Set<Integer> pathInts = new HashSet<>();
        
        
        public Node(int value){
            this.value = value;
            add(this);
        }
        
        public void add(Node node){
            paths.add(node);
            pathInts.add(node.value);
        }
        
        public boolean contains(int value){
            return pathInts.contains(value);
        }
        
        public boolean contains(Node node){
            return paths.contains(node);
        }
    }
}