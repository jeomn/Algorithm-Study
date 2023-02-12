import java.util.*;
class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int idx = 1;
        for(char i = 'A';i<='Z';i++) {
            map.put(i+"", idx++);
        }
        
        String str = "";
        int w = 0;
        int size = 1;
        
        for(int i=0;i<msg.length();i++) {
            str = msg.substring(w, w+size);
            
            if(map.containsKey(str)) {
                size++;
            }
            else {
                w = w+size-1;
                size = 1;
                map.put(str, idx++);
                ans.add(map.get(str.substring(0, str.length()-1)));
                i--;
                str = "";
            }
        }
        if(str.length() > 0)
            ans.add(map.get(str));

        int[] answer = new int[ans.size()];
        for(int i=0;i<ans.size();i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
