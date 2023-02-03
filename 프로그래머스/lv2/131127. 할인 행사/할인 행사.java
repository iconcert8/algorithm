import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int requiredDay = Arrays.stream(number).sum();
        Map<String, Integer> wantMap = new HashMap<>();

        int result = 0;
        for (int day = 0; day < discount.length; day++) {
            if (day + requiredDay > discount.length) {
                break;
            }

            wantMap.clear();
            for (int i = day; i < day + requiredDay; i++) {
                wantMap.merge(discount[i], 1, Integer::sum);
            }

            boolean isValidate = true;
            for (int i = 0; i < want.length; i++) {
                int n = wantMap.getOrDefault(want[i], 0);
                if (number[i] != n) {
                    isValidate = false;
                    break;
                }
            }

            if (isValidate) {
                result++;
            }
        }

        return result;
    }
}