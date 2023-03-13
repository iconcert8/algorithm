import java.util.*;
class Solution {
    public int solution(String s) {
        int min = s.length();
        for(int u = 1; u <= s.length()/2; u++){
            min = Math.min(min, zip(s, u));   
        }
        return min;
    }
    
    private int zip(String str, int unit){
        int length = str.length();
        String prevStr = str.substring(0, unit);
        int duplicateCount = 0;
        for(int i = unit; i < str.length(); i=i+unit){
            if(i + unit > str.length()) {
                break;
            }
            String token = str.substring(i, i+unit);
            if(prevStr.equals(token)){
                duplicateCount++;
            }else{
                if(duplicateCount != 0) length = length - (duplicateCount*unit) + (int)Math.log10(duplicateCount+1)+1;
                duplicateCount = 0;
            }
            prevStr = token;
        }
        
        if(duplicateCount != 0) length = length - (duplicateCount*unit) + (int)Math.log10(duplicateCount+1)+1;
        
        return length;
    }
}