//20220314
//2019 카카오 개발자 겨울 인턴십


import java.util.*;

class Solution {
    
    int K;
    int[] stone;
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        stone = stones;
        K = k;
        
        int min = 1, max = 200_0000_000, mid = 0;
        while(min <= max){
            mid = (min+max)/2;
            if(!isCross(mid)) max = mid-1;
            else{
                min = mid+1;
                answer = Math.max(answer, mid);
            }
        }
        
        return answer;
    }
    
    public boolean isCross(int mid){
        int cnt = 0;
        for(int s : stone){
            if(s - mid < 0) cnt++;
            else cnt=0;
            
            if(cnt == K) return false;
        }
        return true;
    }
}