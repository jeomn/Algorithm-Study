//20220120
//2020 KAKAO BLIND RECRUITMENT


import java.util.*;


class Solution {
    public String solution(String p) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        if(isRightString(p)) return p;
        if(p.length()==0) return answer;
        
        String[] splitString = makeBalancedString(p);
        if(isRightString(splitString[0])) return splitString[0]+solution(splitString[1]);
        else{
            answer += '(';
            answer += solution(splitString[1]);
            answer += ')';
            String temp = splitString[0].substring(1, splitString[0].length()-1);
            answer += temp.replace("(", "0").replace(")", "(").replace("0", ")");
            return answer;
        }
    }
    
    public Boolean isBalancedString(String origin){
        int originLength = origin.length();
        int countOne = origin.replace("(", "").length();
        if(countOne == (origin.length()-countOne)) return true;
        else return false;
    }
    
    public Boolean isRightString(String origin){
        Stack<Character> temp = new Stack<>();
        for(char o : origin.toCharArray()){
            if(o=='(') temp.push(o);
            else {
                if(temp.size() == 0) return false;
                temp.pop();
            }
        }
        if(temp.size() != 0) return false;
        return true;
    }
    
    public String[] makeBalancedString(String origin){
        StringBuilder sb2 = new StringBuilder();
        for(int i=0; i<origin.length(); i++){
            char o = origin.charAt(i);
            sb2.append(String.valueOf(o));
            if(isBalancedString(sb2.toString())) return new String[]{sb2.toString(), origin.substring(i+1, origin.length())};
        }
        return new String[]{"", origin};
    }
}
