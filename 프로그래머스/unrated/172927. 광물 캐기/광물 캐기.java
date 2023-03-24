import java.util.*;
class Solution {
    static int min = Integer.MAX_VALUE;
    static Map<Integer, Map<String, Integer>> FATIGUE_TABLE = new HashMap<>(){{
        put(0, new HashMap<>(){{
           put("diamond", 1); put("iron", 1); put("stone", 1);
        }});
        put(1, new HashMap<>(){{
           put("diamond", 5); put("iron", 1); put("stone", 1);
        }});
        put(2, new HashMap<>(){{
           put("diamond", 25); put("iron", 5); put("stone", 1);
        }});
    }};
    
    public int solution(int[] picks, String[] minerals) {
        dfs(picks, minerals, 0, 0);
        return min;
    }
    
    private void dfs(int[] picks, String[] minerals, int startIdx, int fatigue){
        if(startIdx == minerals.length || emptyPicks(picks)){
            min = Math.min(min, fatigue);
            return;
        }
        
        if(min <= fatigue) return;
        
        for(int i = 0; i < 3; i++){
            if(picks[i] == 0) continue;
            picks[i]--;
            int addedFatigue = miningFatigue(i, minerals, startIdx);
            dfs(picks, minerals, Math.min(minerals.length, startIdx + 5), fatigue+addedFatigue);
            picks[i]++;
        }
    }
    
    private int miningFatigue(int pick, String[] minerals, int startIdx){
        int fatigue = 0;
        for(int i = startIdx; i < Math.min(minerals.length, startIdx + 5); i++){
            fatigue += FATIGUE_TABLE.get(pick).get(minerals[i]);
        }
        return fatigue;
    }
    
    private boolean emptyPicks(int[] picks){
        for(int i = 0; i < picks.length; i++){
            if(picks[i] > 0) return false;
        }
        
        return true;
    }
}