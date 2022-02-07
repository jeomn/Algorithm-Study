// 20220207
// 2022 KAKAO BLIND RECRUITMENT


import java.util.*;


class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String number = Integer.toString(n, k);
        
        int startIdx = number.indexOf("0");
        if(startIdx == -1){
            if(isPrime(Long.valueOf(number))) answer++;
            return answer;
        }
        String start = number.substring(0, startIdx);
        if(isPrime(Long.valueOf(start))) answer++;
        
        number = number.substring(startIdx);
        
        int endIdx = number.lastIndexOf("0");
        if(endIdx == -1){
            if(isPrime(Long.valueOf(number))) answer++;
            return answer;
        }
        String end = number.substring(endIdx);
        if(isPrime(Long.valueOf(end))) answer++;
            
        number = number.substring(0, endIdx+1);
        
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<number.length(); i++){
            if(number.charAt(i) == '0'){
                if(temp.length() == 0) continue;
                if(isPrime(Long.valueOf(temp.toString()))) answer++;
                temp.setLength(0);
                continue;
            }
            temp.append(number.charAt(i));
        }
        
        return answer;
    }
    
    public boolean isPrime(Long n){
        if(n <= 1) return false;
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0) return false;
        }
        return true;
    }
}
