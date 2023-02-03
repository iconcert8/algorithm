import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = Integer.MAX_VALUE;
        
        Queue<Object[]> q = new LinkedList<>();
        q.add(new Object[]{begin, new boolean[words.length], new LinkedList<String>()});
        
        while(!q.isEmpty()){
            Object[] e = q.poll();
            String from = (String)e[0];
            boolean[] visited = (boolean[])e[1];
            List<String> list = (List<String>)e[2];
            
            if(from.equals(target)){
                answer = Math.min(answer, list.size());
                continue;
            }
            
            for(int i = 0; i < words.length; i++){
                if(visited[i]) continue;
                if(!diffOnlyOne(from, words[i])) continue;
                
                String to = words[i];
                boolean[] copyVisited = visited.clone();
                copyVisited[i] = true;
                q.add(
                    new Object[]{
                        to, copyVisited, new LinkedList<String>(list){{add(to);}}
                    }
                );
            }
            
        }
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public boolean diffOnlyOne(String from, String to){
        int diffCount = 0;
        for(int i = 0; i < from.length(); i++){
            if(from.charAt(i) != to.charAt(i)) diffCount++;
        }
        
        if(diffCount == 1) return true;
        return false;
    }
}