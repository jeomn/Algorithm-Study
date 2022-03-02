//20220302
import java.util.*;

class Solution {
    
    PriorityQueue<Integer> crewTable;
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        crewTable = new PriorityQueue<>();
        for(String time : timetable){
            String[] timeSplit = time.split(":");
            int crew = Integer.parseInt(timeSplit[0])*60+Integer.parseInt(timeSplit[1]);
            crewTable.add(crew);
        }
        
        int shuttleStart = 540;
        int shuttleLast = shuttleStart+((n-1)*t);
        int konTime = -1;
        for(int shuttle = shuttleStart; shuttle<=shuttleLast; shuttle+=t){
            int limit = m;
            int lastCrew = -1;
            while(limit>0 && !crewTable.isEmpty() && crewTable.peek() <= shuttle){
                int crew = crewTable.poll();
                --limit;
                if(lastCrew < crew) lastCrew = crew;
            }

            if(limit > 0) konTime = shuttle;
            else konTime = lastCrew-1;
        }
        
        if(konTime == -1) konTime = 0;
        
        String konHour = String.valueOf(konTime/60);
        if(konHour.length() == 1) konHour = "0"+konHour;
        String konMinutes = String.valueOf(konTime%60);
        if(konMinutes.length() == 1) konMinutes = "0"+konMinutes;
        answer = konHour + ":" + konMinutes;
        return answer;
    }
}