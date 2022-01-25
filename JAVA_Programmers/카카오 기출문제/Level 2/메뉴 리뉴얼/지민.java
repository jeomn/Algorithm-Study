//20220125
//2021 KAKAO BLIND RECRUITMENT


import java.util.*;


class Solution {
    
    int combNum, combMaxNum;
    String order;
    Set<String> combSet;
    HashMap<String, Integer> combCntMap;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        ArrayList<String> answerList = new ArrayList<>();
        for(int c : course){
            combNum = c;
            combMaxNum = 1;
            combSet = new HashSet<String>();
            combCntMap = new HashMap<String, Integer>();
            for(String o : orders){
                order = o;
                makeComb(0, 0, new ArrayList<String>());
            }

            if(combMaxNum < 2) continue;
            for(String s : combSet){
                if(combCntMap.get(s) == combMaxNum) answerList.add(s);
            } 
        }
        
        Collections.sort(answerList);
        answer = new String[answerList.size()];
        for(int i=0; i<answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    
    public void makeComb(int cnt, int idx, ArrayList<String> pre){
        if(cnt==combNum){
            ArrayList<String> newOrder = (ArrayList<String>)pre.clone();
            
            Collections.sort(newOrder);
            String combStr = String.join("", newOrder);
            
            combSet.add(combStr);
            int combCnt = combCntMap.getOrDefault(combStr, 0);
            combMaxNum = Math.max(combMaxNum, combCnt+1);
            combCntMap.put(combStr, combCnt+1);
            return;
        }
        
        for(int i=idx; i<order.length(); i++){
            pre.add(order.charAt(i)+"");
            makeComb(cnt+1, i+1, pre);
            pre.remove(cnt);
        }
    }   
}