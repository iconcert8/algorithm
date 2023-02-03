import java.util.*;
class Solution {
    public int solution(String word) {
        String[] alphabet = new String[]{"A","E","I","O","U"};
        
        Map<String, Integer> map = new HashMap<>();
        dfs(alphabet, map, "", 0);
        
        return map.get(word);
    }
    
    void dfs(String[] alphabet, Map<String, Integer> map, String curStr, int depth){
        if(depth >= alphabet.length){
            return;
        }
        
        for(int i = 0; i < alphabet.length; i++){
            // System.out.println(curStr+alphabet[i]);
            map.put(curStr+alphabet[i], map.size()+1);
            dfs(alphabet, map, curStr+alphabet[i], depth+1);
        }
    }
}