import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[][] lines) {
       Map<Integer, Integer> lineCountMap = new HashMap<>();
        for (int[] line : lines) {
            boolean isAsc = line[0] < line[1];
            int start = isAsc ? line[0] : line[1];
            int end = isAsc ? line[1] : line[0];
            for (int i = start; i < end; i++) {
                lineCountMap.put(i, lineCountMap.containsKey(i) ? lineCountMap.get(i) + 1 : 1);
            }
        }
        
        return (int)lineCountMap.values().stream().filter(duplicateCount -> duplicateCount > 1).count();
    }
}