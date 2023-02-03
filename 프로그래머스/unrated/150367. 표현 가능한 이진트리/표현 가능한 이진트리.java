import java.util.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            if(check(numbers[i])) result[i] = 1;
        }
        return result;
    }
    
    public boolean check(long number){
        if(number == 1 || number == 0) return true;
        String bin = Long.toBinaryString(number);
        int n = 1;
        while(bin.length() > Math.pow(2, n) - 1){
            n++;
        }
        int diff = ((int)Math.pow(2, n) - 1) - bin.length();
        bin = "0".repeat(diff) + bin;
        
        String[] arr = bin.split("");
        if(arr[(arr.length-1)/2].equals("0")) return false;        
        return check(arr, 0, arr.length-1, false);
    }
    
    public boolean check(String[] arr, int s, int e, boolean parentIsDummy){
        boolean isTrue = true;
        int m = (e+s)/2;
        if(arr[m].equals("1") && parentIsDummy) return false;
        if(arr[m].equals("0")) parentIsDummy = true;
        if(s <= m-1){
            isTrue = check(arr, s, m-1, parentIsDummy) && check(arr, m+1, e, parentIsDummy);
        }
        return isTrue;
    }
}