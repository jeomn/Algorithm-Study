import java.util.PriorityQueue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(String time : timetable) {
            String[] times = time.split(":");
            int hh = Integer.parseInt(times[0]);
            int mm = Integer.parseInt(times[1]);
            
            queue.add(hh*60 + mm);
        }
        
        int startTime = 9*60;
        int lastTime = 0;
        
        int crew = 0;
        for(int i=0;i<n;i++) {
            crew = 0;
            while(!queue.isEmpty()) {
                int time = queue.poll();
                if(time<=startTime && crew < m) {
                    crew++;
                } else {
                    queue.add(time);
                    break;
                }
                lastTime = time - 1;
            }
            startTime += t;
        }
        
        if(crew < m)
            lastTime = startTime - t;
        
        String hour = String.format("%02d", lastTime/60);
        String min = String.format("%02d", lastTime%60);
        answer = hour +":"+min;
        return answer;
    }
}
