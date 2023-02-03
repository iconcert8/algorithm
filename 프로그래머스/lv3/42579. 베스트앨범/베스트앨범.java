import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        
        // key: genre, value: Que<[idx, playCount]>
        Map<String, PriorityQueue<int[]>> map = new HashMap<>();
        Map<String, Integer> countTotal = new HashMap<>();
        for(int i = 0; i < n; i++){
            PriorityQueue<int[]> value = null;
            if(map.containsKey(genres[i])){
                value = map.get(genres[i]);
            }else{
                value = new PriorityQueue<>((o1, o2)->o2[1]-o1[1]);
                map.put(genres[i], value);
            }
            
            value.add(new int[]{i, plays[i]});
            
            countTotal.merge(genres[i], plays[i], Integer::sum);
        }
        
        //Object[]{genre, totalCount}
        PriorityQueue<Object[]> q = new PriorityQueue<>((o1, o2)->(int)o2[1] - (int)o1[1]);
        
        for(Map.Entry<String, Integer> e : countTotal.entrySet()){
            q.add(new Object[]{e.getKey(), e.getValue()});
        }
        
        List<Integer> result = new LinkedList<>();        
        while(!q.isEmpty()){
            Object[] o = q.poll();
            String gen = (String)o[0];
            
            PriorityQueue<int[]> songs = map.get(gen);
            
            int limitTo2 = 2;
            while(limitTo2-- > 0 && !songs.isEmpty()){
                int[] s = songs.poll();
                result.add(s[0]);
            }
        }
        
        
        return result.stream().mapToInt(i->i).toArray();
    }
}