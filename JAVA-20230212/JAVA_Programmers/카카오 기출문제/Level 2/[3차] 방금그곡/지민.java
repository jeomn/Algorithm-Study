//20220107

import java.util.*;

class Music implements Comparable<Music>{
    int order, time;
    String name, melody;
    Music(int order, String name, int time, String melody){
        this.order = order;
        this.name = name;
        this.time = time;
        this.melody = melody;
    }
    
    public int compareTo(Music o){
        int x = o.time - this.time;
        if(x==0) return this.order-o.order;
        return x;
    }
}

class  Solution {
    
    HashMap<String, String> soundMap = new HashMap<>(){
        {
            put("C#", "c");
            put("D#", "d");
            put("F#", "f");
            put("G#", "g");
            put("A#", "a");
        }  
    };
    
	public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		
        m = setSound(m);
        
        int idx = 0;
        PriorityQueue<Music> candidate = new PriorityQueue<Music>();
        for(String musicInfo : musicinfos){
            String[] infos = musicInfo.split(",");
            int time = getTimeDiffer(infos[0], infos[1]);
            
            String melody = "";
            infos[3] = setSound(infos[3]);
            int len = infos[3].length();
            for(int t=0; t<time; t++){
                melody+=infos[3].charAt(t%len);
            }
            if(melody.contains(m)) candidate.add(new Music(idx, infos[2], time, melody));
            idx++;
        }
		
        if(candidate.size() >= 1) answer = candidate.poll().name;
        return answer;
	}
    
    public String setSound(String origin){
        String sound = origin;
        for(String key : soundMap.keySet()){
            sound = sound.replace(key, soundMap.get(key));
        }
        return sound;
    }
    
    public int getTimeDiffer(String startTime, String endTime){
        String[] start = startTime.split(":");
        String[] end = endTime.split(":");
        
        int startHour = Integer.parseInt(start[0]);
        int startMinutes = Integer.parseInt(start[1]);
        int endHour = Integer.parseInt(end[0]);
        int endMinutes = Integer.parseInt(end[1]);
        
        return (endHour-startHour)*60+(endMinutes-startMinutes);
    }
}
