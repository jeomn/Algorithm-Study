import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, Integer> result = new HashMap<>();
        
        for(String record: records) {
            String[] s = record.split(" ");
            if(s[2].equals("IN")) {
                map.put(s[1], s[0]);
            } else {
                int time = setTime(map.get(s[1]), s[0]);
                map.remove(s[1]);
                result.put(s[1], result.getOrDefault(s[1], 0) + time);
            }
        }
        
        for(String key : map.keySet()) {
            int time = setTime(map.get(key), "23:59");
            result.put(key, result.getOrDefault(key, 0) + time);
        }
        
        String[] mapKey = result.keySet().toArray(new String[result.size()]);
        Arrays.sort(mapKey);
        
        int[] answer = new int[mapKey.length];
        int i=0;
        for(String key : mapKey) {
            int num = result.get(key);
            if(num <= fees[0])
                answer[i] = fees[1];
            else {
                answer[i] += fees[1];
                answer[i] += Math.ceil((double)(num - fees[0])/fees[2]) * fees[3];
            }
            
            i++;
        }
        
        return answer;
    }
    
    private int setTime(String str1, String str2) {
        String[] s1 = str1.split(":");
        String[] s2 = str2.split(":");
        
        int time1 = Integer.parseInt(s1[0]) * 60 + Integer.parseInt(s1[1]);
        int time2 = Integer.parseInt(s2[0]) * 60 + Integer.parseInt(s2[1]);
        
        return time2 - time1;
    }
}
