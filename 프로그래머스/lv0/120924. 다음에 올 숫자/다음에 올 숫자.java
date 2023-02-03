class Solution {
    public int solution(int[] common) {
        int diff0 = common[1] - common[0];
        int diff1 = common[2] - common[1];
        if(diff0 == diff1){
            return common[common.length-1] + diff0;
        }
        
        return common[common.length-1] * (common[1]/common[0]);
    }
}