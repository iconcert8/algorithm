class Solution {
    static int maxDungeonCount = 0;
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        searchDungeons(dungeons, visited, k, 0);
        return maxDungeonCount;
    }
    
    void searchDungeons(int[][] dungeons, boolean[] visited, int remainK, int passedCount) {
        if (passedCount == dungeons.length) {
            maxDungeonCount = dungeons.length;
            return;
        }


        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (dungeons[i][0] > remainK) {
                maxDungeonCount = Math.max(maxDungeonCount, passedCount);
                continue;
            }
            visited[i] = true;
            searchDungeons(dungeons, visited, remainK - dungeons[i][1], passedCount + 1);
            visited[i] = false;
        }
    }
}