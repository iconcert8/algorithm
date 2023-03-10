import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length()-1);
        String regex = "\\d+(?:,\\d+)*";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(s);
        
        List<Set<Integer>> list = new ArrayList<>();
        while (matcher.find()) {
            String str = matcher.group(0);
            String[] splitArr = str.split(",");
            Set<Integer> set = new HashSet<>();
            list.add(set);
            for(String splitStr : splitArr) set.add(Integer.parseInt(splitStr));
        }
        
        Collections.sort(list, (s1, s2)->s1.size()-s2.size());
        
        Map<Integer, Integer> orderMap = new HashMap<>();
        for(int i = 0; i < list.size(); i++){
            Set<Integer> set = list.get(i);
            for(int e : set){
                if(orderMap.containsKey(e)) continue;
                orderMap.put(e, i);
            }
        }
        
        int[] answer = new int[list.size()];
        for(Map.Entry<Integer, Integer> e : orderMap.entrySet()){
            answer[e.getValue()] = e.getKey();
        }
        
        return answer;
    }
}