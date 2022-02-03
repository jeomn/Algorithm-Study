//2022 KAKAO BLIND RECRUITMENT
//20220203


import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, HashSet<String>> sueMap = new HashMap<>();
        for(String r : report){
            String[] reportInfo = r.split(" ");
            
            if(sueMap.getOrDefault(reportInfo[1], null) == null)
                sueMap.put(reportInfo[1], new HashSet<String>());
            sueMap.get(reportInfo[1]).add(reportInfo[0]);
        }
        
        HashMap<String, Integer> idMap = new HashMap<>();
        for(int idx=0; idx<id_list.length; idx++){
            idMap.put(id_list[idx], idx);
        }
        
        for(int idx=0; idx<id_list.length; idx++){
            String id = id_list[idx];
            if(sueMap.getOrDefault(id, new HashSet<String>()).size()<k) continue;
            for(String mail : sueMap.get(id)){
                answer[idMap.get(mail)]++;
            }
        }
        
        return answer;
    }
}