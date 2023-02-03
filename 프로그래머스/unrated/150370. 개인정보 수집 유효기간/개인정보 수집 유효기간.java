import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new LinkedList<>();
        Day standard = new Day(today);
        
        Map<String, Integer> termMap = new HashMap<>();
        for(String t : terms){
            String[] arr = t.split(" ");
            termMap.put(arr[0], Integer.valueOf(arr[1]));
        }
        
        for(int i = 0; i < privacies.length; i++){
            String[] arr = privacies[i].split(" ");
            Day day = new Day(arr[0]);
            int term = termMap.get(arr[1]);
            day.add(term);
            if(day.isExpire(standard)){
                result.add(i+1);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    class Day{
        int y;
        int m;
        int d;
        
        public Day(String date){
            String[] arr = date.split("\\.");
            y = Integer.valueOf(arr[0]);
            m = Integer.valueOf(arr[1]);
            d = Integer.valueOf(arr[2]);
        }
        
        public void add(int m){
            this.m += m;
            while(this.m > 12){
                this.m -= 12;
                this.y += 1;
            }
        }
        
        public boolean isExpire(Day today){
            if(today.y > this.y) return true;
            if(today.y < this.y) return false;
            if(today.m > this.m) return true;
            if(today.m < this.m) return false;
            if(today.d >= this.d) return true;
            return false;
        }
    }
}