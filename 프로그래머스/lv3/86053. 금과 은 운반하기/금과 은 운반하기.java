class Solution {
    // 참조: https://yabmoons.tistory.com/714
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {

        long start = 0;
        long end = (long) Math.pow(10, 14) * 4;
        long mid = (start + end) / 2;
        long result = end;

        while (start <= end) {
            if (isPossible(a, b, g, s, w, t, mid)) {
                result = Math.min(mid, result);
                end = mid - 1;
                mid = (start + end) / 2;
                continue;
            }

            start = mid + 1;
            mid = (start + end) / 2;
        }

        return result;
    }

    //시간이 주어졌을 때 가능한지
    boolean isPossible(int a, int b, int[] g, int[] s, int[] w, int[] t, long stdTime) {
        int totalGold = 0;
        int totalSilver = 0;
        int totalMineral = 0;

        for (int i = 0; i < g.length; i++) {
            long time = t[i];
            long oneWayCount = stdTime / time;
            if (oneWayCount <= 0) continue;

            oneWayCount--;
            long twoWayCount = oneWayCount / 2;
            long maxTake = w[i] + (w[i] * twoWayCount);
            totalGold += Math.min(g[i], maxTake);
            totalSilver += Math.min(s[i], maxTake);
            totalMineral += Math.min(g[i] + s[i], maxTake);
        }

        return (totalGold >= a && totalSilver >= b && totalMineral >= a + b);
    }
}