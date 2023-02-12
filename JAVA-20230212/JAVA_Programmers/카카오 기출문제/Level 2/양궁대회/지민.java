//2022 KAKAO BLIND RECRUITMENT
//20220209


class Solution {
    
    int maxScore;
    int[] apeachInfo, myWay;
    
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        
        apeachInfo = info;
        maxScore = 0;
        myWay = new int[11];    //이거 안해주면 23번 런타임 에러
        makeWay(0, n, 0, 0, new int[11]);
        
        if(maxScore == 0) answer = new int[]{-1};
        else answer = myWay;
        
        return answer;
    }
    
    public void makeWay(int cnt, int n, int ryan, int apeach, int[] way){
        if(cnt == 10){
            int scoreDiffer = ryan-apeach;
            if(scoreDiffer < 0) return;
            way[cnt] = n;
            if(maxScore < scoreDiffer){
                maxScore = scoreDiffer;
                myWay = way.clone();
            }else if(maxScore == scoreDiffer){
                if(checkWay(way)) myWay = way.clone();
            }
            
            return;
        }
        
        if(apeachInfo[cnt] < n){
            way[cnt] = apeachInfo[cnt]+1;
            makeWay(cnt+1, n-(apeachInfo[cnt]+1), ryan+(10-cnt), apeach, way);
        }
        way[cnt] = 0;
        int s = (apeachInfo[cnt]==0) ? 0 : 10-cnt;
        makeWay(cnt+1, n, ryan, apeach+s, way);
        
        return;
    }
    
    public Boolean checkWay(int[] way){
        for(int i=10; i>=0; i--){
            if(way[i] > myWay[i]) return true;
            else if(way[i] < myWay[i]) return false;
        }
        return false;
    }
}