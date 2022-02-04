//2022 KAKAO BLIND RECRUITMENT
//20220204


import java.util.*;


class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = null;
        
        HashMap<String, Integer> parkingTimeMap = new HashMap<>();
        HashMap<String, String> inMap = new HashMap<>();
        for(String record: records){
            String[] info = record.split(" ");
            if(inMap.containsKey(info[1])){
                int time = calcTime(inMap.get(info[1]), info[0]);
                parkingTimeMap.put(info[1], parkingTimeMap.getOrDefault(info[1], 0)+time);
                inMap.remove(info[1]);
                continue;
            }
            inMap.put(info[1], info[0]);
        }
        
        if(!inMap.isEmpty()){
            for(String key : inMap.keySet()){
                int time = calcTime(inMap.get(key), "23:59");
                parkingTimeMap.put(key, parkingTimeMap.getOrDefault(key, 0)+time);
            }
        }
        
        answer = new int[parkingTimeMap.size()];
        ArrayList<String> keyList = new ArrayList<>(parkingTimeMap.keySet());
        Collections.sort(keyList);
        int idx = 0;
        for(String key: keyList){
            int time = parkingTimeMap.get(key);
            int money = fees[1];
            if(time>fees[0]) money += Math.ceil((time-fees[0])/Double.valueOf(fees[2]))*fees[3];
            answer[idx++] = money;
        }
        
        return answer;
    }
    
    public int calcTime(String in, String out){
        String[] inTime = in.split(":");
        String[] outTime = out.split(":");
        
        Integer hours = Integer.parseInt(outTime[0])-Integer.parseInt(inTime[0]);
        Integer minutes = Integer.parseInt(outTime[1])-Integer.parseInt(inTime[1]);
        
        return (hours*60)+minutes;
    }
}