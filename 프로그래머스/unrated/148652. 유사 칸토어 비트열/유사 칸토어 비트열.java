class Solution {
    // 참조: https://school.programmers.co.kr/questions/41641
    public int solution(int n, long l, long r) {
        return (int)(countOfOne(r) - countOfOne(l-1));
    }
    
    public long countOfOne(long range){
        if(range <= 5){
            int count = 0;
            for(int i = 0; i < range; i++){
                if("11011".charAt(i) == '1') count++;               
            }
            return count;
        } 
        
        
        int base = 1;
        while(((long)Math.pow(5, base+1)) < range){
            base++;
        }
        
        long section = range / (long)Math.pow(5, base);
        long remainder = range % (long)Math.pow(5, base);
        
        long answer = section * (long)Math.pow(4, base);
        if(section >= 3)
            answer -= (long)Math.pow(4, base);
        
        if(section == 2)
            return answer;
        
        return answer + countOfOne(remainder);
    }
}