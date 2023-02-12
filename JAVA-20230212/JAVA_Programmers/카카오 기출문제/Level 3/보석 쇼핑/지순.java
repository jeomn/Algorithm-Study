import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        HashSet<String> set = new HashSet<>();
        for(String gem:gems) {
            set.add(gem);
        }
        
        int start = 0;
        int startPoint = 0;
        int end = Integer.MAX_VALUE;
        
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        for(int i=0;i<gems.length;i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0)+1);
            queue.add(gems[i]);
            
            while(true) {
                String target = queue.peek();
                
                if(map.get(target)>1) {
                    map.put(target, map.get(target)-1);
                    queue.poll();
                    startPoint++;
                } else {
                    break;
                }
            }
            
            if(map.size() == set.size() && end > queue.size()) {
                end = queue.size();
                start = startPoint;
            }
        }
        
        return new int[] {start+1, start+end};
    }
}
