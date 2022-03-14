class Solution {
    int[] stones;
    int K;
    public int solution(int[] stones, int k) {
        this.stones = stones;
        int answer = 0;
        int low = 1;
        int high = 200000000;
        K = k;
        
        while(low<=high) {
            int mid = (low+high)/2;
            
            if(check(mid)) {
                low = mid+1;
                answer = Math.max(answer, mid);
            } else {
                high = mid -1;
            }
            
        }
        
        return answer;
    }
    
    public boolean check(int mid) {
        int cnt = 0;
        for(int stone: stones) {
            if(stone - mid < 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            
            if(cnt == K)
                return false;
        }
        return true;
    }
}
