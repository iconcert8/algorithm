import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        if(play_time.equals(adv_time)){
            return "00:00:00";
        }
        
        List<Log> logList = new ArrayList<>();
        for(String l : logs){
            String[] lSplit = l.split("-");
            logList.add(
                new Log(
                    toSeconds(lSplit[0]), 
                    toSeconds(lSplit[1])
                ));
        }
        
        int playTime = toSeconds(play_time);
        int advTime = toSeconds(adv_time);
        int[] timeseries = new int[playTime];
        for(Log log : logList){
            log.updateTimeseries(timeseries);
        }
        
        long sum = 0;
        for(int i = 0; i < advTime; i++){
            sum += timeseries[i];
        }
        
        long maxSum = sum;
        int maxStartTime = 0;
        for(int i = 1; i <= playTime-advTime; i++){
            int s = i;
            int e = i - 1 + advTime;
            
            sum -= timeseries[s-1];
            sum += timeseries[e];
            
            if(maxSum < sum){
                maxStartTime = s;
                maxSum = sum;
            }
        }
        
        // System.out.println(toTime(maxSum));
        
        return toTime(maxStartTime);
    }
    
    public int toSeconds(String time){
        String[] timeSplit = time.split(":");
        int h = Integer.parseInt(timeSplit[0]);
        int m = Integer.parseInt(timeSplit[1]);
        int s = Integer.parseInt(timeSplit[2]);
        return (h*60*60) + (m*60) + s;
    }
    
    public String toTime(int seconds){
        int h = seconds/(60*60);
        seconds = seconds - (h * (60*60));
        int m = seconds/60;
        seconds = seconds - (m * 60);
        int s = seconds;
        
        String strH = h+"";
        String strM = m+"";
        String strS = s+"";
        
        if(h < 10){
            strH = "0"+h;
        }
        if(m < 10){
            strM = "0"+m;
        }
        if(s < 10){
            strS = "0"+s;
        }
        
        return strH+":"+strM+":"+strS;
    }
    
    public class Log{
        int start;
        int end;
        
        public Log(int start, int end){
            this.start = start;
            this.end = end;
        }
        
        public void updateTimeseries(int[] timeseries){
            for(int i = start; i < end; i++){
                timeseries[i] += 1;
            }
        }
    }
}