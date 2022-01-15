import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        ArrayList<String[]> result = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        
        for(String str: record) {
            String[] records = str.split(" ");
            
            if(records[0].equals("Enter")) {
                map.put(records[1], records[2]);
                result.add(records);
            } else if(records[0].equals("Change")) {
                map.replace(records[1], records[2]);
            } else if(records[0].equals("Leave")) {
                result.add(records);
            }
        }
        
        String[] answer = new String[result.size()];
        int idx = 0;
        for(int i=0;i<result.size();i++) {
            String[] records = result.get(i);
            
            if(records[0].equals("Enter")) {
                answer[idx++] = map.get(records[1]) +"님이 들어왔습니다.";
            } else {
                answer[idx++] = map.get(records[1]) +"님이 나갔습니다.";
            }
        }
        return answer;
    }
}
