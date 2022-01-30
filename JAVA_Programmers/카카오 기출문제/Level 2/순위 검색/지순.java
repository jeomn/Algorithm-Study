import java.util.*;

class Solution {
    HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    boolean[] visited;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for(int i=0;i<info.length;i++) {
            visited = new boolean[5];
            String[] st = info[i].split(" ");
            comb(0, 0, Integer.parseInt(st[4]), "", st);
        }
        
        for(String s : map.keySet()){
            ArrayList<Integer> l = map.get(s);
            Collections.sort(l);
            map.put(s, l);
        }
        
        for(int i=0;i<query.length;i++) {
            String[] st = query[i].split(" ");
            int num = Integer.parseInt(st[st.length - 1]);
            st[st.length - 1] = "";
            
            String result = st[0] + st[2] + st[4] + st[6];
            
            if(map.containsKey(result)) {
                ArrayList<Integer> list = map.get(result);
                int start = 0;
                int end = list.size();
                while(start < end) {
                    int mid = (start + end) / 2;
                    if(list.get(mid) >= num)
                        end = mid;
                    else
                        start = mid + 1;
                }
                answer[i] = list.size() - start;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    public void comb(int cnt, int idx, int num, String st, String[] str) {
        if(cnt == 4) {
            ArrayList<Integer> list = new ArrayList<>(); 
            if(map.containsKey(st)) {
                list = map.get(st);
            }
            list.add(num);
            map.put(st, list);
            return;
        }
        
        for(int i=idx;i<4;i++) {
            if(visited[i]) continue;
            visited[i] = true;
            comb(cnt+1, i, num, st + str[i], str);
            comb(cnt+1, i, num, st + "-", str);
            visited[i] = false;
        }
        
    }
}
