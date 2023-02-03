import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> firstPieceMap = new HashMap<>();
        Map<Integer, Integer> secondPieceMap = new HashMap<>();
        for (int n : topping) {
            secondPieceMap.merge(n, 1, Integer::sum);
        }

        int count = 0;
        boolean isMeaning = false;

        for (int i = 0; i < topping.length; i++) {
            int removeInteger = topping[i];
            firstPieceMap.put(removeInteger, null);
            if (secondPieceMap.containsKey(removeInteger)) {
                int remainCount = secondPieceMap.get(removeInteger);
                if (remainCount > 1) {
                    secondPieceMap.put(removeInteger, remainCount - 1);
                } else {
                    secondPieceMap.remove(removeInteger);
                }
            }

            if (firstPieceMap.size() == secondPieceMap.size()) {
                count++;
                isMeaning = true;
            } else {
                if (isMeaning) {
                    break;
                }
            }


        }

        return count;
    }
}