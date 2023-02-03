import java.util.*;
class Solution {
    static int INF = 900_000_000;
    static Set<Integer> endSet;
    static Map<Integer, Map<Integer, Integer>> graphs;
    static int minInten = INF;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // initialize
        Arrays.sort(summits);
        endSet = new HashSet<>();
        graphs = new HashMap<>();
        for(int g : gates) endSet.add(g);
        for(int s : summits) endSet.add(s);
        for(int i = 1; i <= n; i++) graphs.put(i, new HashMap<>());
        for(int[] path : paths) {
            graphs.get(path[0]).put(path[1], path[2]);
            graphs.get(path[1]).put(path[0], path[2]);
        }
        
        int minSummit = 0;
        for(int s : summits){
            int[] dp = getDP(n, s);
            for(int g : gates){
                if(minInten > dp[g]){
                    minInten = dp[g];
                    minSummit = s;
                }
            }
        }
        
        return new int[]{minSummit, minInten};
    }
    
    public int[] getDP(int n, int start){
        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[start] = 0;
        
        Map<Integer, Integer> links = graphs.get(start);
        PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2)->a1[1]-a2[1]);
        for(Map.Entry<Integer, Integer> e : links.entrySet()) {
            dp[e.getKey()] = e.getValue();
            q.add(new int[]{e.getKey(), e.getValue()});
        }

        while(!q.isEmpty()){
            int[] o = q.poll();
            int node = o[0];
            int inten = o[1];
            if(inten > minInten) continue;
            
            Map<Integer, Integer> nextLinks = graphs.get(node);
            for(Map.Entry<Integer, Integer> e : nextLinks.entrySet()){
                int nextNode = e.getKey();
                int maxInten = Math.max(dp[node], e.getValue());
                if(dp[nextNode] > maxInten){
                    dp[nextNode] = maxInten;
                    if(!endSet.contains(nextNode)){
                        q.add(new int[]{nextNode, dp[nextNode]});
                    }
                }
            }
        }
        
        return dp;
    }
}