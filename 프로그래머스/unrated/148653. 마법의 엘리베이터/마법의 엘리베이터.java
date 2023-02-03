import java.util.*;
class Solution {
    public int solution(int storey) {
        String[] str = String.valueOf(storey).split("");
        String[] arr = new String[str.length+1];
        arr[0] = "0";
        System.arraycopy(str, 0, arr, 1, str.length);
        
        int result = 0;
        for(int i = arr.length-1; i > 0; i--){
            int next = Integer.valueOf(arr[i-1]);
            int current = Integer.valueOf(arr[i]);
            if(current < 5){
                result += current;
            }else if(current > 5){
                result += 10-current;
                arr[i-1] = String.valueOf(next+1);
            }else{
                if(next < 5){
                    result += current;
                }else{
                    result += 10-current;
                    arr[i-1] = String.valueOf(next+1);
                }
            }
        }
        return result + Integer.valueOf(arr[0]);
    }
}