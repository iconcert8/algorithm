import java.util.*;
class Solution {
    public int solution(int[][] game_board, int[][] table) {
        Map<String, List<Point>> pieceMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table.length; col++) {
                if (visited.contains(row + "_" + col)) {
                    continue;
                }
                if (pieceMap.containsKey(row + "_" + col)) {
                    continue;
                }

                if (table[row][col] == 0) {
                    continue;
                }

                Point p0 = new Point(row, col);
                pieceMap.put(p0.toString(), new ArrayList<>() {{
                    add(p0);
                }});
                visited.add(p0.toString());

                Queue<Point> q = new LinkedList<>();
                q.add(p0);
                while (!q.isEmpty()) {
                    Point p = q.poll();
                    //top
                    int r = p.row - 1;
                    int c = p.col;
                    if (r >= 0 && table[r][c] == 1) {
                        Point nP = new Point(r, c);
                        if (!visited.contains(nP.toString())) {
                            pieceMap.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited.add(nP.toString());
                        }
                    }

                    //bottom
                    r = p.row + 1;
                    c = p.col;
                    if (r < table.length && table[r][c] == 1) {
                        Point nP = new Point(r, c);
                        if (!visited.contains(nP.toString())) {
                            pieceMap.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited.add(nP.toString());
                        }
                    }

                    //left
                    r = p.row;
                    c = p.col - 1;
                    if (c >= 0 && table[r][c] == 1) {
                        Point nP = new Point(r, c);
                        if (!visited.contains(nP.toString())) {
                            pieceMap.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited.add(nP.toString());
                        }
                    }

                    //right
                    r = p.row;
                    c = p.col + 1;
                    if (c < table.length && table[r][c] == 1) {
                        Point nP = new Point(r, c);
                        if (!visited.contains(nP.toString())) {
                            pieceMap.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited.add(nP.toString());
                        }
                    }

                }
            }
        }

        List<Piece> pieces = new ArrayList<>();
        for (List<Point> pointList : pieceMap.values()) {
            pieces.add(new Piece(pointList));
        }

        //game board
        Map<String, List<Point>> pieceMap2 = new HashMap<>();
        Set<String> visited2 = new HashSet<>();
        for (int row = 0; row < game_board.length; row++) {
            for (int col = 0; col < game_board.length; col++) {
                if (visited2.contains(row + "_" + col)) {
                    continue;
                }
                if (pieceMap2.containsKey(row + "_" + col)) {
                    continue;
                }

                if (game_board[row][col] == 1) {
                    continue;
                }

                Point p0 = new Point(row, col);

                pieceMap2.put(p0.toString(), new ArrayList<>() {{
                    add(p0);
                }});
                visited2.add(p0.toString());

                Queue<Point> q = new LinkedList<>();
                q.add(p0);
                while (!q.isEmpty()) {
                    Point p = q.poll();
                    //top
                    int r = p.row - 1;
                    int c = p.col;
                    if (r >= 0 && game_board[r][c] == 0) {
                        Point nP = new Point(r, c);
                        if (!visited2.contains(nP.toString())) {
                            pieceMap2.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited2.add(nP.toString());
                        }
                    }

                    //bottom
                    r = p.row + 1;
                    c = p.col;
                    if (r < game_board.length && game_board[r][c] == 0) {
                        Point nP = new Point(r, c);
                        if (!visited2.contains(nP.toString())) {
                            pieceMap2.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited2.add(nP.toString());
                        }
                    }

                    //left
                    r = p.row;
                    c = p.col - 1;
                    if (c >= 0 && game_board[r][c] == 0) {
                        Point nP = new Point(r, c);
                        if (!visited2.contains(nP.toString())) {
                            pieceMap2.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited2.add(nP.toString());
                        }
                    }

                    //right
                    r = p.row;
                    c = p.col + 1;
                    if (c < game_board.length && game_board[r][c] == 0) {
                        Point nP = new Point(r, c);
                        if (!visited2.contains(nP.toString())) {
                            pieceMap2.get(p0.toString()).add(nP);
                            q.add(nP);
                            visited2.add(nP.toString());
                        }
                    }

                }
            }
        }

        List<Piece> pieces2 = new ArrayList<>();
        for (List<Point> pointList : pieceMap2.values()) {
            pieces2.add(new Piece(pointList));
        }

        int sum = 0;
        Set<Integer> usedJ = new HashSet<>();
        Set<Integer> usedI = new HashSet<>();
        for (int i = 0; i < pieces2.size(); i++) {
            for (int j = 0; j < pieces.size(); j++) {
                if (usedJ.contains(j)) {
                    continue;
                }
                if (usedI.contains(i)) {
                    continue;
                }

                if (pieces.get(j).compare(pieces2.get(i))) {
                    usedJ.add(j);
                    usedI.add(i);
                    sum += pieces.get(j).pointCount;
                }
            }
        }


        return sum;
    }

    static class Piece {
        List<List<Integer>> points = new ArrayList<>();
        int pointCount;

        public Piece(List<Point> pointList) {
            pointCount = pointList.size();

            Set<String> set = new HashSet<>();
            int minRow = Integer.MAX_VALUE;
            int minCol = Integer.MAX_VALUE;
            int maxRow = -1;
            int maxCol = -1;
            for (Point p : pointList) {
                set.add(p.toString());
                minRow = Math.min(minRow, p.row);
                minCol = Math.min(minCol, p.col);
                maxRow = Math.max(maxRow, p.row);
                maxCol = Math.max(maxCol, p.col);
            }

            for (int i = minRow; i <= maxRow; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = minCol; j <= maxCol; j++) {
                    if (set.contains(i + "_" + j)) {
                        row.add(1);
                    } else {
                        row.add(0);
                    }
                }
                points.add(row);
            }
        }

        public static List<List<Integer>> rotate(List<List<Integer>> points) {
            List<List<Integer>> l = new ArrayList<>();
            for (int i = 0; i < points.get(0).size(); i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = points.size() - 1; j >= 0; j--) {
                    row.add(points.get(j).get(i));
                }
                l.add(row);
            }
            return l;
        }

        boolean compare(Piece otherPiece) {
            if (pointCount != otherPiece.pointCount) {
                return false;
            }

            List<List<Integer>> otherPoints = otherPiece.points;

            //normal
            if (compare(otherPoints)) {
                return true;
            }

            //rotate
            otherPoints = rotate(otherPoints);
            if (compare(otherPoints)) {
                return true;
            }
            //rotate
            otherPoints = rotate(otherPoints);
            if (compare(otherPoints)) {
                return true;
            }
            //rotate
            otherPoints = rotate(otherPoints);
            return compare(otherPoints);
        }

        private boolean compare(List<List<Integer>> otherPoints) {
            if (points.size() != otherPoints.size()) {
                return false;
            }

            if (points.get(0).size() != otherPoints.get(0).size()) {
                return false;
            }

            for (int i = 0; i < points.size(); i++) {
                for (int j = 0; j < points.get(i).size(); j++) {
                    if (!Objects.equals(points.get(i).get(j), otherPoints.get(i).get(j))) {
                        return false;
                    }
                }
            }

            return true;
        }

        @Override
        public String toString() {
            StringBuilder b = new StringBuilder();
            for (List<Integer> point : points) {
                for (Integer integer : point) {
                    b.append(integer);
                }
                b.append("\n");
            }

            return b.toString();
        }
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return row + "_" + col;
        }
    }
}