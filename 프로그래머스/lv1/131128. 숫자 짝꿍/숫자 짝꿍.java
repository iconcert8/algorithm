import java.util.*;
class Solution {
    public String solution(String X, String Y) {
        Map<String, Integer> xCount = new HashMap<>();
        Map<String, Integer> yCount = new HashMap<>();

        Arrays.stream(X.split("")).forEach(s -> xCount.merge(s, 1, Integer::sum));
        Arrays.stream(Y.split("")).forEach(s -> yCount.merge(s, 1, Integer::sum));

        Map<String, Integer> duplicateCount = new HashMap<>();
        xCount.entrySet().forEach(entry -> {
            int yValue = yCount.getOrDefault(entry.getKey(), 0);
            int duplicate = Math.min(entry.getValue(), yValue);
            if (duplicate != 0) {
                duplicateCount.put(entry.getKey(), duplicate);
            }
        });


        boolean isOnlyZero = true;
        StringBuilder answer = new StringBuilder("");
        for (int i = 9; i >= 0; i--) {
            int duplicate = duplicateCount.getOrDefault(i + "", 0);
            if (i > 0 && duplicate > 0) isOnlyZero = false;
            for (int d = 0; d < duplicate; d++) {
                answer.append(i);
            }
        }

        if (duplicateCount.size() == 0) {
            return "-1";
        }

        if (isOnlyZero) {
            return "0";
        }

        return answer.toString();
    }
}