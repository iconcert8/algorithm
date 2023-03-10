import java.util.*;
class Solution {
    
    static long max = 0;
    static char[] OPERATORS = {'+', '*', '-'};
    
    public long solution(String expression) {
        List<Character> chars = new ArrayList<>();
        List<Long> numbers = new ArrayList<>();
        String[] numStrs = expression.split("-|\\*|\\+");
        for(String numStr : numStrs) numbers.add(Long.parseLong(numStr));
        String[] charStrs = expression.split("\\d+");
        for(String charStr : charStrs) {
            if(charStr.isEmpty()) continue;
            chars.add(charStr.charAt(0));
        }
        
        dfs(chars, numbers, new boolean[OPERATORS.length]);
        return max;
    }
    
    private void dfs(List<Character> chars, List<Long> numbers, boolean[] visited){
        if(chars.size() == 0){
            max = Math.max(max, Math.abs(numbers.get(0)));
            return;
        }
        
        for(int i = 0; i < OPERATORS.length; i++){
            if(visited[i] == true) continue;
            visited[i] = true;
            loop(OPERATORS[i], chars, numbers, visited);
            visited[i] = false;
        }
    }
    
    private void loop(char operator, List<Character> chars, List<Long> numbers, boolean[] visited){
        List<Character> cpChars = new ArrayList<>(chars);
        List<Long> cpNumbers = new ArrayList<>(numbers);
        boolean exist = false;
        for(int i = 0; i < chars.size(); i++){
            if(chars.get(i) == operator){
                exist = true;
                long n1 = numbers.get(i);
                long n2 = numbers.get(i+1);
                long newN = 0;
                switch(operator){
                    case '+':
                        newN = n1+n2;
                        break;
                    case '*':
                        newN = n1*n2;
                        break;
                    case '-':
                        newN = n1-n2;
                        break;
                }
                
                cpChars.remove(i);
                cpNumbers.remove(i);
                cpNumbers.set(i, newN);
                loop(operator, cpChars, cpNumbers, visited);
                break;
            }
        }
        
        if(exist == false){
            dfs(cpChars, cpNumbers, visited);
        }
    }
}