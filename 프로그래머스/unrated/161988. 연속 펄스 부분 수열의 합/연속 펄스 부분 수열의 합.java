class Solution {
    public long solution(int[] sequence) {
        // 1, -1, 1 ...
        long max1 = getMax(sequence, true);
        // -1, 1, -1 ...
        long max2 = getMax(sequence, false);
        return Math.max(max1, max2);
    }
    
    private static long getMax(int[] sequence, boolean startPulsePlus){
        final int pulse = startPulsePlus ? 0 : 1;
        long tmpSum = startPulsePlus ? sequence[0] : sequence[0]*-1;
        long maxSum = tmpSum;
        int sIdx = 0;
        int eIdx = 1;
        while(sIdx < sequence.length){
            while(eIdx < sequence.length){
                int value = eIdx % 2 == pulse ? sequence[eIdx] : sequence[eIdx]*-1; 
                if(value >= 0){
                    tmpSum += value;
                    maxSum = Math.max(maxSum, tmpSum);
                    eIdx++;
                }else break;
            }
        
            while(sIdx < eIdx-1){
                int value = sIdx % 2 == pulse ? sequence[sIdx] : sequence[sIdx]*-1;
                if(value < 0){
                    tmpSum -= value;
                    maxSum = Math.max(maxSum, tmpSum);
                    sIdx++;
                }else break;
            }
            
            if(eIdx < sequence.length){
                int value = eIdx % 2 == pulse ? sequence[eIdx] : sequence[eIdx]*-1; 
                eIdx++;
                tmpSum += value;
                if(tmpSum < 0){
                    sIdx = eIdx-1;
                    tmpSum = sIdx % 2 == pulse ? sequence[sIdx] : sequence[sIdx]*-1; 
                }
            }else{
                int value = sIdx % 2 == pulse ? sequence[sIdx] : sequence[sIdx]*-1;
                tmpSum -= value;
                sIdx++;
            }
        }
        return maxSum;
    }
}