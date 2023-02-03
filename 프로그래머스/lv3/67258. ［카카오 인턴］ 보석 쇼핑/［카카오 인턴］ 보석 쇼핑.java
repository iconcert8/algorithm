import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for(String gem : gems){set.add(gem);}
        
        int[] result = new int[]{1,1};
        int minLength = 0;
        
        List<Map.Entry<Integer, String>> q = new ArrayList<>();
        Map<String, Integer> m = new HashMap<>();
        
        for(int i = 0; i < gems.length; i++){
            m.merge(gems[i], 1, Integer::sum);
            q.add(new AbstractMap.SimpleEntry<>(i+1, gems[i]));
            minLength++;
            if(set.size() == m.size()){
                break;
            }
        }
        while(true){
            String key = q.get(0).getValue();
            int count = m.get(key);
            
            if(count < 2) break;
            
            q.remove(0);
            m.merge(key, -1, Integer::sum);
            minLength--;
        }
        result[0] = q.get(0).getKey();
        result[1] = q.get(q.size()-1).getKey();

        
        int nextIdx = q.get(q.size()-1).getKey();
        int length = minLength; 
        for(int i = nextIdx; i < gems.length; i++){
            q.add(new AbstractMap.SimpleEntry<>(i+1, gems[i]));
            m.merge(gems[i], 1, Integer::sum);
            length++;
            
            while(true){
                String key = q.get(0).getValue();
                int count = m.get(key);
                if(count < 2) break;
                
                q.remove(0);
                m.merge(key, -1, Integer::sum);
                length--;
            }

            if(minLength > length){
                minLength = length;
                result[0] = q.get(0).getKey();
                result[1] = q.get(q.size()-1).getKey();
            }

            if(minLength == set.size()){
                break;
            }
        }
        
        return result;
    }
}