class Solution {
    public long solution(int k, int d) {
        long sum = d/k+1;
        for(int y = 0; y <= d; y=y+k){
            int x = (int)Math.sqrt(Math.pow(d,2)-Math.pow(y,2));
            sum += x/k;
        }
        return sum;
    }
}