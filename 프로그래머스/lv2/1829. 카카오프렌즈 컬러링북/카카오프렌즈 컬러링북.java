import java.util.*;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        Set<ColoringBookRowCol> usedSet = new HashSet<>();
        List<Set<ColoringBookRowCol>> areas = new ArrayList<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                ColoringBookRowCol startPoint = new ColoringBookRowCol(row, col, picture[row][col]);
                if (startPoint.value == 0) {
                    continue;
                }
                if (usedSet.contains(startPoint)) {
                    continue;
                }
                Set<ColoringBookRowCol> area = new HashSet<>();
                ColoringBookRowCol.recursiveSearch(picture, area, startPoint);

                areas.add(area);
                usedSet.addAll(area);
            }
        }


        int maxCount = areas.stream().max((o1, o2) -> o1.size() > o2.size() ? 1 : -1).get().size();
        return new int[]{areas.size(), maxCount};
    }
    
    static class ColoringBookRowCol {
        public int rowIndex;
        public int colIndex;

        public int value;

        public ColoringBookRowCol(int rowIndex, int colIndex, int value) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.value = value;
        }

        static void recursiveSearch(int[][] picture, Set<ColoringBookRowCol> set, ColoringBookRowCol startPoint) {
            if (set.contains(startPoint)) {
                return;
            }

            set.add(startPoint);

            int maxRow = picture.length - 1;
            int maxCol = picture[0].length - 1;
            int row = startPoint.rowIndex;
            int col = startPoint.colIndex;
            int val = startPoint.value;
            // top
            if (0 <= row - 1) {
                if (picture[row - 1][col] == val) {
                    recursiveSearch(picture, set, new ColoringBookRowCol(row - 1, col, val));
                }
            }
            // bottom
            if (maxRow >= row + 1) {
                if (picture[row + 1][col] == val) {
                    recursiveSearch(picture, set, new ColoringBookRowCol(row + 1, col, val));
                }
            }
            // left
            if (0 <= col - 1) {
                if (picture[row][col - 1] == val) {
                    recursiveSearch(picture, set, new ColoringBookRowCol(row, col - 1, val));
                }
            }
            // right
            if (maxCol >= col + 1) {
                if (picture[row][col + 1] == val) {
                    recursiveSearch(picture, set, new ColoringBookRowCol(row, col + 1, val));
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ColoringBookRowCol rolCol = (ColoringBookRowCol) o;
            return rowIndex == rolCol.rowIndex && colIndex == rolCol.colIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowIndex, colIndex);
        }

        @Override
        public String toString() {
            return "RowCol{" +
                    "rowIndex=" + rowIndex +
                    ", colIndex=" + colIndex +
                    ", value=" + value +
                    '}';
        }
    }
}