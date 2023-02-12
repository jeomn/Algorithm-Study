import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, Integer> id = new HashMap<>();
        HashMap<String, HashSet<String>> reports = new HashMap<>();
        
        for(String ids: id_list) {
            id.put(ids, 0);
            reports.put(ids, new HashSet<>());
        }
        
        for(String str : report) {
            String[] s = str.split(" ");
            reports.get(s[1]).add(s[0]);
        }
                
        for(String key : reports.keySet()) {
            HashSet<String> set = reports.get(key);
            if(set.size() >= k) {
                for(String user : set) {
                    int cnt = id.get(user)+1;
                    id.put(user, cnt);
                }
            }
        }
        
        int i=0;
        for(String ids : id_list) {
            answer[i] = id.get(ids);
            i++;
        }
        
        return answer;
    }
}
