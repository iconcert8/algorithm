import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        
        if(n <= cores.length){
            return n;
        }
        
        int sTime = 0;
        int eTime = 10_000 * 50_000;
        int mTime = 0;
        while(sTime <= eTime){
            mTime = (eTime + sTime)/2;
            int count = enableJobCount(cores, mTime, n);
            if(count >= n){
                eTime = mTime-1;
            }else{
                sTime = mTime+1;
            }
        }
        
        // System.out.println(sTime +", " + eTime);
        
        //{idx, totalTime}
        PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2)->{
            int diff = a1[1] - a2[1];
            if(diff == 0) return a1[0] - a2[0];
            return diff;
        });
        
        
        for(int i = 0; i < cores.length; i++){
            int jobCount = eTime / cores[i];
            if(eTime % cores[i] != 0) jobCount++;
            n -= jobCount;
            q.add(new int[]{i, jobCount * cores[i]});
        }
        
        // System.out.println("n: " + n);
        
        int[] last = q.peek();
        while(n-- > 0){
            int[] arr = q.poll();
            last = arr;
            arr[1] += cores[arr[0]];
            q.add(arr);
        }
        
        return last[0]+1;
    }
    
    public int enableJobCount(int[] cores, int time, int n){
        int count = 0;
        for(int i = 0; i < cores.length; i++){
            if(count > n) return n+1;
            count += time/cores[i];
            
            if(time%cores[i] != 0) count++;
        }
        
        return count;
    }
}