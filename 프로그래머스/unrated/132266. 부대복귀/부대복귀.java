import java.util.*;
class Solution {
    static int INF = 1_000_000;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 1; i <= n; i++) map.put(i, new LinkedList<>());
        for(int[] road :roads){
            int from = road[0], to = road[1];
            map.get(from).add(to);
            map.get(to).add(from);
        }
        
        dp[destination] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2)->a1[1]-a2[1]);
        q.add(new int[]{destination, dp[destination]});
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int from = arr[0], dist = arr[1];
            if(dp[from] < dist) continue;
            List<Integer> list = map.get(from);
            for(int to : list){
                if(dp[to] > dp[from] + 1){
                    dp[to] = dp[from] + 1;
                    q.add(new int[]{to, dp[to]});
                }
            }
            // System.out.println("n: "+from+", "+Arrays.toString(dp));
        }
        
        int[] result = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            result[i] = dp[sources[i]] >= INF ? -1 : dp[sources[i]];
        }
        
        return result;
    }
}