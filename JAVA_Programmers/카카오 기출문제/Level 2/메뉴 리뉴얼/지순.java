import java.util.*;

class Solution {
    HashMap<String, Integer> hashmap;
    int maxNum;
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        ArrayList<String> result = new ArrayList<String>();
        for(int c: course) {
            hashmap = new HashMap<String, Integer>();
            maxNum = 0;
            for(String s:orders) {
                ArrayList<String> temp = new ArrayList<String>();
                char[] ch = s.toCharArray();
                Arrays.sort(ch);
                comb(0, 0, c, ch, temp);
            }
            
            if(maxNum <= 1) continue;
            for(String s: hashmap.keySet()) {
                if(hashmap.get(s) == maxNum) {
                    result.add(s);
                }
            }
        }
        Collections.sort(result);
        answer = new String[result.size()];
        for(int i=0;i<result.size();i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    public void comb(int cnt, int idx, int N, char[] ch, ArrayList<String> temp) {
        if(cnt == N) {
            ArrayList<String> tmp = new ArrayList<>(temp);
            Collections.sort(tmp);
            String s = String.join("", tmp);
            hashmap.put(s, hashmap.containsKey(s)? hashmap.get(s) + 1:1);
            maxNum = Math.max(maxNum, hashmap.get(s));
            return;
        }
        for(int i=idx;i<ch.length;i++) {
            temp.add(ch[i]+"");
            comb(cnt+1, i+1, N, ch, temp);
            temp.remove(ch[i]+"");
        }
    }
}
