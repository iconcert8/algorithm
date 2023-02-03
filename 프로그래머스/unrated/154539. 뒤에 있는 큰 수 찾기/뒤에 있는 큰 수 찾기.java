import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {        
        int[] result = new int[numbers.length];
        Stack<int[]> stack = new Stack<>();
        for(int i = 0; i < numbers.length; i++){
            while(!stack.empty()){
                if(stack.peek()[1] >= numbers[i]) break;
                result[stack.pop()[0]] = numbers[i];
            }
            stack.push(new int[]{i, numbers[i]});
        }
        while(!stack.empty()) result[stack.pop()[0]] = -1;
        return result;
    }
}