import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();
        for(String person : info){
            String[] options = person.split(" ");
            for(int a = 0; a < 2; a++){
                for(int b = 0; b < 2; b++){
                    for(int c = 0; c < 2; c++){
                        for(int d = 0; d < 2; d++){
                            StringBuilder sb = new StringBuilder();
                            sb.append(a==0?"-":options[0]);
                            sb.append(b==0?"-":options[1]);
                            sb.append(c==0?"-":options[2]);
                            sb.append(d==0?"-":options[3]);
                            String s = sb.toString();
                            if(!map.containsKey(s)) map.put(s, new ArrayList<>());
                            map.get(s).add(Integer.parseInt(options[4]));
                        }
                    }
                }
            }
        }
        
        for(Map.Entry<String, List<Integer>> e : map.entrySet()){
            Collections.sort(e.getValue());
        }
        
        int[] result = new int[query.length];
        for(int i = 0; i < query.length; i++){
            int scoreIdx = query[i].lastIndexOf(" ");
            String q = query[i].substring(0, scoreIdx).replaceAll(" and ", "");
            int score = Integer.parseInt(query[i].substring(scoreIdx+1));
            
            if(!map.containsKey(q)) continue;
            
            result[i] = findUpperCount(map.get(q), score);
        }
        
        return result;
    }
    
    public int findUpperCount(List<Integer> list, int score){
        // System.out.println(list);
        int s = 0;
        int e = list.size()-1;
        while(e >= s){
            int m = (e+s)/2;
            // System.out.println(s+", "+m+", "+e);
            if(list.get(m) >= score){
                e = m-1;
            }else{
                s = m+1;
            }
        }
        
        return list.size() - s;
    }
}