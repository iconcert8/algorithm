import java.util.*;
class Solution {
    public String[] solution(String[] orders, int[] course) {

        String[][] splitOrders = new String[orders.length][];
        int maxLength = 0;
        for(int i = 0; i < orders.length; i++){
            String[] arr = orders[i].split("");
            maxLength = Math.max(maxLength, arr.length);
            Arrays.sort(arr);
            splitOrders[i] = arr;
        }
        
        List<String> result = new LinkedList<>();
        for(int c : course){
            
            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i < orders.length; i++){
                getCase(splitOrders[i], c, map);
            }
            PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>((e1, e2)->e2.getValue() - e1.getValue());
            for(Map.Entry<String, Integer> e : map.entrySet()){
                if(e.getValue() <= 1) continue;
                q.add(e);
            }
            
            if(q.peek() == null) continue;
            int max = q.peek().getValue();
            while(!q.isEmpty()){
                Map.Entry<String, Integer> e = q.poll();
                if(e.getValue() < max) break;
                result.add(e.getKey());
            }
        }
        
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    public void getCase(String[] arr, int targetCount, Map<String, Integer> map){
        getCase(arr, targetCount, 0, 0, new String[targetCount], map);
    }
    
    private void getCase(String[] arr, int targetCount, int depth, int count, String[] tmp, Map<String, Integer> map){
        if(count == targetCount){
            String value = String.join("", tmp);
            map.put(value, map.getOrDefault(value, 0) + 1);
            return;
        }
        
        for(int i = depth; i < arr.length; i++){
            tmp[count] = arr[i];
            getCase(arr, targetCount, i+1, count+1, tmp, map);
        }        
    }
    
}