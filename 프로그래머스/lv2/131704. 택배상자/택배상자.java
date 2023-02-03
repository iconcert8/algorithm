import java.util.*;
class Solution {
    public int solution(int[] order) {
        Queue<Integer> mainContainer = new ArrayDeque<>();
        for (int i = 0; i < order.length; i++) {
            mainContainer.add(i + 1);
        }
        Stack<Integer> subContainer = new Stack<>();

        int currentIndex = 0;
        int successCount = 0;
        while (true) {
            if (mainContainer.size() == 0 && subContainer.size() == 0) {
                break;
            }

            // 1 case
            if (mainContainer.size() == 0 && subContainer.size() != 0) {
                if (subContainer.peek() == order[currentIndex]) {
                    subContainer.pop();
                    successCount++;
                    currentIndex++;
                } else {
                    break;
                }
                continue;
            }

            // 2 case
            if (mainContainer.size() != 0 && subContainer.size() == 0) {
                if (mainContainer.peek() == order[currentIndex]) {
                    mainContainer.poll();
                    successCount++;
                    currentIndex++;
                } else {
                    subContainer.add(mainContainer.poll());
                }
                continue;
            }

            // 3 case

            if (mainContainer.size() != 0 && subContainer.size() != 0) {
                if (mainContainer.peek() == order[currentIndex]) {
                    mainContainer.poll();
                    successCount++;
                    currentIndex++;
                } else if (subContainer.peek() == order[currentIndex]) {
                    subContainer.pop();
                    successCount++;
                    currentIndex++;
                } else {
                    subContainer.add(mainContainer.poll());
                }
                continue;
            }


        }

        return successCount;
    }
}