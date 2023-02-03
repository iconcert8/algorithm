import java.util.*;
class Solution {
    static int minCost = Integer.MAX_VALUE;    
    public int solution(int[][] board) {
        
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(0, 0, 0, 1));
        // 1기준 상하좌우 (0, 1, 2, 3)
        boolean[][][] visited = new boolean[board.length][board.length][4];
        visited[0][0][3] = true;
        int len = board.length;
        int minCost = 0;
        while(!q.isEmpty()){
            Robot robot = q.poll();
            if((robot.r1 == len - 1 && robot.c1 == len - 1)
               || (robot.r2 == len - 1 && robot.c2 == len - 1)){
                minCost = robot.cost;
                break;
            }    
            
            //move
            for(int i = 0; i < 4; i++){
                Robot movedBot = robot.move(i);
                if(!movedBot.isCorrectCoordinate(board)) continue;
                if(visited[movedBot.r1][movedBot.c1][movedBot.getDir()]) continue;
                
                visited[movedBot.r1][movedBot.c1][movedBot.getDir()] = true;
                movedBot.cost = robot.cost + 1;
                q.add(movedBot);
            }
            
            //rotate
            for(int i = 0; i < 4; i++){
                if(!robot.isRotatable(board, i)) continue;
                
                Robot rotatedBot = robot.rotate(i);
                if(!rotatedBot.isCorrectCoordinate(board)) continue;
                if(visited[rotatedBot.r1][rotatedBot.c1][rotatedBot.getDir()]) continue;
                
                visited[rotatedBot.r1][rotatedBot.c1][rotatedBot.getDir()] = true;
                rotatedBot.cost = robot.cost + 1;
                q.add(rotatedBot);
            }
        }
        
        return minCost;
    }
    
    class Robot{
        int r1;
        int c1;
        int r2;
        int c2;
        int cost;
        
        public Robot(int r1, int c1, int r2, int c2){
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
        
        public int getDir(){
            if(r1 > r2){
                return 0;
            }
            
            if(r1 < r2){
                return 1;
            }
            
            if(c1 > c2){
                return 2;
            }
            
            return 3;
        }
        
        public boolean isCorrectCoordinate(int[][] board){
            int len = board.length;
            if(r1 < 0 || r1 >= len || c1 < 0 || c1 >= len 
              || r2 < 0 || r2 >= len || c2 < 0 || c2 >= len){
                return false;
            }
            
            if(board[r1][c1] != 0 || board[r2][c2] != 0){
                return false;
            }
            
            return true;
        }
        
        // rot 0, 1, 2, 3 (1시계, 1반시계, 2시계, 2반시계)
        public boolean isRotatable(int[][] board, int rot){
            int[] range = getRotateRange(rot);
            int len = board.length;
            
            if(range[0] < 0 || range[0] >= len || range[1] < 0 || range[1] >= len 
              || range[2] < 0 || range[2] >= len || range[3] < 0 || range[3] >= len) 
                return false;
            
            for(int i = range[0]; i <= range[2]; i++){
                for(int j = range[1]; j <= range[3]; j++){
                    if(board[i][j] != 0){
                        return false;
                    }
                }
            }
            
            return true;
        }
        
        //[x,x,x,x] (sr, sc, er, ec);
        int[] getRotateRange(int rot){
            Robot rotateRobot = rotate(rot);
            if(rot < 2){
                return new int[]{
                    Math.min(r2, rotateRobot.r2),
                    Math.min(c2, rotateRobot.c2),
                    Math.max(r2, rotateRobot.r2),
                    Math.max(c2, rotateRobot.c2)
                };
            }
            
            return new int[]{
                Math.min(r1, rotateRobot.r1),
                Math.min(c1, rotateRobot.c1),
                Math.max(r1, rotateRobot.r1),
                Math.max(c1, rotateRobot.c1)
            };
        }
        
        // rot 0, 1, 2, 3 (1시계, 1반시계, 2시계, 2반시계)
        public Robot rotate(int rot){
            if(rot == 0){
                return clockBy1();
            }
            
            if(rot == 1){
                return counterClockBy1();
            }
            
            if(rot == 2){
                return clockBy2();
            }
            
            if(rot == 3){
                return counterClockBy2();
            }
            
            return new Robot(r1, c1, r2, c2);
        }
        
        //dir 0, 1, 2, 3 (상, 하, 좌, 우)
        public Robot move(int dir){
            if(dir == 0){
                return up();
            }
            
            if(dir == 1){
                return down();
            }
            
            if(dir == 2){
                return left();
            }
            
            if(dir == 3){
                return right();
            }
            
            return new Robot(r1, c1, r2, c2);
        }
        
        public Robot clockBy1(){
            if(r1 > r2){
                return new Robot(r1, c1, r1, c1 + 1);
            }
            
            if(r1 < r2){
                return new Robot(r1, c1, r1, c1 - 1);
            }
            
            // r1 == r2
            if(c1 < c2){
                return new Robot(r1, c1, r1+1, c1);
            }else{
                return new Robot(r1, c1, r1 - 1, c1);
            }
        }
        
        public Robot counterClockBy1(){
            if(r1 > r2){
                return new Robot(r1, c1, r1, c1 - 1);
            }
            
            if(r1 < r2){
                return new Robot(r1, c1, r1, c1 + 1);
            }
            
            // r1 == r2
            if(c1 < c2){
                return new Robot(r1, c1, r1 - 1, c1);
            }else{
                return new Robot(r1, c1, r1 + 1, c1);
            }
        }
        
        public Robot clockBy2(){
            if(r1 > r2){
                return new Robot(r2, c2-1, r2, c2);
            }
            
            if(r1 < r2){
                return new Robot(r2, c2+1, r2, c2);
            }
            
            // r1 == r2
            if(c1 < c2){
                return new Robot(r2-1, c2, r2, c2);
            }else{
                return new Robot(r2+1, c2, r2, c2);
            }
        }
        
        public Robot counterClockBy2(){
            if(r1 > r2){
                return new Robot(r2, c2+1, r2, c2);
            }
            
            if(r1 < r2){
                return new Robot(r2, c2-1, r2, c2);
            }
            
            // r1 == r2
            if(c1 < c2){
                return new Robot(r2+1, c2, r2, c2);
            }else{
                return new Robot(r2-1, c2, r2, c2);
            }
        }
        
        
        
        public Robot left(){
            return new Robot(r1, c1 - 1, r2, c2 - 1);
        }
        
        public Robot right(){
            return new Robot(r1, c1 + 1, r2, c2 + 1);
        }
        
        public Robot up(){
            return new Robot(r1-1, c1, r2-1, c2);
        }
        
        public Robot down(){
            return new Robot(r1+1, c1, r2+1, c2);
        }
        
        @Override
        public String toString(){
            return String.format("(%s, %s, %s, %s)", r1, c1, r2, c2);
        }
    }
}