import java.util.*;
class Solution {
    static ArrayList<int[]> answer = new ArrayList<>();
    int N;
    int[] ryan;
    int[] apeach;
    int max = Integer.MIN_VALUE;
    public int[] solution(int n, int[] info) {
        ryan = new int[11];
        apeach = info;
        N = n;
        comb(0, 0);
        if(answer.size()==0) 
            return new int[]{-1};
        
        return answer.get(0);
    }
    
    private void comb(int cnt, int idx) {
        if(cnt == N) {
            int ryanCount = 0;
            int apeachCount = 0;
            for (int i = 0; i <= 10; i++) {
                if (apeach[i] == 0 && ryan[i] == 0) continue;
                if (apeach[i] < ryan[i])
                    ryanCount += 10-i;
                else 
                    apeachCount += 10-i;
            }
            if (ryanCount > apeachCount) {
                int diff = ryanCount-apeachCount;
                if (diff >= max) {
                    if(diff == max) {
                       if(!check(answer.get(0), ryan))
                           return;
                    }
                    max = diff;
                    answer.clear();
                    answer.add(ryan.clone());
                }
            }
            return;
        } 
            
        for(int i = idx;i<11;i++) {
            ryan[i]++;
            comb(cnt+1, i);
            ryan[i]--;
        }
        
    }
    
    private boolean check(int[] a, int[] b) {
        for(int i = 10; i >= 0; i--){
            if(a[i] < b[i]) return true;
            else if(a[i] > b[i]) return false;
        }
        return false;
    }
}
