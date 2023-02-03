import java.util.*;
class Solution {
    static Map<Integer, Set<Integer>> child;
    static int[] info;
    static int maxCount = 1;
    public int solution(int[] arr, int[][] edges) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i]==1) arr[i] = -1;
            if(arr[i]==0) arr[i] = 1;
        }
        info = arr;
        child = new HashMap<>();
        for(int i = 0; i < info.length; i++) child.put(i, new HashSet<>());
        for(int[] edge : edges) {
            child.get(edge[0]).add(edge[1]);
        }
        dfs(child.get(0), 1, 1);
        return maxCount;        
    }
    
    public void dfs(Set<Integer> next, int remainSheepCount, int sheepCount){
        if(remainSheepCount == 0) return;
        maxCount = Math.max(maxCount, sheepCount);
        
        for(int n : next){
            Set<Integer> tmpNext = new HashSet<>(next);
            tmpNext.remove(n);
            tmpNext.addAll(child.get(n));
            dfs(tmpNext, remainSheepCount+info[n], info[n]==1?sheepCount+1:sheepCount);
        }
    }
 
    
   
}