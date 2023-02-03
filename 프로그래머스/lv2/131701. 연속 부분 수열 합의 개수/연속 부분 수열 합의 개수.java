
import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> sumSet = new HashSet<>();
        for (int count = 1; count <= elements.length; count++) {
            for (int i = 0; i < elements.length; i++) {
                int sum = 0;
                for (int j = i; j < i + count; j++) {
                    int index = j % elements.length;
                    sum += elements[index];
                }
                sumSet.add(sum);
            }
        }

        return sumSet.size();
    }
}