import java.util.*;
class Solution {
    
    static Map<String, Set<Cell>> valueMap = new HashMap<>();
    
    public String[] solution(String[] commands) {
        
        Cell[][] cells = new Cell[51][51];
        for(int i = 1; i < 51; i++){
            for(int j = 1; j < 51; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
        
        List<String> result = new LinkedList<>();
        for(int i = 0; i < commands.length; i++){
            Command command = Command.of(commands[i]);
            command.execute(cells);
            if(command instanceof Print){
                result.add(((Print)command).value);
            }
            
            // for(int r = 1; r < 5; r++){
            //     for(int c = 1; c < 5; c++){
            //         System.out.print(cells[r][c].value+", ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }
        
        return result.toArray(new String[result.size()]);
    }
    
    public static class Cell{
        public int r;
        public int c;
        public String value;
        public Set<Cell> group = new HashSet<>();
        
        public Cell(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        @Override
        public String toString(){
            return "("+r+","+c+","+value+")";
        }
        
        public void update(String otherValue){
            update(otherValue, new HashSet<>());           
        }
        
        public void update(String otherValue, Set<Cell> visited){
            if(value != null)
                valueMap.get(value).remove(this);
            
            this.value = otherValue;
            
            Set<Cell> sameValueCells = null;
            if(otherValue != null){
                if(!valueMap.containsKey(otherValue)){
                    sameValueCells = new HashSet<>();
                    valueMap.put(otherValue, sameValueCells);
                }else{
                    sameValueCells = valueMap.get(otherValue);
                }
                sameValueCells.add(this);
            }
            
            if(visited.contains(this)) return;
            visited.add(this);
            for(Cell c : group){
                c.update(otherValue, visited);
            }
        }
        
        public void merge(Cell otherCell){
            this.group.add(otherCell);
            otherCell.group.add(this);
        }
        
        public void unmerge(){
            Set<Cell> visited = new HashSet<>();
            visited.add(this);
            for(Cell cell : group){
                cell.unmerge(visited);
            }
            group = new HashSet<>();
        }
        
        private void unmerge(Set<Cell> visited){
            if(this.value != null){
                valueMap.get(this.value).remove(this);
            }
            this.value = null;
            for(Cell cell : group){
                if(visited.contains(cell)) continue;
                visited.add(cell);
                cell.unmerge(visited);
            }
            group = new HashSet<>();
        }
    }
    
    public static abstract class Command{
        public static Command of(String commandStr){
            String[] arr = commandStr.split(" ");
            String com = arr[0];
            
            Command command = null;
            switch(com){
                case "UPDATE":
                    if(arr.length > 3){
                        command = new UpdateCell();
                        command.from(arr);
                    }else{
                        command = new UpdateValue();
                        command.from(arr);
                    }
                    break;
                case "MERGE":
                    command = new Merge();
                    command.from(arr);
                    break;
                case "UNMERGE":
                    command = new UNMerge();
                    command.from(arr);
                    break;
                case "PRINT":
                    command = new Print();
                    command.from(arr);
                    break;
            }
            return command;
        }
        
        public abstract void from(String[] arr);
        public abstract void execute(Cell[][] cells);
    }
    
    static class UpdateCell extends Command{
        int r, c;
        String value;
        
        @Override
        public void from(String[] arr){
            this.r = Integer.valueOf(arr[1]);
            this.c = Integer.valueOf(arr[2]);
            this.value = arr[3];
        }
        
        @Override
        public void execute(Cell[][] cells){
            Cell cell = cells[r][c];
            cell.update(value);
        }
    }
    
    static class UpdateValue extends Command{
        String value1;
        String value2;
        @Override
        public void from(String[] arr){
            this.value1 = arr[1];
            this.value2 = arr[2];
        }
        
        @Override
        public void execute(Cell[][] cells){
            Set<Cell> set = new HashSet<>();
            if(value1 != null)
                set = new HashSet<>(valueMap.getOrDefault(value1, new HashSet<>()));                
            Set<Cell> visited = new HashSet<>();
            for(Cell cell : set){
                cell.update(value2, visited);
            }
        }
    }
    
    static class Merge extends Command{
        int r1, c1;
        int r2, c2;
        
        @Override
        public void from(String[] arr){
            this.r1 = Integer.valueOf(arr[1]);
            this.c1 = Integer.valueOf(arr[2]);
            this.r2 = Integer.valueOf(arr[3]);
            this.c2 = Integer.valueOf(arr[4]);
        }
        
        @Override
        public void execute(Cell[][] cells){
            Cell cell1 = cells[r1][c1];
            Cell cell2 = cells[r2][c2];
            if(cell1 == cell2) return;
            if(cell1.value != null && cell2.value != null){
                cell2.update(cell1.value);
            }else if(cell1.value == null && cell2.value != null){
                cell1.update(cell2.value);
            }else if(cell1.value != null && cell2.value == null){
                cell2.update(cell1.value);
            }
            cell1.merge(cell2);
        }
    }
    
    static class UNMerge extends Command{
        int r, c;
        @Override
        public void from(String[] arr){
            this.r = Integer.valueOf(arr[1]);
            this.c = Integer.valueOf(arr[2]);
        }
        
        @Override
        public void execute(Cell[][] cells){
            cells[r][c].unmerge();
        }
    }
    
    static class Print extends Command{
        int r,c;
        String value;
        @Override
        public void from(String[] arr){
            this.r = Integer.valueOf(arr[1]);
            this.c = Integer.valueOf(arr[2]);
        }
        
        @Override
        public void execute(Cell[][] cells){
            Cell cell = cells[r][c];
            if(cell.value == null){
                this.value = "EMPTY";
                return;
            }
            this.value = cell.value;
        }
    }
}