import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> players = new HashMap<>();
        for (String player : participant) {
            players.merge(player, 1, Integer::sum);
        }

        for (String player : completion) {
            int count = players.remove(player);
            if (count > 1) {
                players.put(player, count - 1);
            }
        }
        
        String answer = "";
        for(String a : players.keySet()){
            answer = a;
        }
        
        return answer;
    }
}