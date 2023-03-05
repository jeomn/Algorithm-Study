import java.util.*;

class StageFailure implements Comparable<StageFailure>{
    int stageNum = 0;
    double failure = 0.0;
    
    public StageFailure(int stageNum, double failure){
        this.stageNum = stageNum;
        this.failure = failure;
    }
    
    public int compareTo(StageFailure obj){
        int x = Double.compare(obj.failure, this.failure);
        if(x==0) return this.stageNum - obj.stageNum;
        return x;
    }
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        
        int[] curStage = new int[N+2];
        for(int i=0; i<stages.length; i++){
            curStage[stages[i]]+=1;
        }
        
        PriorityQueue<StageFailure> failOrder = new PriorityQueue<>();
        int players = curStage[N+1];
        for(int i=N; i>0; i--){
            players+=curStage[i];
            if(players == 0){
                failOrder.add(new StageFailure(i, 0));
                continue;
            }
            failOrder.add(new StageFailure(i, (double)curStage[i]/players));
        }

        answer = new int[N];
        for(int i=0; i<N; i++){
            answer[i] = failOrder.poll().stageNum;    
        }
        return answer;
    }
}
