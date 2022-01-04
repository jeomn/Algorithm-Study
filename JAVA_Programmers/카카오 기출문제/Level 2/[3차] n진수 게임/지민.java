// 2018 KAKAO BLIND RECRUITMENT
// 20220104


import java.util.*;


class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int turn = 0, num = 0;
        String baseNumber = "";
        while(answer.length() != t){
            baseNumber = makeBaseNumber(num, n);
            for(int i=0; i<baseNumber.length(); i++){
                if(answer.length() == t) break;
                if(turn%m == (p-1)) answer += baseNumber.charAt(i);
                turn++;
            }
            num++;
        }
        return answer;
    }
    
    public String makeBaseNumber(int n, int nBase){
        String base = "0123456789ABCDEF";
        
        int number = n;
        StringBuilder sb = new StringBuilder();
        if(number == 0){
            sb.append("0");
            return sb.toString();
        }
        while(number > 0){
            int remainder = number%nBase;
            number /= nBase;
            sb.insert(0, base.charAt(remainder));
            //sb.append(base.charAt(remainder));
        }
        return sb.toString();
    }
}
