import java.util.*;
class Solution {
    public int[] solution(int n, int[] info) {
        List<int[]> caseList = new ArrayList<>();
        recursiveArcheryScore(
                info,
                new int[11],
                new boolean[11],
                n,
                0,
                caseList);

        int maxDifScore = Integer.MIN_VALUE;
        List<int[]> maxCaseList = new ArrayList<>();
        for (int[] array : caseList) {
            int difScore = 0;
            for (int i = 0; i < array.length; i++) {
                if (info[i] >= array[i]  && info[i] != 0) {
                    difScore = difScore - (array.length - 1 - i);
                }

                if (info[i] < array[i]) {
                    difScore = difScore + (array.length - 1 - i);
                }
            }

            if (maxDifScore < difScore) {
                maxDifScore = difScore;
                maxCaseList.clear();
                maxCaseList.add(array);
                continue;
            }

            if (maxDifScore == difScore) {
                maxCaseList.add(array);
            }
        }

        if (maxDifScore <= 0) {
            return new int[]{-1};
        }


        int[] resultArray = null;
        int maxBinarySum = 0;
        for (int[] maxCase : maxCaseList) {
            int binarySum = 0;
            for (int i = 0; i < maxCase.length; i++) {
                if (maxCase[i] > 0) {
                    binarySum += Math.pow(2, i) * maxCase[i];
                }
            }

            if (maxBinarySum < binarySum) {
                maxBinarySum = binarySum;
                resultArray = maxCase;
            }
        }


        return resultArray;
    }
    
    void recursiveArcheryScore(int[] info, int[] output, boolean[] visited, int remain, int start, List<int[]> caseList) {
        if (remain == 0) {
            caseList.add(output.clone());
            return;
        }

        for (int i = start; i < info.length; i++) {
            if (visited[i] == false) {
                if (remain > 0) {
                    for (int remainValue = 1; remainValue <= remain; remainValue++) {
                        visited[i] = true;
                        output[i] = remainValue;
                        recursiveArcheryScore(info, output, visited, remain - remainValue, i + 1, caseList);
                        output[i] = 0;
                        visited[i] = false;
                    }
                }
            }
        }
    }
}