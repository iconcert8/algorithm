import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Box lastDBox = null;
        for(int i = 1; i <= n; i++){
            Box box = new Box(i, deliveries[i-1]);
            if(box.remain > 0){
                box.addPrev(lastDBox);
                lastDBox = box; 
            }
        }
        Box lastPBox = null;
        for(int i = 1; i <= n; i++){
            Box box = new Box(i, pickups[i-1]);
            if(box.remain > 0){
                box.addPrev(lastPBox);
                lastPBox = box;
            }
        }
        
        long result = 0;
        
        int currentPos = 0;
        while(lastDBox != null || lastPBox != null){
            currentPos = 0;
            if(lastDBox != null){
                currentPos = lastDBox.home;
                result += lastDBox.home;
                lastDBox = lastDBox.visit(cap);
                if(lastPBox == null){
                    result += currentPos;
                }
            }
            if(lastPBox != null){
                if(lastPBox.home > currentPos){
                    result += (2*lastPBox.home) - currentPos;
                }else{
                    result += currentPos;
                }
                lastPBox = lastPBox.visit(cap);
            }
        }
        return result;
    }
    
    class Box{
        int home;
        int remain;
        Box prev;
        Box next;
        
        public Box(int home, int remain){
            this.home = home;
            this.remain = remain;
        }
        
        public void addPrev(Box prevBox){
            if(prevBox == null) return;
            prevBox.next = this;
            this.prev = prevBox;
        }
        
        public Box visit(int cap){
            int diff = this.remain - cap;
            this.remain = diff;
            if(diff <= 0){
                if(prev != null){
                    return prev.visit(Math.abs(diff));
                }
                return prev;
            }
            return this;
        }
    }
}