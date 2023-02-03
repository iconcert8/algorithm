class Solution {
    public int solution(int a, int b, int n) {
        int totalReceiveCount = 0;
        while (n >= a) {
            int receiveCount = (n / a) * b;
            totalReceiveCount += receiveCount;
            n = receiveCount + (n % a);
        }

        return totalReceiveCount;
    }
}