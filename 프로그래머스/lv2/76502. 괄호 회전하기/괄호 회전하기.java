import java.util.*;
class Solution {
    static Map<String, String> PAIR = new HashMap<>(){{
        put("}", "{");
        put("]", "[");
        put(")", "(");
    }};
    
    static Set<String> ALL = new HashSet<>(){{
       add("{"); 
       add("}"); 
       add("["); 
       add("]"); 
       add("("); 
       add(")"); 
    }};
    
    public int solution(String s) {
        int answer = 0;
        String[] sArr = s.split("");
        for(int i = 0; i < sArr.length; i++){
            if(isValid(sArr, i)){
                answer++;
            }
        }
        
        return answer;
    }
    
    public boolean isValid(String[] sArr, int startIdx){
        Stack<String> stack = new Stack<>();
        for(int i = startIdx; i < sArr.length; i++){
            if(!ALL.contains(sArr[i])) continue;
            
            if(stack.isEmpty()) {
                stack.add(sArr[i]);
                continue;
            }
            
            if(PAIR.containsKey(sArr[i])){
                if(stack.peek().equals(PAIR.get(sArr[i]))){
                    stack.pop();
                    continue;
                }
            }
            
            stack.add(sArr[i]);
        }
        for(int i = 0; i < startIdx; i++){
            if(!ALL.contains(sArr[i])) continue;
            
            if(stack.isEmpty()) {
                stack.add(sArr[i]);
                continue;
            }
            
            if(PAIR.containsKey(sArr[i])){
                if(stack.peek().equals(PAIR.get(sArr[i]))){
                    stack.pop();
                    continue;
                }
            }
            
            stack.add(sArr[i]);
        }
        
        return stack.isEmpty();
    }
}