import java.util.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        Map<String, Map.Entry<Integer, Integer>> keypadIndex = new HashMap<>() {
            {
                put("1", new SimpleEntry<>(0, 0));
                put("2", new SimpleEntry<>(0, 1));
                put("3", new SimpleEntry<>(0, 2));
                put("4", new SimpleEntry<>(1, 0));
                put("5", new SimpleEntry<>(1, 1));
                put("6", new SimpleEntry<>(1, 2));
                put("7", new SimpleEntry<>(2, 0));
                put("8", new SimpleEntry<>(2, 1));
                put("9", new SimpleEntry<>(2, 2));
                put("*", new SimpleEntry<>(3, 0));
                put("0", new SimpleEntry<>(3, 1));
                put("#", new SimpleEntry<>(3, 2));
            }
        };

        String leftHand = "*";
        String rightHand = "#";
        StringBuilder answer = new StringBuilder();
        for (int number : numbers) {
            if (number == 1 || number == 4 || number == 7) {
                leftHand = number + "";
                answer.append("L");
                continue;
            }

            if (number == 3 || number == 6 || number == 9) {
                rightHand = number + "";
                answer.append("R");
                continue;
            }

            Map.Entry<Integer, Integer> leftIndex = keypadIndex.get(leftHand);
            Map.Entry<Integer, Integer> rightIndex = keypadIndex.get(rightHand);
            Map.Entry<Integer, Integer> targeIndex = keypadIndex.get(number + "");

            if (keyDistance(targeIndex, leftIndex) > keyDistance(targeIndex, rightIndex)) {
                rightHand = number + "";
                answer.append("R");
            } else if (keyDistance(targeIndex, leftIndex) < keyDistance(targeIndex, rightIndex)) {
                leftHand = number + "";
                answer.append("L");
            } else {
                if (hand.equals("right")) {
                    rightHand = number + "";
                    answer.append("R");
                } else {
                    leftHand = number + "";
                    answer.append("L");
                }
            }
        }
        
        return answer.toString();
    }
    
     int keyDistance(Map.Entry<Integer, Integer> from, Map.Entry<Integer, Integer> to) {
        return Math.abs(from.getKey() - to.getKey()) + Math.abs(from.getValue() - to.getValue());
    }
}