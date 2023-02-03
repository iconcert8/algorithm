class Solution {
    public int[] solution(String s) {
        StringBuilder sb = null;
        int removedZero = 0;
        int change = 0;
        while(!s.equals("1")){
            sb = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1') {
                    sb.append('1');
                    continue;
                }
                removedZero++;
            }
            int len = sb.length();
            s = Integer.toBinaryString(len);
            change++;
        }
        
        return new int[]{change, removedZero};
    }
}