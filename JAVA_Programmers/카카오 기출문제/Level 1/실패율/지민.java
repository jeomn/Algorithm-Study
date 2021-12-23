import java.util.*;

class Fail implements Comparable<Fail>{
    int stage;
    double failRate;
    Fail(int stage, double failRate){
        this.stage = stage;
        this.failRate = failRate;
    }
    
    public int compareTo(Fail o){
        int x = Double.compare(o.failRate, this.failRate);
        if(x==0){
            return this.stage - o.stage;
        }
        return x;
    }
}

class Solution {
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int[] players = new int[N+1];
        int[] clearPlayers = new int[N+1];
        for(int s : stages){
            players[s-1]++;
            for(int i=0; i<s; i++){
              clearPlayers[i]++;
            }
        }
        
        PriorityQueue<Fail> order = new PriorityQueue<Fail>();
        for(int i=0; i<N; i++){
            if(clearPlayers[i] == 0){
                order.add(new Fail(i+1, 0));
                continue;
            }
            order.add(new Fail(i+1, players[i]/(double)clearPlayers[i]));
        }
        
        for(int i=0; i<N; i++){
            answer[i] = order.poll().stage;
        }
        return answer;
    }
}
