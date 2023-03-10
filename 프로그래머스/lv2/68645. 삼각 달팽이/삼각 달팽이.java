import java.util.*;
class Solution {
    public int[] solution(int n) {
        int totalCount = (n*(n+1))/2;
        List<int[]> list = new ArrayList<>(n);
        for(int i = 0; i < n; i++) list.add(new int[i+1]);
        
        int length = n, num = 1, direction = 0, y = -1, x = 0;
        while(num < totalCount+1){
            for(int i = 0; i < length; i++){
                // down
                if(direction == 0){
                    y++;
                    list.get(y)[x] = num;
                }
                // right
                else if(direction == 1){
                    x++;
                    list.get(y)[x] = num;
                }
                // up
                else if(direction == 2){
                    y--;
                    x--;
                    list.get(y)[x] = num;
                }
                num++;
            }
            
            direction = (direction + 1)%3;
            length--;
        }
        
        int[] result = new int[totalCount];
        int idx = 0;
        while(idx < totalCount){
            for(int[] arr : list){
                for(int i = 0; i < arr.length; i++){
                    result[idx++] = arr[i];
                }
            }
        }
        
        return result;
    }
}