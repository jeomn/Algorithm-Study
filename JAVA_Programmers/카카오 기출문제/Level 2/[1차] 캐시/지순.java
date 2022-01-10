import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        LinkedList<String> cache = new LinkedList<String>();
        
        if(cacheSize == 0) {
            return cities.length * 5;
        }
        
        int answer = 0;
        
        for(String city:cities) {
            city = city.toLowerCase();
            
            if(cache.contains(city)) { // 히트이면
                cache.remove(city);
                cache.add(city);
                answer += 1;
            } else {
                if(cache.size() == cacheSize) {
                    cache.remove(0);
                }
                cache.add(city);
                answer += 5;
            }
        }
        
        return answer;
    }
}
