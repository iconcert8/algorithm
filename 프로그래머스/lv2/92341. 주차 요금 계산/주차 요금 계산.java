import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        Map<Integer, ParkingCar> map = new TreeMap<>();

        for (String record : records) {
            String[] splitRecord = record.split(" ");
            int time = timeToInteger(splitRecord[0]);
            int number = Integer.parseInt(splitRecord[1]);
            boolean isIn = splitRecord[2].equals("IN") ? true : false;
            map.merge(
                    number,
                    new ParkingCar(number) {{
                        addRecord(time, isIn);
                    }},
                    (o1, o2) -> {
                        o1.addRecord(time, isIn);
                        return o1;
                    }
            );
        }
        List<Integer> result = new ArrayList<>();
        for (Integer number : map.keySet()) {
            ParkingCar parkingCar = map.get(number);
            int totalTime = parkingCar.getTotalTime();
            if (totalTime - basicTime <= 0) {
                result.add(basicFee);
                continue;
            }
            int ceilUnit = (totalTime - basicTime) % unitTime != 0 ? 1 : 0;
            int extraFee = unitFee * (((totalTime - basicTime) / unitTime) + ceilUnit);
            int fee = basicFee + extraFee;
            result.add(fee);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
    
    public int timeToInteger(String time) {
        String[] splitTime = time.split(":");
        int h = Integer.parseInt(splitTime[0]);
        int m = Integer.parseInt(splitTime[1]);
        return (h * 60) + m;
    }
    
    class ParkingCar {
        int number;
        List<Map.Entry<Integer, Boolean>> records;

        public ParkingCar(int number) {
            this.number = number;
            records = new ArrayList<>();
        }

        public void addRecord(int time, boolean isIn) {
            records.add(new AbstractMap.SimpleEntry<>(time, isIn));
        }

        public int getTotalTime() {
            int totalTime = 0;
            Stack<Integer> stack = new Stack<>();
            for (Map.Entry<Integer, Boolean> record : records) {
                int time = record.getKey();
                boolean isIn = record.getValue();
                if (isIn) {
                    stack.add(time);
                } else {
                    int inTime = stack.pop();
                    totalTime += time - inTime;
                }
            }

            if (!stack.empty()) {
                totalTime += 1439 - stack.pop();
            }

            return totalTime;
        }


    }
}