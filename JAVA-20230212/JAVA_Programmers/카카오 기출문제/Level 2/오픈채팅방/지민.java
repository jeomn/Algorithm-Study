//20220115
//2019 KAKAO BLIND RECRUITMENT


import java.util.*;


class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        
        HashMap<String, String> idMap = new HashMap<>();
        for(String r : record){
            String[] command = r.split(" ");
            if(command[0].equals("Leave")) continue;
            idMap.put(command[1], command[2]);
        }
        
        ArrayList<String> resultList = new ArrayList<>();
        for(String r : record){
            String[] command = r.split(" ");
            if(command[0].equals("Enter")) resultList.add(idMap.get(command[1])+"님이 들어왔습니다.");
            else if(command[0].equals("Leave")) resultList.add(idMap.get(command[1])+"님이 나갔습니다.");
        }
        
        answer = new String[resultList.size()];
        for(int i=0; i<resultList.size(); i++){
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
}
