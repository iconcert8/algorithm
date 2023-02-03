import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        List<List<int[]>> rooms = new ArrayList<>(){{add(new ArrayList<>());}};
        int[][] times = new int[book_time.length][2];
        for(int i = 0; i < book_time.length; i++){
            int s = parseTime(book_time[i][0]);
            int e = parseTime(book_time[i][1])+10;
            times[i] = new int[]{s, e};
        }
        Arrays.sort(times, (arr1, arr2)->arr1[0]-arr2[0]);
        
        for(int[] book : times){
            int s = book[0];
            int e = book[1];
            boolean needNewRoom = true;
            for(List<int[]> room : rooms){
                boolean enable = true;
                for(int[] seTime : room){
                    if(seTime[0] >= e || seTime[1] <= s) continue;
                    enable = false;
                    break;
                }
                if(enable) {
                    room.add(new int[]{s, e});
                    needNewRoom = false;
                    break;
                }
            }
            if(needNewRoom) rooms.add(new ArrayList<>(){{add(new int[]{s, e});}});
        }
        return rooms.size();
    }
    
    int parseTime(String str){
        String[] arr = str.split(":");
        int time = Integer.parseInt(arr[0])*60;
        time += Integer.parseInt(arr[1]);
        return time;
    }
}