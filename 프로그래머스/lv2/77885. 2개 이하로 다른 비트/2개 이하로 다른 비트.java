class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int k = 0; k < numbers.length; k++){
            long num = numbers[k];
            if(num % 2 == 0){
                answer[k] = num+1;
                continue;
            }
            
            String bit = Long.toBinaryString(num);
            bit = '0' + bit;
            int zeroIdx = bit.lastIndexOf('0');
            answer[k] = num  
                + (long)(Math.pow(2, bit.length() - 1 - zeroIdx))
                - (long)(Math.pow(2, bit.length() - 2 - zeroIdx));
        }
        
        return answer;
    }
}