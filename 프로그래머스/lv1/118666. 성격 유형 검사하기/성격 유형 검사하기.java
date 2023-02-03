import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> score = new HashMap<>() {{
            put("R", 0);
            put("T", 0);
            put("C", 0);
            put("F", 0);
            put("J", 0);
            put("M", 0);
            put("A", 0);
            put("N", 0);
        }};

        for (int i = 0; i < survey.length; i++) {
            String[] surveyType = survey[i].split("");
            String fromType = surveyType[0];
            String toType = surveyType[1];
            int choice = choices[i];

            switch (choice) {
                case 1:
                    score.merge(fromType, 3, Integer::sum);
                    break;
                case 2:
                    score.merge(fromType, 2, Integer::sum);
                    break;
                case 3:
                    score.merge(fromType, 1, Integer::sum);
                    break;
                case 4:
                    break;
                case 5:
                    score.merge(toType, 1, Integer::sum);
                    break;
                case 6:
                    score.merge(toType, 2, Integer::sum);
                    break;
                case 7:
                    score.merge(toType, 3, Integer::sum);
                    break;
                default:
                    break;
            }
        }

        StringBuilder answer = new StringBuilder();

        if (score.get("R") >= score.get("T")) {
            answer.append("R");
        } else {
            answer.append("T");
        }

        if (score.get("C") >= score.get("F")) {
            answer.append("C");
        } else {
            answer.append("F");
        }

         if (score.get("J") >= score.get("M")) {
            answer.append("J");
        } else {
            answer.append("M");
        }

        if (score.get("A") >= score.get("N")) {
            answer.append("A");
        } else {
            answer.append("N");
        }
        
        return answer.toString();
    }
}