//20220113
//2019 KAKAO BLIND RECRUITMENT


import java.util.*;

class Comb implements Comparable<Comb>{
    int len;
    String order;
    Comb(int len, String order){
        this.len = len;
        this.order = order;
    }
    
    public int compareTo(Comb o){
        return this.len-o.len;
    }
}

class Solution {
    
    int row, col, answer;
    boolean[] isSelected;
    String[][] relationStr;
    ArrayList<String> candidate = new ArrayList<>();
    PriorityQueue<Comb> combination = new PriorityQueue<>();
    
    public int solution(String[][] relation) {
        
        relationStr = relation;
        row = relation.length;
        col = relation[0].length;
        isSelected = new boolean[col];
        makeComb(0);
        while(!combination.isEmpty()){
            Comb comb = combination.poll();
            if(checkComb(comb.order)) answer++;
        }
        
        return answer;
    }
    
    public void makeComb(int cnt){
        if(cnt == col){
            String key = "";
            for(int i=0; i<col; i++){
                if(isSelected[i]) key += (i+1);
            }
            if(key.equals("")) return;
            combination.add(new Comb(key.length(), key));
            return;
        }
        
        isSelected[cnt] = true;
        makeComb(cnt+1);
        isSelected[cnt] = false;
        makeComb(cnt+1);
    }
    
    public boolean checkComb(String order){
        HashSet<String> combSet = new HashSet<>();
        for(int i=0; i<row; i++){
            String key = "";
            for(int j=0; j<order.length(); j++){
                int idx = order.charAt(j)-'0';
                key += relationStr[i][idx-1];
            }
            combSet.add(key);
        }
        if(combSet.size() != row) return false;
        
        for(String candi : candidate){
            int cnt = 0;
            for(int i=0; i<order.length(); i++){
                String n = String.valueOf(order.charAt(i));
                if(candi.contains(n)) cnt++;
            }
            if(cnt==candi.length()) return false;
        }
        candidate.add(order);
        
        return true;
    }
}
