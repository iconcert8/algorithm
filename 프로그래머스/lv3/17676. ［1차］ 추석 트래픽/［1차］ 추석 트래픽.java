import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<Process> list = new ArrayList<>(lines.length);
        for(String line : lines){
            list.add(new Process(line));
        }
        
        int max = 0;
        for(int k = 0; k < lines.length; k++){
            Process p = list.get(k);
            LocalDateTime after1s = p.endTime.plus(Duration.ofMillis(999));
            int count = 0;
            for(int i = k; i < lines.length; i++){
                Process np = list.get(i);
                
                // p.endTime <= x <= after1s
                if(p.endTime.isEqual(np.endTime) || p.endTime.isBefore(np.endTime)){
                    if(after1s.isEqual(np.startTime) || after1s.isAfter(np.startTime)){
                        count++;
                    }
                }
            }
            
            max = Math.max(max, count);
        }
        
        return max;
    }
    
    static class Process{
        static String regex = "(.+-.+-.+:.+:.+) (.+)s";
        static String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.KOREA); 
        static Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        
        LocalDateTime startTime;
        LocalDateTime endTime;
        
        public Process(String line){
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String date = matcher.group(1);
            String ellapsed = matcher.group(2);
            endTime = LocalDateTime.parse(date, dateTimeFormatter);
            startTime = endTime.minus(Duration.ofMillis((int)(1000 * Double.parseDouble(ellapsed)-1)));
        }
    }
}