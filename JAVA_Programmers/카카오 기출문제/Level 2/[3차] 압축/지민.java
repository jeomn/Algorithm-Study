//20220106
//2018 KAKAO BLIND RECRUITMENT


import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        
        HashMap<String, Integer> dictionary = new HashMap<String, Integer>(){
            {
                put("A", 1);
                put("B", 2);
                put("C", 3);
                put("D", 4);
                put("E", 5);
                put("F", 6);
                put("G", 7);
                put("H", 8);
                put("I", 9);
                put("J", 10);
                put("K", 11);
                put("L", 12);
                put("M", 13);
                put("N", 14);
                put("O", 15);
                put("P", 16);
                put("Q", 17);
                put("R", 18);
                put("S", 19);
                put("T", 20);
                put("U", 21);
                put("V", 22);
                put("W", 23);
                put("X", 24);
                put("Y", 25);
                put("Z", 26);
            }
        };
        
        Deque<Integer> numList = new LinkedList<Integer>();
        StringBuilder sb = new StringBuilder();
        int idx = 27;
        for(int i=0; i<msg.length(); i++){
            sb.append(msg.charAt(i));
            int num = dictionary.get(sb.toString());
            for(int j=i+1; j<msg.length(); j++){
                sb.append(msg.charAt(j));
                i = j==msg.length()-1? j:j-1;
                int new_num = dictionary.getOrDefault(sb.toString(), -1);
                if(new_num == -1){
                    dictionary.put(sb.toString(), idx++);
                    i = j-1;
                    break;
                }
                num = new_num;
            }
            numList.offerLast(num);
            sb.setLength(0);
        }            
        
        int len = numList.size();
        answer = new int[len];
        for(int i=0; i<len; i++){
            answer[i] = numList.poll();
        }
        return answer;
    }
}
