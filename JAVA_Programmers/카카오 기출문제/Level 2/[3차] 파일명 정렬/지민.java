//2018 KAKAO BLIND RECRUITMENT
//20220104


import java.util.*;


class FileName implements Comparable<FileName>{
    int order, number;
    String head, origin;
    FileName(int order, String origin, String head, int number){
        this.order = order;
        this.origin = origin;
        this.head = head;
        this.number = number;
    }
    
    public int compareTo(FileName o){
        int x = this.head.compareTo(o.head);
        if(x==0){
            int y = this.number - o.number;
            if(y==0){
                int z = this.order - o.order;
                return z;
            }
            return y;
        }
        return x;
    }
}


class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        PriorityQueue<FileName> fileSort = new PriorityQueue<FileName>();
        for(int i=0; i<files.length; i++){
            String file = files[i];
            boolean flag = false;
            String head = "", tempNum = "";
            int number = 0;
            check: for(int j=0; j<file.length(); j++){
                if(Character.isDigit(file.charAt(j))){
                    int len = file.length()>(j+5)? j+5:file.length();
                    for(int k=j; k<len; k++){
                        if(Character.isDigit(file.charAt(k))) tempNum+=file.charAt(k);
                        else break check;
                    }
                    break;
                }
                head += file.charAt(j);
            }
            head = head.toLowerCase();
            number = Integer.parseInt(tempNum);
            fileSort.offer(new FileName(i, file, head, number));
        }
        
        for(int i=0; i<files.length; i++){
            FileName fn = fileSort.poll();
            answer[i] = fn.origin;
        }
        
        return answer;
    }
}
