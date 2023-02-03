import java.util.*;
class Solution {
    public int solution(int n, int k) {
        //n -> k진수
        int digit = 0;
        for (int i = 0; ; i++) {
            if (n < Math.pow(k, i)) {
                digit = i - 1;
                break;
            }
        }

        StringBuilder kNumber = new StringBuilder();
        for (int i = digit; i >= 0; i--) {
            int kDigit = (int) Math.pow(k, i);
            int a = 0;
            while (n - kDigit >= 0) {
                n = n - kDigit;
                a++;
            }
            kNumber.append(a);
        }


        //분해
        String kStr = kNumber.toString();
        kStr = kStr.replaceAll("0+", "0");
        String[] kStrSplit = kStr.split("0");

        int result = 0;
        for (String oneStr : kStrSplit) {
            
            if (isPrime(Long.parseLong(oneStr))) {
                result++;
            }
        }

        return result;
    }
    
    public boolean isPrime(long num) {
        if (num == 1) {
            return false;
        }

        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}