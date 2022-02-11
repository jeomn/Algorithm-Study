//2018 KAKAO BLIND RECRUITMENT
//20220211


import java.util.*;

class Solution {
    
    ArrayList<int[]> process;
    
    public int solution(String[] lines) {
        int answer = 0;
        
        process = new ArrayList<int[]>();
        for(String line : lines){
            String[] lineInfo = line.split(" ");
            int interval = (int)(Double.parseDouble(lineInfo[2].substring(0, lineInfo[2].length()-1))*1000.0);
            String[] timeInfo = lineInfo[1].split(":");
            int time = Integer.parseInt(timeInfo[0])*3600000;
            time += Integer.parseInt(timeInfo[1])*60000;
            time += (int)(Double.parseDouble(timeInfo[2])*1000.0);
            
            process.add(new int[]{time-interval+1, time});
        }
        
        for(int[] p : process){
            answer = Math.max(answer, Math.max(calcProcessCnt(p[0]), calcProcessCnt(p[1])));
        }
        
        return answer;
    }
    
    public int calcProcessCnt(int time){
        int cnt = 0;
        int start = time, end = time+1000;
        for(int[] p : process){
            if(start <= p[1] && end > p[0]) cnt++;
        }
        return cnt;
    }
}