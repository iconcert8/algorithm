import java.util.*;
class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        
        //[4]: 좌표 별 0-상 1-하 2-좌 3-우
        Structure[][][] totalFrame = new Structure[n+1][n+1][4];
        
        for(int[] arr : build_frame){
            Structure struct = Structure.of(arr[0], arr[1], arr[2], arr[3]);
            struct.action(totalFrame);
        }
        
        List<Structure> list = new ArrayList<>();
        for(int x = 0; x < n+1; x++){
            for(int y = 0; y < n+1; y++){
                if(totalFrame[x][y][0] != null) list.add(totalFrame[x][y][0]);
                if(totalFrame[x][y][3] != null) list.add(totalFrame[x][y][3]);
            }
        }
        Collections.sort(list);
        
        int[][] answer = new int[list.size()][3];
        for(int i = 0; i < list.size(); i++){
            Structure struct = list.get(i);
            answer[i][0] = struct.x;
            answer[i][1] = struct.y;
            answer[i][2] = struct.type;
        }
        
        return answer;
    }
}

abstract class Structure implements Comparable<Structure>{
    int x;
    int y;
    int type;
    int action;
    private Structure(){}

    Structure(int x, int y, int type, int action){
        this.x = x;
        this.y = y;
        this.type = type;
        this.action = action;
    }

    public static Structure of(int x, int y, int type, int action){
        if(type == 0){
            return new Pillar(x,y, type,action);
        }

        return new Plane(x,y,type,action);
    }
    
    @Override
	public int compareTo(Structure o) {
        if(this.x > o.x) return 1;
        if(this.x < o.x) return -1;
        
        if(this.y > o.y) return 1;
        if(this.y < o.y) return -1;
        
        if(this.type > o.type) return 1;
        if(this.type < o.type) return -1;
        
        return 0;
	}

    public abstract boolean isValid(Structure[][][] totalFrame);
    public abstract void action(Structure[][][] totalFrame);
}

class Pillar extends Structure{
    Pillar(int x, int y, int type, int action){
        super(x,y,type, action);
    }

    public boolean isValid(Structure[][][] totalFrame){
        //설치
        if(action == 1){
            if(y == 0) return true;
            
            Structure[] structures = totalFrame[x][y];
            for(Structure structure : structures){
                if(structure == this) continue;
                if(structure != null) return true;
            }
            return false;
        }
        
        //삭제
        Structure tmp = totalFrame[x][y][0];
        totalFrame[x][y][0] = null;
        totalFrame[x][y+1][1] = null;
        
        Structure[] topStructures = totalFrame[x][y+1];
        boolean isOk = true;
        for(Structure structure : topStructures){
            if(structure == null) continue;
            if(!structure.isValid(totalFrame)){
                isOk = false;
                break;
            }
        }
        totalFrame[x][y][0] = tmp;
        totalFrame[x][y+1][1] = tmp;
        return isOk;
    }
    
    public void action(Structure[][][] totalFrame){
        if(isValid(totalFrame)){
            if(action == 1){
                totalFrame[x][y][0] = this;
                totalFrame[x][y+1][1] = this;    
            }else{
                totalFrame[x][y][0] = null;
                totalFrame[x][y+1][1] = null;
            }
            
        }
    }
}
    
class Plane extends Structure{
    public Plane(int x, int y,int type, int action){
        super(x,y,type, action);
    }

    public boolean isValid(Structure[][][] totalFrame){
        //설치
        if(action == 1){
            if(totalFrame[x][y][1] != null && totalFrame[x][y][1] instanceof Pillar) return true;
            
            if(totalFrame[x+1][y][1] != null && totalFrame[x+1][y][1] instanceof Pillar) return true;
            
            if(totalFrame[x][y][2] != null && totalFrame[x+1][y][3] != null 
              && totalFrame[x][y][2] instanceof Plane && totalFrame[x+1][y][3] instanceof Plane ) return true;
            
            return false;
        }
        
        //삭제
        Structure tmp = totalFrame[x][y][3];
        totalFrame[x][y][3] = null;
        totalFrame[x+1][y][2] = null;
        
        boolean isOk = true;
        Structure[] leftStructures = totalFrame[x][y];
        for(Structure structure : leftStructures){
            if(structure == null) continue;
            if(!structure.isValid(totalFrame)){
                isOk = false;
                break;
            }
        }
        Structure[] rightStructures = totalFrame[x+1][y];
        for(Structure structure : rightStructures){
            if(structure == null) continue;
            if(!structure.isValid(totalFrame)){
                isOk = false;
                break;
            }
        }
        totalFrame[x][y][3] = tmp;
        totalFrame[x+1][y][2] = tmp;
        return isOk;
    }
    
    public void action(Structure[][][] totalFrame){
        if(isValid(totalFrame)){
            if(action == 1){
                totalFrame[x][y][3] = this;
                totalFrame[x+1][y][2] = this;    
            }else{
                totalFrame[x][y][3] = null;
                totalFrame[x+1][y][2] = null;    
            }
        }
    }
}