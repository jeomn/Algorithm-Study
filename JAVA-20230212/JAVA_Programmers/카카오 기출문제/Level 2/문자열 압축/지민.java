//20220124
//2021 KAKAO BLIND RECRUITMENT

import java.util.*;


class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        StringBuilder sb = new StringBuilder();
        for(int l=1; l<(s.length()/2)+1; l++){
            int cnt=0;
            String subStr = s.substring(0, l);
            for(int i=0; i<s.length(); i+=l){
                if(i+l > s.length()){
                    subStr = s.substring(i-l, s.length());
                    break;
                }
                
                String temp = s.substring(i, i+l);
                if(subStr.equals(temp)){
                    cnt++;
                }else{
                    if(cnt==1){
                        sb.append(subStr);
                    }else{
                        sb.append(cnt+subStr);
                    }
                    subStr = temp;
                    cnt=1;
                }
            }
            if(cnt==1) sb.append(subStr);
            else sb.append(subStr+cnt);
            answer = Math.min(answer, sb.length());
            sb.setLength(0);
        }
        
        return answer;
    }
}