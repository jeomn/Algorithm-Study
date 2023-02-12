//20220310
//2019 카카오 개발자 겨울 인턴십


import java.util.*;

class Solution {
    
    int result = 0;
    String[] comb, user, banned;
    HashSet<HashSet<String>> dup = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        user = user_id;
        banned = banned_id;
        comb = new String[banned_id.length];
        
        makeComb(0);
        answer = result;
        return answer;
    }
    
    public void makeComb(int cnt){
        if(cnt == banned.length){
            
            HashSet<String> idCheck = new HashSet<>();
            for(int i=0; i<cnt; i++){
                String id = comb[i];
                if(id.length() != banned[i].length()) return;
                for(int j=0; j<id.length(); j++){
                    char c = banned[i].charAt(j);
                    if(c == '*') continue;
                    if(c != id.charAt(j)) return;
                }
                idCheck.add(id);
            }
            
            if(idCheck.size() != banned.length) return;
            if(dup.contains(idCheck)) return;
            
            result++;
            dup.add(idCheck);
            
            return;
        }
        
        for(int i=0; i<user.length; i++){
            comb[cnt] = user[i];
            makeComb(cnt+1);
        }
    }
}