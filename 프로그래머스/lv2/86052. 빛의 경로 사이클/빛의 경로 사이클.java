import java.util.*;


class Solution {
    public int[] solution(String[] grid) {
        LightNode[][] nodeGrid = new LightNode[grid.length][grid[0].split("").length];
        for (int row = 0; row < grid.length; row++) {
            String[] rowNode = grid[row].split("");
            for (int col = 0; col < rowNode.length; col++) {
                nodeGrid[row][col] = new LightNode(rowNode[col], row, col);
            }
        }

        List<Integer> resultCount = new ArrayList<>();
        int[][] directArray = new int[][]{
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        Set<LightPath> globalPassedPath = new HashSet<>();

        for (int row = 0; row < nodeGrid.length; row++) {
            for (int col = 0; col < nodeGrid[row].length; col++) {
                for (int i = 0; i < 4; i++) {
                    LightPath lightPath = new LightPath(nodeGrid[row][col], directArray[i]);
                    Set<LightPath> passedPath = new HashSet<>();
                    LightPath currentPath = lightPath;

                    if (globalPassedPath.contains(currentPath)) {
                        continue;
                    }

                    while (!passedPath.contains(currentPath)) {
                        passedPath.add(currentPath);

                        LightNode nextNode = getNext(nodeGrid, currentPath);
                        currentPath = nextNode.createNextPath(currentPath);
                    }

                    globalPassedPath.addAll(passedPath);

                    if (currentPath.equals(lightPath)) {
                        resultCount.add(passedPath.size());
                    }
                }
            }
        }

        return resultCount.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
    
    LightNode getNext(LightNode[][] nodeGrid, LightPath currentPath) {
        int maxRow = nodeGrid.length - 1;
        int maxCol = nodeGrid[0].length - 1;

        int currentRow = currentPath.node.rowIdx;
        int currentCol = currentPath.node.colIdx;

        int nextRow = currentRow + currentPath.direct[1];
        int nextCol = currentCol + currentPath.direct[0];

        if (nextRow < 0) {
            nextRow = maxRow;
        }
        if (nextRow > maxRow) {
            nextRow = 0;
        }
        if (nextCol < 0) {
            nextCol = maxCol;
        }
        if (nextCol > maxCol) {
            nextCol = 0;
        }

        return nodeGrid[nextRow][nextCol];
    }

    static class LightPath {
        public LightNode node;
        //left{-1, 0}, right{1, 0}, up{0, -1}, down{0, 1}
        public int[] direct;

        public LightPath(LightNode node, int[] direct) {
            this.node = node;
            this.direct = direct;
        }

        static int[] turnLeft(int[] currentDirect) {
            if (currentDirect[0] == -1) {
                return new int[]{0, 1};
            }

            if (currentDirect[0] == 1) {
                return new int[]{0, -1};
            }

            if (currentDirect[1] == -1) {
                return new int[]{-1, 0};
            }

//            if(currentDirect[1] == 1){
            return new int[]{1, 0};
//            }

        }

        static int[] turnRight(int[] currentDirect) {
            if (currentDirect[0] == -1) {
                return new int[]{0, -1};
            }

            if (currentDirect[0] == 1) {
                return new int[]{0, 1};
            }

            if (currentDirect[1] == -1) {
                return new int[]{1, 0};
            }

//            if(currentDirect[1] == 1){
            return new int[]{-1, 0};
//            }

        }

        @Override
        public String toString() {
            return "LightPath{" +
                    "node=" + node +
                    ", direct=" + Arrays.toString(direct) +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LightPath lightPath = (LightPath) o;
            return Objects.equals(node, lightPath.node) && Arrays.equals(direct, lightPath.direct);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(node);
            result = 31 * result + Arrays.hashCode(direct);
            return result;
        }
    }

    class LightNode {
        public String type;
        public int rowIdx;
        public int colIdx;

        public LightNode(String type, int rowIdx, int colIdx) {
            this.type = type;
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }

        public LightPath createNextPath(LightPath currentPath) {
            LightPath nextLightPath = null;
            switch (type) {
                case "S":
                    nextLightPath = new LightPath(this, currentPath.direct.clone());
                    break;
                case "L":
                    nextLightPath = new LightPath(this, LightPath.turnLeft(currentPath.direct));
                    break;
                case "R":
                    nextLightPath = new LightPath(this, LightPath.turnRight(currentPath.direct));
                    break;
                default:
                    break;
            }

            return nextLightPath;
        }

        @Override
        public String toString() {
            return "LightNode{" +
                    "type='" + type + '\'' +
                    ", rowIdx=" + rowIdx +
                    ", colIdx=" + colIdx +
                    '}';
        }
    }
}