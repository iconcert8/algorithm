class Solution {
    public int[] solution(int n, long left, long right) {
        
        int dist = (int)((right - left) + 1);
        int[] result = new int[dist];
        int idx = 0;
        for(long i = left; i <= right; i++){
            result[idx] = get(n, i);
            idx++;
        }
        
        return result;
    }
    
    public int get(int n, long index){
        long num = (index/n)+1;
        long ext = (index%n);
        
        return (int)(num + Math.max(ext + 1 - num, 0));
    }
}