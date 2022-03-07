//20220307
//2020 카카오 인턴십


import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
           
        HashSet<String> gemsCnt = new HashSet<>();
        for(String gem : gems){
            gemsCnt.add(gem);
        }
        int totalGemsSortCnt = gemsCnt.size();
        
        int length = gems.length, minLength = length;
        int[] answer = new int[]{1, length};
        
        int preIdx = 0, postIdx = -1;
        HashMap<String, Integer> gemsMap = new HashMap<>();
        while(preIdx < length){
            if(gemsMap.size() != totalGemsSortCnt){
                postIdx++;
                if(postIdx >= length) break;
                gemsMap.put(gems[postIdx], gemsMap.getOrDefault(gems[postIdx], 0)+1);
                continue;
            }
            
            if(postIdx-preIdx < minLength){
                answer[0] = preIdx+1;
                answer[1] = postIdx+1;
                minLength = postIdx-preIdx;
            }
            
            gemsMap.put(gems[preIdx], gemsMap.getOrDefault(gems[preIdx], 0)-1);
            if(gemsMap.get(gems[preIdx])<=0) gemsMap.remove(gems[preIdx]);
            preIdx++;
        }
        
        return answer;
    }
}