import java.util.*;
class Solution {
    public String[] solution(int[][] line) {
        long[][] lines = new long[line.length][3];
        for (int i = 0; i < line.length; i++) {
            for (int j = 0; j < 3; j++) {
                lines[i][j] = line[i][j];
            }
        }
        
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        Set<XYIndex> intersectionSet = new HashSet<>();


        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                double[] point = getIntersectionPoint(lines[i], lines[j]);
                if (point == null) {
                    continue;
                }

                Double doubleX = point[0];
                Double doubleY = point[1];
                if (isInt(doubleX) && isInt(doubleY)) {
                    long xValue = doubleX.longValue();
                    long yValue = doubleY.longValue();
                    intersectionSet.add(new XYIndex(xValue, yValue));
                    if (minX > xValue) {
                        minX = xValue;
                    }

                    if (maxX < xValue) {
                        maxX = xValue;
                    }

                    if (minY > yValue) {
                        minY = yValue;
                    }

                    if (maxY < yValue) {
                        maxY = yValue;
                    }
                }
            }
        }

        if (minX == Long.MAX_VALUE || minY == Long.MAX_VALUE || maxX == Long.MIN_VALUE || maxY == Long.MIN_VALUE) {
            return null;
        }


        List<String> answer = new ArrayList<>();
        for (long i = maxY; i >= minY; i--) {
            StringBuilder result = new StringBuilder();
            for (long j = minX; j <= maxX; j++) {
                if (intersectionSet.contains(new XYIndex(j, i))) {
                    result.append("*");
                } else {
                    result.append(".");
                }
            }
            answer.add(result.toString());
        }
        return answer.toArray(new String[answer.size()]);
    }
    
    double[] getIntersectionPoint(long[] f1, long[] f2) {
        long A = f1[0];
        long B = f1[1];
        long E = f1[2];
        long C = f2[0];
        long D = f2[1];
        long F = f2[2];

        long denominator = (A * D) - (B * C);
        if (denominator == 0) {
            return null;
        }

        return new double[]{(B * F - E * D) / (double) denominator, (E * C - A * F) / (double) denominator};
    }
    
    boolean isInt(double value) {
        return value == (long) value;
    }
    
    class XYIndex {
        public long x;
        public long y;

        public XYIndex(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            XYIndex that = (XYIndex) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}