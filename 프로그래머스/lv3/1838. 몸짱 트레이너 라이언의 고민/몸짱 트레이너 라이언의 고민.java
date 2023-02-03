import java.util.*;
class Solution {
    public int solution(int n, int m, int[][] timetable) {
        Arrays.sort(timetable, (arr1, arr2)->arr1[0]-arr2[0]);
        
        // 가장 많이 겹치는 숫자를 구한다.
        int count = 0;
        int maxCount = 0;
        Set<int[]> prevGuest = new HashSet<>(); 
        for(int i = 0; i < timetable.length; i++){
            int sTime = timetable[i][0];
            int eTime = timetable[i][1];
            count++;
            
            int[][] prevs = prevGuest.toArray(new int[0][0]);
            for(int[] prev : prevs){
                if(prev[1] < sTime){
                    prevGuest.remove(prev);
                    count--;
                }
            }
            
            prevGuest.add(timetable[i]);
            maxCount = Math.max(maxCount, count);
        }
        
        // 최장거리 자리 분배.
        if(maxCount == 1) return 0;
        if(maxCount == 2) return (n-1)*2;
        
        for(int d = (n-1)*2; d >= 2; d--){
            for(int i = 0; i < n; i++){
                Set<int[]> people = new HashSet<>();
                people.add(new int[]{i, 0});
                for(int y = 0; y < n; y++){
                    for(int x = 0; x < n; x++){
                        if(!valid(people, d, x, y)) continue;

                        people.add(new int[]{x,y});
                        if(people.size() == maxCount) return d;
                    }
                }
            }
        }
        
        
        return 1;
    }
    
    public boolean valid(Set<int[]> people, int dist, int x, int y){
        for(int[] p : people){
            int d = Math.abs(x-p[0]) + Math.abs(y-p[1]);
            if(dist > d) return false;
        }
        
        return true;
    }
}