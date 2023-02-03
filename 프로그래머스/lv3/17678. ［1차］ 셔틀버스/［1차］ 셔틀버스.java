import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);
        List<Integer> crewTimeTable = new ArrayList<>(timetable.length);
        for(int i = 0; i < timetable.length; i++) crewTimeTable.add(timeToInt(timetable[i]));
        
        int currentTime = timeToInt("09:00");
        while(n-- > 0){
            
            int tmpM = m;
            while(crewTimeTable.size() > 0 && tmpM > 0){
                if(currentTime >= crewTimeTable.get(0)){
                    //마지막 자리 차지.
                    if(n == 0 && tmpM == 1){
                        return intToTime(crewTimeTable.get(0) - 1);
                    }
                    
                    tmpM--;
                    crewTimeTable.remove(0);
                    continue;
                }
                break;
            }
            
            //마지막 시간
            if(n == 0){
                return intToTime(currentTime);
            }
            
            currentTime += t;
        }
        
        return "";
    }
    
    public int timeToInt(String time){
        String[] arr = time.split(":");
        int h = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        
        return h*60 + m;        
    }
    
    public String intToTime(int time){
        int h = time/60;
        int m = time - (h*60);
        String sh;
        String sm;
        if(h < 10) 
            sh = "0"+h;
        else
            sh = ""+h;
        if(m < 10) 
            sm = "0"+m;
        else
            sm = ""+m;
        
        return sh+":"+sm;
    }
}