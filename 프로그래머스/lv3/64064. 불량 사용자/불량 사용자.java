import java.util.*;
class Solution {
    static int result = 0;
    static Set<String> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        dfs(user_id, banned_id, new boolean[user_id.length], 0);
        return result;
    }
    
    void dfs(String[] user_id, String[] banned_id, boolean[] visited, int depth){
        if(banned_id.length <= depth){
            StringBuilder b = new StringBuilder();
            for(int i = 0; i < visited.length; i++){
                if(visited[i]){
                    b.append(i);
                    b.append("_");
                }
            }
            if(set.contains(b.toString())) return;            
            set.add(b.toString());
            result++;
            return;
        }
        
        String banId = banned_id[depth];
        for(int i = 0; i < user_id.length; i++){
            if(visited[i]) continue;
            if(banId.length() != user_id[i].length()) continue;          
            
            boolean isOk = true;
            for(int j = 0; j < banId.length(); j++){
                char banChar = banId.charAt(j);
                if(banChar == '*') continue;
                if(banChar != user_id[i].charAt(j)){
                    isOk = false;
                    break;
                }
            }
            
            if(!isOk) continue;
            
            visited[i] = true;
            String id = user_id[i];
            dfs(user_id, banned_id, visited, depth+1);
            visited[i] = false;
        }
    }
}