import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // Arrays.sort(arrayA); Arrays.sort(arrayB);
        
        int amin = minGCD(arrayA);
        int bmin = minGCD(arrayB);
        
        if(amin == -1 && bmin == -1) return 0;
        if(amin == -1){
            if(checkNotDivision(arrayA, bmin)) return bmin;
            return 0;
        }
        if(bmin == -1){
            if(checkNotDivision(arrayB, amin)) return amin;
            return 0;
        }
        
        boolean aok = checkNotDivision(arrayB, amin);
        boolean bok = checkNotDivision(arrayA, bmin);
        if(aok && bok) return amin > bmin ? amin : bmin;
        if(!aok && !bok) return 0;
        return !aok ? bmin : amin;
    }
    
    public boolean checkNotDivision(int[] array, int value){
        for(int e : array){
            if(e/value != 0 && e%value == 0) return false;
        }
        return true;
    }
    
    public int minGCD(int[] array){
        int min = array[0];
        for(int i = 1; i < array.length; i++){
            min = GCD(array[i], min);
            if(min == 1) return -1;
        }
        return min;
    }
    
    public int GCD(int a, int b){
        if(b==0)return a;
        else return GCD(b, a%b);
    }
}