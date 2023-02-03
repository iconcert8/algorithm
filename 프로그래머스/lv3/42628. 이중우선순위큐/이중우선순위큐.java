import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        
        List<Integer> list = new ArrayList<>();
        for(String operation : operations){
            Oper(operation, list);
        }
        
        if(list.size() == 0) return new int[]{0, 0};
        
        return new int[]{list.get(list.size()-1), list.get(0)};
    }
    
    public void Oper(String operation, List<Integer> list){
        String[] arr = operation.split(" ");
        if(arr[0].equals("I")){
            int value = Integer.parseInt(arr[1]);
            list.add(value);
            Collections.sort(list);
            
        } else if(arr[0].equals("D")){
            int value = Integer.parseInt(arr[1]);
            if(value == 1){
                if(list.size() > 0) list.remove(list.size()-1);
            }
            
            if(value == -1){
                if(list.size() > 0) list.remove(0);
            }
        }
    }
    
}