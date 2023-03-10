class Solution {
    public int solution(int n, int m, int[] section) {
        boolean[] arr = new boolean[n+1];
        for(int sec : section) arr[sec] = true;
        
        int idx = 1;
        int count = 0;
        while(idx < n+1){
            if(arr[idx] == false){
                idx++;
                continue;
            }
            idx += m;
            count++;
        }
        
        return count;
    }
}