//2017 카카오코드 본선
//20211228


import java.util.*;


class Solution {
    
    int count;
    int[] friends;
    boolean[] isSelected;
    String[] strData;
    HashMap<Character, Integer> friendMap;
    
    public int solution(int n, String[] data) {
        int answer = 0;
        
        count=0;
        friends = new int[8];
        isSelected = new boolean[8];
        strData = data;
        friendMap = new HashMap<Character, Integer>(){
            {
                put('A', 0);
                put('C', 1);
                put('F', 2);
                put('J', 3);
                put('M', 4);
                put('N', 5);
                put('R', 6);
                put('T', 7);
                
            }
        };
            
        makeCombinations(0);        
        
        answer = count;
        return answer;
    }
    
    public void makeCombinations(int cnt){
        if(cnt==8){
            if(checkFriends()) count++;
            return;
        }
        
        for(int i=0; i<8; i++){
            if(isSelected[i]) continue;
            friends[cnt] = i;
            isSelected[i] = true;
            makeCombinations(cnt+1);
            isSelected[i] = false;
        }
    }
    
    public boolean checkFriends(){
        for(String d : strData){
            int fOne = friends[friendMap.get(d.charAt(0))];
            int fTwo = friends[friendMap.get(d.charAt(2))];
            
            char oper = d.charAt(3);
            int dist = d.charAt(4)-'0'+1;
            
            if(oper == '='){
                if(Math.abs(fOne-fTwo) != dist) return false;
            }else if(oper == '<'){
                if(Math.abs(fOne-fTwo) >= dist) return false;
            }else{
                if(Math.abs(fOne-fTwo) <= dist) return false;
            }
        }
        return true;
    }
}
