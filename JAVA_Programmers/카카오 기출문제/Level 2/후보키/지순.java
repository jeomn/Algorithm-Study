import java.util.*;
class Solution {
    int col;
    boolean[] visited;
    ArrayList<String> list = new ArrayList<String>();
    public int solution(String[][] relation) {
        int answer = 0;
        col = relation[0].length;
        visited = new boolean[col];
        for(int i=1;i<=col;i++) {
            comb(0, 0, i, relation);
        }
        return list.size();
    }
    
    public void comb(int idx, int cnt, int size, String[][] relation) {
        if(cnt == size) {
            String str = "";
            for(int i=0;i<visited.length;i++) {
                if(visited[i]) {
                    str += i+"";
                }
            }
            
            if(str.length() == 0) return;
            
            for(int i=0;i<list.size();i++) { // 최소성
                int count = 0;
                for(int j=0;j<str.length();j++) {
                    if(list.get(i).contains(String.valueOf(str.charAt(j)))) {
                        count++;
                    }
                }
                if(count == list.get(i).length()) return;
            }
            
            
            HashSet<String> set = new HashSet<>();
            for(int i=0;i<relation.length;i++) {
                String sample = "";
                for(int j=0;j<str.length();j++) {
                    sample += relation[i][str.charAt(j)-'0'];
                }
                
                if(!set.add(sample)) {
                    return;
                }
            }
            
            list.add(str);
            return;
        }
        
        for(int i=idx;i<col;i++) {
            if(visited[i]) continue;
            visited[i] = true;
            comb(i, cnt+1, size, relation);
            visited[i] = false;
        }
    }
}
