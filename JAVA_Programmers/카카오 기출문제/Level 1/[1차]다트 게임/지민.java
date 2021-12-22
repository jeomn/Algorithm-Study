#2018 KAKAO BLIND RECRUITMENT
#20211222


import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        char[] dartResultCharArray = dartResult.toCharArray();
        int[] dartScore = new int[dartResult.length()];
        int score = 0, idx = 0;
        for(char c : dartResultCharArray){
            if(Character.isDigit(c)){
                if(score == 1 && c=='0') score = 10;                
                else{
                    if(score!=0) dartScore[idx++] = score;
                    score = c-'0';
                }
            }else if(Character.isAlphabetic(c)){
                if(c=='S') score = (int)Math.pow(score, 1);
                else if(c=='D') score = (int)Math.pow(score, 2);
                else if(c=='T') score = (int)Math.pow(score, 3);
                continue;
            }else {
                if(c=='*'){
                    if(idx!=0) dartScore[idx-1] *= 2;
                    score *= 2;
                }else if(c=='#'){
                    score *= (-1);
                }
            }
        }
        dartScore[idx++] = score;

        answer = Arrays.stream(dartScore).sum();
        return answer;
    }
}
