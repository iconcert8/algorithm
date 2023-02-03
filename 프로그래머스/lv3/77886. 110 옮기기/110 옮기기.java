import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] result = new String[s.length];
        
        int k = 0;
       for (String str : s) {
        
            int cnt = 0;
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < str.length(); i++) {
                stack.add(str.charAt(i));
                if (stack.size() <= 2) continue;

                if (stack.peek() != '0') continue;
                char zero = stack.pop();

                if (stack.peek() != '1') {
                    stack.add(zero);
                    continue;
                }
                char one = stack.pop();

                if (stack.peek() != '1') {
                    stack.add(one);
                    stack.add(zero);
                    continue;
                }
                stack.pop();
                cnt++;
            }

            StringBuilder tmp = new StringBuilder();
            while (!stack.empty()) {
                tmp.insert(0, stack.pop());
            }
            str = tmp.toString();

            StringBuilder b = new StringBuilder();
            StringBuilder cB = new StringBuilder("");
            for (int i = 0; i < cnt; i++) {
                cB.append("110");
            }

            if (str.contains("0")) {
                int idxOf0 = str.lastIndexOf("0");
                b.append(str.substring(0, idxOf0 + 1));
                b.append(cB);
                b.append(str.substring(idxOf0 + 1));
            } else {
                b.append(cB);
                b.append(str);
            }

            result[k++] = b.toString();
        }
        
        return result;
    }
    
    
}