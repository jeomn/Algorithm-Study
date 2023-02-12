//20220110
//2018 KAKAO BLIND RECRUITMENT


import java.util.*;


class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        LinkedList<String> cache = new LinkedList<>();
        int idx = -1;
        for(String city : cities){
            city = city.toLowerCase();
            if(cache.contains(city)){
                answer++;
                idx = cache.indexOf(city);
                cache.remove(idx);
                cache.add(city);
            }else{
                answer+=5;
                if(cache.size()!=0 && cache.size() >= cacheSize) cache.remove(0);
                if(cacheSize!=0) cache.add(city);
            }
        }
        
        return answer;
    }
}
