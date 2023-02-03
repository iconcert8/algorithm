import java.util.*;
class Solution {
    public int solution(int[] cards) {
        Set<Integer> usedSet = new HashSet<>();

        List<Integer> countList = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            if (usedSet.contains(cards[i])) {
                continue;
            }
            if (cards[i] == i + 1) {
                countList.add(1);
                continue;
            }
            List<Integer> cycleValues = new ArrayList<>();
            usedSet.add(cards[i]);
            cycleValues.add(cards[i]);
            recursiveCardOpen(cards, usedSet, cycleValues);

            if (cycleValues.size() > 1) {
                countList.add(cycleValues.size());
            }
        }
        if (countList.size() < 2) {
            return 0;
        }

        countList.sort(Comparator.reverseOrder());

        return countList.get(0) * countList.get(1);
    }
    
    void recursiveCardOpen(int[] cards, Set<Integer> usedSet, List<Integer> cycleValues) {
        int currentValue = cycleValues.get(cycleValues.size() - 1);

        int nextValue = cards[currentValue - 1];
        if (usedSet.contains(nextValue)) {
            return;
        }

        usedSet.add(nextValue);
        cycleValues.add(nextValue);

        recursiveCardOpen(cards, usedSet, cycleValues);
    }
}