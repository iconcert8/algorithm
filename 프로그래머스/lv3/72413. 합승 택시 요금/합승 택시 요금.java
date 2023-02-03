import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
//         Map<Integer, Node> map = new HashMap<>();
//         for(int i = 1; i <= n; i++){
//             map.put(i, new Node(i));
//         }
        
//         for(int[] fare : fares){
//             Node node1 = map.get(fare[0]);
//             Node node2 = map.get(fare[1]);
//             node1.add(node2, fare[2]);
//             node2.add(node1, fare[2]);
//         }
        
        int[][] dp = new int[n+1][n+1];
        for(int[] d : dp){
            Arrays.fill(d, 100_000 * n);
        }
        for(int i = 1; i <= n; i++){
            dp[i][i] = 0;
        }
        
        for(int[] fare : fares){
            int p1 = fare[0];
            int p2 = fare[1];
            int c = fare[2];
            
            dp[p1][p2] = c;
            dp[p2][p1] = c;
        }
        
        floyd(dp);
        
        int minCost = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++){
            int cost = dp[s][i] + dp[i][a] + dp[i][b];
            minCost = Math.min(minCost, cost);
        }
        
        return minCost;
    }
    
    void floyd(int[][] dp){
        for(int k = 1; k < dp.length; k++){
            for(int i = 1; i < dp.length; i++){
                if(i==k) continue;
                for(int j = i+1; j < dp.length; j++){
                    if(dp[i][j] > dp[i][k] + dp[k][j]){
                        dp[i][j] = dp[i][k] + dp[k][j];
                        dp[j][i] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
    }

    
    
    
//     public class Node{
//         int id;
//         List<Node> linked = new ArrayList<>();
//         List<Integer> cost = new ArrayList<>();
        
//         public Node(int id){
//             this.id = id;
//         }
        
//         public void add(Node node, int cost){
//             linked.add(node);
//             this.cost.add(cost);
//         }
//     }
}