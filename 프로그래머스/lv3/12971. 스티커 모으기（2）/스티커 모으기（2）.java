class Solution {
    public int solution(int sticker[]) {
        //참조: https://latte-is-horse.tistory.com/231
        int n = sticker.length;
        if(n == 1) return sticker[0];
        
        // 맨 앞 뜯는 경우
        int[] dp1 = new int[n];
        dp1[0] = sticker[0];
        dp1[1] = dp1[0];
        for(int i = 2; i < n - 1; i++){
           dp1[i] = Math.max(dp1[i-2]+sticker[i], dp1[i-1]); 
        }
        
        // 맨 앞 뜯지 않는 경우
        int[] dp2 = new int[n];
        for(int i = 1; i < n; i++){
            int idx = i-2;
            if(idx < 0) idx = n-1;
            dp2[i] = Math.max(dp2[idx]+sticker[i], dp2[i-1]);
        }
        
        return Math.max(dp1[n-2], dp2[n-1]);
    }
}