import java.util.*;
class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int maxX = 0;
        int maxY = 0;

        for (int[] rect : rectangle) {
            maxX = Math.max(maxX, rect[2]);
            maxY = Math.max(maxY, rect[3]);
        }


        int[][] map = new int[maxX * 2 + 2][maxY * 2 + 2];
        for (int[] rect : rectangle) {
            int sX = rect[0] * 2;
            int sY = rect[1] * 2;
            int eX = rect[2] * 2;
            int eY = rect[3] * 2;

            for (int i = sX; i <= eX; i++) {
                for (int j = sY; j <= eY; j++) {
                    map[i][j] = 1;
                }
            }
        }

        for (int[] rect : rectangle) {
            int sX = (rect[0] + 1) * 2 - 1;
            int sY = (rect[1] + 1) * 2 - 1;
            int eX = (rect[2] - 1) * 2 + 1;
            int eY = (rect[3] - 1) * 2 + 1;

            for (int i = sX; i <= eX; i++) {
                for (int j = sY; j <= eY; j++) {
                    map[i][j] = 0;

                }
            }
        }

//        for (int y = maxY * 2 + 1; y >= 0; y--) {
//            for (int x = 0; x <= maxX * 2 + 1; x++) {
//                System.out.print(map[x][y] + ", ");
//            }
//            System.out.println();
//        }


        int path1Dist = 0;
        int path2Dist = 0;
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        int path1X = characterX;
        int path1Y = characterY;

        int path2X = characterX;
        int path2Y = characterY;

        Set<String> passedPathSet = new HashSet<>();
        passedPathSet.add(characterX + " " + characterY);

        int minPathCount = 0;

        while (true) {
            if (path1X == itemX && path1Y == itemY) {
                minPathCount = path1Dist;
                break;
            }
            if (path2X == itemX && path2Y == itemY) {
                minPathCount = path2Dist;
                break;
            }


            int[] nextPath1 = getNextPath(map, passedPathSet, path1X, path1Y);
            if (nextPath1 != null) {
                passedPathSet.add(nextPath1[0] + " " + nextPath1[1]);
            }
            int[] nextPath2 = getNextPath(map, passedPathSet, path2X, path2Y);
            if (nextPath2 != null) {
                passedPathSet.add(nextPath2[0] + " " + nextPath2[1]);
            }

//            System.out.println("1 " + Arrays.toString(nextPath1));
//            System.out.println("2 " + Arrays.toString(nextPath2));
//            System.out.println();

            if (nextPath1 != null) {
                path1X = nextPath1[0];
                path1Y = nextPath1[1];
                path1Dist++;
            }

            if (nextPath2 != null) {
                path2X = nextPath2[0];
                path2Y = nextPath2[1];
                path2Dist++;
            }
        }

        return minPathCount / 2;
    }
    
    int[] getNextPath(int[][] map, Set<String> passedPathSet, int x, int y) {
        if (!passedPathSet.contains((x + 1) + " " + y)) {
            if (map[x + 1][y] == 1) {
                return new int[]{x + 1, y};
            }
        }

        if (!passedPathSet.contains((x - 1) + " " + y)) {
            if (map[x - 1][y] == 1) {
                return new int[]{x - 1, y};
            }
        }

        if (!passedPathSet.contains(x + " " + (y + 1))) {
            if (map[x][y + 1] == 1) {
                return new int[]{x, y + 1};
            }
        }

        if (!passedPathSet.contains(x + " " + (y - 1))) {
            if (map[x][y - 1] == 1) {
                return new int[]{x, y - 1};
            }
        }

        return null;
    }
}