import java.util.*;

class Solution {
    ArrayList<ArrayList<String>> bannedList = new ArrayList<ArrayList<String>>();
    HashSet<HashSet<String>> result = new HashSet<HashSet<String>>();
    HashSet<String> tmp = new HashSet<String>();
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        for(String str : banned_id) {
            bannedList.add(getUserId(user_id, str));
        }
        
        dfs(0);
        return result.size();
    }
    
    public void dfs(int idx) {
        if(idx == bannedList.size()) {
            result.add(new HashSet<>(tmp));
            return;
        }
        
        for(String user : bannedList.get(idx)) {
            if(tmp.contains(user)) continue;
            tmp.add(user);
            dfs(idx+1);
            tmp.remove(user);
        }
    }
    
    public ArrayList<String> getUserId(String[] user_id, String banned_id) {
        ArrayList<String> list = new ArrayList<String>();
        for(String user : user_id) {
            boolean check = false;
            if(user.length() != banned_id.length()) 
                continue;
            
            for(int i=0;i<user.length();i++) {
                if(banned_id.charAt(i) == '*') continue;
                
                if(user.charAt(i) != banned_id.charAt(i))
                    check = true;
            }
            
            if(!check) {
                list.add(user);
            }
        }
        
        return list;
    }
}
