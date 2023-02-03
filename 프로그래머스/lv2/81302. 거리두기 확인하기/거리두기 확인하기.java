import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int k = 0;
        for(String[] p : places){
            List<int[][]> closePeople = new ArrayList<>();//[사람[0,0], 사람[1,1]]
            String[][] place = new String[7][];
            for(int i = 0; i < 5; i++){ 
                place[i] = Arrays.copyOf(p[i].split(""), 7);
            }
            place[5] = new String[]{"","","","","","",""};
            place[6] = new String[]{"","","","","","",""};
            
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    String space = place[i][j];
                    if(!"P".equals(space)) continue;
                    if("P".equals(place[i][j+1])) 
                        closePeople.add(new int[][]{{i,j},{i,j+1}});
                    if("P".equals(place[i][j+2])) 
                        closePeople.add(new int[][]{{i,j},{i,j+2}});
                    if("P".equals(place[i+1][j])) 
                        closePeople.add(new int[][]{{i,j},{i+1,j}});
                    if("P".equals(place[i+2][j])) 
                        closePeople.add(new int[][]{{i,j},{i+2,j}});
                    if("P".equals(place[i+1][j+1])) 
                        closePeople.add(new int[][]{{i,j},{i+1,j+1}});
                    
                    if(i-1 >= 0 && "P".equals(place[i-1][j+1]))
                        closePeople.add(new int[][]{{i,j},{i-1,j+1}});
                }
            }    
            
            boolean isOk = true;
            for(int[][] pair : closePeople){
                int[] p1 = pair[0];
                int[] p2 = pair[1];
                
                if(p1[0] == p2[0]) {//같은 row에 있을 때
                    if(!place[p1[0]][(p1[1]+p2[1])/2].equals("X")){
                        isOk = false;
                        break;
                    }
                    continue;
                }
                
                if(p1[1] == p2[1]) {//같은 col에 있을 때
                    if(!place[(p1[0]+p2[0])/2][p1[1]].equals("X")){
                        isOk = false;
                        break;
                    }
                    
                    continue;
                }
                
                int[] minColPoint = p1[1] < p2[1] ? p1 : p2;
                int[] maxColPoint = p1[1] > p2[1] ? p1 : p2;                    
                int[] b1 = new int[]{minColPoint[0], minColPoint[1]+1};
                int[] b2 = new int[]{maxColPoint[0], maxColPoint[1]-1};
                
                if(!place[b1[0]][b1[1]].equals("X")
                  || !place[b2[0]][b2[1]].equals("X")){
                    isOk = false;
                    break;
                }
            }
            System.out.println();
            answer[k++] = isOk ? 1 : 0;
        }
        
        
        return answer;
    }
}