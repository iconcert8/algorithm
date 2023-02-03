import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportUserMap = new HashMap<>();
        for (String oneReport : report) {
            String[] message = oneReport.split(" ");
            String reportUser = message[0];
            String reportedUser = message[1];

            reportUserMap.merge(reportedUser, new HashSet<>() {{
                add(reportUser);
            }}, (v1, v2) -> {
                v1.addAll(v2);
                return v1;
            });
        }

        Set<String> upperThanKUser = new HashSet<>();
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            if (reportUserMap.containsKey(id)) {
                if (reportUserMap.get(id).size() >= k) {
                    upperThanKUser.add(id);
                }
            }
        }


        Map<String, Integer> messageCount = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            if (upperThanKUser.contains(id)) {
                reportUserMap.get(id).stream().forEach((s) -> messageCount.merge(s, 1, Integer::sum));
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            answer[i] = messageCount.containsKey(id) ? messageCount.get(id) : 0;
        }
        
        return answer;
    }
}