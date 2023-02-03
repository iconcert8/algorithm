import java.util.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> winSet = new HashSet<>();
        for (int w : win_nums) {
            winSet.add(w);
        }

        int zeroCount = 0;
        for (int l : lottos) {
            if (l == 0) {
                zeroCount++;
            }
            winSet.remove(l);
        }

        int duplicateCount = 6 - winSet.size();
        int minRank = 6;
        switch (duplicateCount) {
            case 6:
                minRank = 1;
                break;
            case 5:
                minRank = 2;
                break;
            case 4:
                minRank = 3;
                break;
            case 3:
                minRank = 4;
                break;
            case 2:
                minRank = 5;
                break;
            case 1:
                minRank = 6;
                break;
            case 0:
                minRank = 6;
                if (zeroCount > 0) {
                    zeroCount--;
                }
                break;
            default:
                break;
        }

        
        return new int[]{minRank-zeroCount, minRank};
    }
}