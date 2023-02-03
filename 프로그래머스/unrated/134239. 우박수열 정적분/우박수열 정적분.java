import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        List<Double> list = getAreas(k);
        for(int i = 0; i < ranges.length; i++){
            int[] range = ranges[i];
            int start = range[0];
            int end = list.size() + range[1];
            if(start > end){
                answer[i] = -1.0;
                continue;
            }
            
            double sum = 0;
            for(int j = start; j < end; j++){
                sum += list.get(j);
            }
            answer[i] = sum;
        }
        
        return answer;
    }
    
    List<Double> getAreas(int k){
        List<Double> list = new ArrayList<>();
        while(k > 1){
            int prevK = k;
            if(k % 2 == 0){
                k = k / 2;
                list.add(getArea(prevK, k));
                continue;
            }
            
            k = (k*3) + 1;
            list.add(getArea(prevK, k));
        }
        
        return list;        
    }
    
    double getArea(int sY, int eY){
        return Math.min(sY, eY) + (Math.abs((double)eY-sY)/2);
    }
}