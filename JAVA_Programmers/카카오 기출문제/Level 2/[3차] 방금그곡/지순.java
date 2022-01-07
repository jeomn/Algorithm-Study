class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int playtime = 0;
        m = setSound(m);
        
        for(String musicinfo: musicinfos) {
            String[] info = musicinfo.split(",");
            info[3] = setSound(info[3]);
            
            int time = setTime(info[0], info[1]);
            if(time>info[3].length()) {
                StringBuilder sb = new StringBuilder();
                for(int i=0;i<time/info[3].length();i++) {
                    sb.append(info[3]);
                }
                sb.append(info[3].substring(0, time%info[3].length()));
                info[3] = sb.toString();
            } else {
                info[3] = info[3].substring(0, time);
            }
            
            if(info[3].contains(m) && time > playtime) {
                answer = info[2];
                playtime = time;
            }
        }
        
        if(answer.length() == 0) 
            answer = "(None)";
        
        return answer;
    }
    public String setSound(String str) {
        str = str.replaceAll("C#", "c");
        str = str.replaceAll("D#", "d");
        str = str.replaceAll("F#", "f");
        str = str.replaceAll("G#", "g");
        str = str.replaceAll("A#", "a");
        return str;
    }
    public int setTime(String time1, String time2) {
        String[] timeList1 = time1.split(":");
        String[] timeList2 = time2.split(":");
        
        int hour = Integer.parseInt(timeList2[0]) - Integer.parseInt(timeList1[0]);
        int min = Integer.parseInt(timeList2[1]) - Integer.parseInt(timeList1[1]);
        
        return hour*60+min;
    }
}
