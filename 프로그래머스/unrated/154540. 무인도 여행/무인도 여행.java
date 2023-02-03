import java.util.*;
class Solution {
    public int[] solution(String[] maps) {
        List<Integer> result = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for(int r = 0; r < maps.length; r++){
            for(int c = 0; c < maps[r].length(); c++){
                int total = dfs(maps, visited, r, c);
                if(total != 0 ) result.add(total);   
            }
        }
        if(result.isEmpty()) return new int[]{-1};
        return result.stream().mapToInt(value -> value).sorted().toArray();
    }
    
    public int dfs(String[] maps, Set<String> visited, int r, int c){
        int[] total = new int[]{0};
        dfs(maps, visited, total, r, c);
        return total[0];
    }
    
    public void dfs(String[] maps, Set<String> visited, int[] total, int r, int c){
        if(r > maps.length-1 || r < 0 || c > maps[0].length()-1 || c < 0) return;
        if(visited.contains(toKey(r, c))) return;
        if(maps[r].charAt(c) == 'X') return;
        visited.add(toKey(r,c));
        total[0] += Character.getNumericValue(maps[r].charAt(c));
        
        dfs(maps, visited, total, r-1, c);
        dfs(maps, visited, total, r+1, c);
        dfs(maps, visited, total, r, c-1);
        dfs(maps, visited, total, r, c+1);
    }
    
    public String toKey(int r, int c){
        return r+","+c;
    }
}