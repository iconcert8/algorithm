import java.util.*;
class Solution {
    public int solution(int n, String[] data) {
        String[] group = {"A", "C", "F", "J", "M", "N", "R", "T"};
        List<PhotoRule> photoRules = new ArrayList<>();
        Arrays.stream(data).forEach(r -> photoRules.add(PhotoRule.of(r)));

        Permutation<String> permutation = new Permutation<>(group);
        List<String[]> totalCase = permutation.permutation(group.length);

        int answer = 0;
        for (String[] oneCase : totalCase) {
            boolean isValidate = true;
            for (PhotoRule photoRule : photoRules) {
                if (!photoRule.validate(oneCase)) {
                    isValidate = false;
                    break;
                }
            }

            if (isValidate) {
                answer++;
            }
        }

        return answer;
    }
    
    static class PhotoRule {
        public String fromPerson;
        public String toPerson;
        public String operator;
        public int value;

        public PhotoRule(String fromPerson, String toPerson, String operator, int value) {
            this.fromPerson = fromPerson;
            this.toPerson = toPerson;
            this.operator = operator;
            this.value = value;
        }

        static PhotoRule of(String rule) {
            String[] ruleSplit = rule.split("");
            return new PhotoRule(ruleSplit[0], ruleSplit[2], ruleSplit[3], Integer.parseInt(ruleSplit[4]));
        }

        boolean validate(String[] people) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < people.length; i++) {
                map.put(people[i], i);
            }

            int fromIndex = map.get(fromPerson);
            int toIndex = map.get(toPerson);
            int innerPeopleCount = Math.abs(fromIndex - toIndex) - 1;
            switch (operator) {
                case "=":
                    return innerPeopleCount == value;
                case "<":
                    return innerPeopleCount < value;
                case ">":
                    return innerPeopleCount > value;
                default:
                    return false;
            }

        }

    }
    
    public static class Permutation<T> {
        private T[] arr;
        private int n;

        public Permutation(T[] arr) {
            this.arr = arr;
            this.n = arr.length;
        }

        public List<T[]> permutation(int r) {
            List<T[]> list = new ArrayList<>();
            permutation(list, 0, r);
            return list;
        }

        private void permutation(List<T[]> result, int depth, int r) {
            if (depth == r) {
                result.add(arr.clone());
                return;
            }

            for (int i = depth; i < n; i++) {
                swap(depth, i);
                permutation(result, depth + 1, r);
                swap(depth, i);
            }
        }

        private void swap(int depth, int i) {
            T temp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = temp;
        }

    }
}