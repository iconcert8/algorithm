import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a1, a2)->a1[1]-a2[1]);
        
        
        Set<Integer> visited = new HashSet<>();
        
        int result = 0;
        int start = 0;
        
        while(visited.size() < routes.length){
            if(visited.contains(start)) {
                start++;
                continue;
            }
            int exit = routes[start][1];
            for(int i = 0; i < routes.length; i++){
                if(visited.contains(i)) continue;
                
                int s = routes[i][0];
                int e = routes[i][1];
                if(exit >= s && exit <= e){
                    visited.add(i);
                }
            }
            
            result++;
            start++;
        }
        
        return result;
        
    }
}