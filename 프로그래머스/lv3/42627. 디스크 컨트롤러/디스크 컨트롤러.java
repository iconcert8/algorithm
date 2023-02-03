import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a1, a2)-> {
                int diff = a1[0]-a2[0];
                if(diff == 0){
                    return a1[1] - a2[1];
                }
                return diff;
            }
        );
        
        Set<Integer> visitedIdx = new HashSet<>();
        int time = 0;
        int latency = 0;
        while(visitedIdx.size() < n){
            PriorityQueue<int[]> q = find(jobs, time, visitedIdx);
            if(q.isEmpty()){
                time++;
                continue;
            }
            
            int[] e = q.poll();
            latency += e[1];
            // System.out.println(e[1] +": " +e[0]);
            visitedIdx.add(e[2]);
            time += jobs[e[2]][1];
        }
        
        
        return latency/n;
    }
    
    public PriorityQueue<int[]> find(int[][] jobs, int time, Set<Integer> visitedIdx){
        PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2)->a1[0]-a2[0]);
        for(int i = 0; i < jobs.length; i++){
            if(visitedIdx.contains(i)) continue;
            if(time >= jobs[i][0]) {
                // System.out.println("-- "+jobs[i][1]+" "+time+" "+jobs[i][0]);
                q.add(new int[]{jobs[i][1], jobs[i][1]+time-jobs[i][0], i});
            }
        }
        
        return q;
    }
    
}