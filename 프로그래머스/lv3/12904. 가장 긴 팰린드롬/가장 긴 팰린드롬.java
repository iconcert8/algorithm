class Solution{
    public int solution(String s){
        int maxCount = 1;
        for(int i = 1; i < s.length(); i++){
            maxCount = Math.max(maxCount, countWithout(s, i));
            maxCount = Math.max(maxCount, countWith(s, i));
        }
        
        return maxCount;
    }
    
    public int countWithout(String s, int idx){
        int prevIdx = idx - 1;
        int nextIdx = idx + 1;
        int count = 0;
        while(true){
            if(prevIdx < 0 || nextIdx >= s.length()){
                break;
            }
            
            char char1 = s.charAt(prevIdx--);
            char char2 = s.charAt(nextIdx++);
            if(char1 == char2){
                count++;
            }else{
                break;
            }
        }
        
        return (count*2)+1;
    }
    
    public int countWith(String s, int idx){
        int prevIdx = idx - 1;
        int nextIdx = idx;
        int count = 0;
        while(true){
            if(prevIdx < 0 || nextIdx >= s.length()){
                break;
            }
            
            char char1 = s.charAt(prevIdx--);
            char char2 = s.charAt(nextIdx++);
            if(char1 == char2){
                count++;
            }else{
                break;
            }
        }
        
        return count*2;
    }
}