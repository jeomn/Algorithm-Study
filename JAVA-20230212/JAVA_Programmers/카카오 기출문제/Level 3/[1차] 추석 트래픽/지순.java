class Solution {
    class Pair{
        int start, end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int solution(String[] lines) {
        int answer = 0;
        Pair arr[] = new Pair[lines.length];

        int idx=0;
        for(String line : lines) {
            String[] ln = line.split(" ");
            String[] time = ln[1].split(":");
            int end = Integer.parseInt(time[0])*60*60*1000;
            end += Integer.parseInt(time[1])*60*1000;
            String[] ms = time[2].split("\\.");
            end += Integer.parseInt(ms[0])*1000;
            end += Integer.parseInt(ms[1]);
            int sec = (int)(Double.parseDouble(ln[2].substring(0, ln[2].length()-1))*1000);
            int start = end - sec +1;
            
            if(start < 0) start = 0;
            
            arr[idx] = new Pair(start, end);
            idx++;
        }
        
        for(int i=0; i<arr.length; i++){
            answer = Math.max(answer, count(arr[i].start, arr, i));
            answer = Math.max(answer, count(arr[i].end, arr, i));
        }
        
        return answer;
    }
    int count(int start, Pair[] arr, int i){
        int end = start + 1000;
        int cnt = 1;
        for(int j=0; j<arr.length; j++){
            if(i==j) continue;
            if( arr[j].end < start || arr[j].start >= end ) continue;
            cnt++;
        }
        return cnt;
    }
    
}


------------------------------------------------------------------------------------------------------
import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        ArrayList<int[]> arr = new ArrayList<>();

        for(String line : lines) {
            String[] ln = line.split(" ");
            String[] time = ln[1].split(":");
            int end = Integer.parseInt(time[0])*60*60*1000;
            end += Integer.parseInt(time[1])*60*1000;
            String[] ms = time[2].split("\\.");
            end += Integer.parseInt(ms[0])*1000;
            end += Integer.parseInt(ms[1]);
            int sec = (int)(Double.parseDouble(ln[2].substring(0, ln[2].length()-1))*1000);
            int start = end - sec +1;
            
            if(start < 0) start = 0;
            
            arr.add(new int[]{start, end});
        }
        
        for(int i=0; i<arr.size(); i++){
            answer = Math.max(answer, count(arr.get(i)[0], arr, i));
            answer = Math.max(answer, count(arr.get(i)[1], arr, i));
        }
        
        return answer;
    }
    int count(int start, ArrayList<int[]> arr, int i){
        int end = start + 1000;
        int cnt = 1;
        for(int j=0; j<arr.size(); j++){
            if(i==j) continue;
            if( arr.get(j)[1] < start || arr.get(j)[0] >= end ) continue;
            cnt++;
        }
        return cnt;
    }
    
}
