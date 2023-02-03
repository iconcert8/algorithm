import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] sortedLost = Arrays.stream(lost).sorted().toArray();

        Set<Integer> lostSet = new HashSet<>();
        for (int l : lost) {
            lostSet.add(l);
        }

        Set<Integer> reserveSet = new HashSet<>();
        for (int r : reserve) {
            reserveSet.add(r);
        }

        for (int r : reserve) {
            if (lostSet.contains(r)) {
                reserveSet.remove(r);
                lostSet.remove(r);
            }
        }

        for (int l : sortedLost) {
            if (reserveSet.contains(l - 1)) {
                reserveSet.remove(l - 1);
                lostSet.remove(l);
                continue;
            }

            if (reserveSet.contains(l)) {
                reserveSet.remove(l);
                lostSet.remove(l);
                continue;
            }

            if (reserveSet.contains(l + 1)) {
                reserveSet.remove(l + 1);
                lostSet.remove(l);
                continue;
            }
        }
        
        return n - lostSet.size();
    }
}