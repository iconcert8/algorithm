import java.util.*;
class Solution {
    public int solution(int N, int number) {
        if(number == N) return 1;
        int answer = -1;
        
        int count = 1;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for(int i = 1; i <= 8; i++) {
            int c = i;
            map.put(i, new HashSet<>(){{
                StringBuilder b = new StringBuilder();
                for(int j = 0; j < c; j++) b.append(N);
                add(Integer.parseInt(b.toString()));
            }});
        }
        
        while(++count <= 8){
            Set<Integer> nextSet = map.get(count);          
            List<int[]> caseList = numberCase(count);
            for(int[] cse : caseList){
                Set<Integer> fromSet = map.get(cse[0]);
                Set<Integer> toSet = map.get(cse[1]);
                merge(fromSet, toSet, nextSet);
                merge(toSet, fromSet, nextSet);
            }
            
            boolean isEqual = false;
            for(int v : nextSet){
                if(v == number){
                    isEqual = true;
                    answer = count;
                    break;
                }
            }
            if(isEqual) break;
            
            // System.out.println(count+": "+nextSet);
        }
        
        
        return answer;
    }
    
    public void merge(Set<Integer> fromSet, Set<Integer> toSet, Set<Integer> merge){
        for(int from : fromSet){
            for(int to : toSet){
                merge.add(from+to);
                merge.add(from*to);
                if(to != 0) merge.add(from/to);
                if(from-to >= 0) merge.add(from-to);
            }
        }
    }
    
    
    public List<int[]> numberCase(int count){
        int tmp = count;
        List<int[]> list = new LinkedList<>();
        while(tmp > 1){
            tmp--;
            list.add(new int[]{count-tmp, tmp});
        }
        
        return list;
    }
}