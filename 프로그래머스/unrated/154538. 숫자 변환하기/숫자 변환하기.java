import java.util.*;
import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        Queue<int[]> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(new int[]{x, 0});
        
        while(!q.isEmpty()){
            int[] e = q.poll();
            int curX = e[0];
            int count = e[1];
            if(curX == y) return count;
            if(curX > y) continue;
            if(visited.contains(curX)) continue;
            visited.add(curX);
            q.add(new int[]{curX*3, count+1});
            q.add(new int[]{curX*2, count+1});
            q.add(new int[]{curX+n, count+1});
        }
        
        return -1;
    }
}