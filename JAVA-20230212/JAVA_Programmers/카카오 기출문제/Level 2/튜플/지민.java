//20220111
//2019 카카오 개발자 겨울 인턴십



import java.util.*;

class Tuple implements Comparable<Tuple>{
    ArrayList<Integer> numbers;
    int order, len;
    Tuple(int order, int len, ArrayList numbers){
        this.order = order;
        this.len = len;
        this.numbers = numbers;
    }
    
    public int compareTo(Tuple o){
        int x = this.len-o.len;
        if(x==0) return this.order-o.order;
        return x;
    }
}

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.replace("{{", "");
        s = s.replace("}}", "");
        String[] ss = s.split("\\},\\{");
        
        PriorityQueue<Tuple> tupleOrder = new PriorityQueue<>();
        for(int i=0; i<ss.length; i++){
            String str = ss[i];
            String[] num = str.split(",");
            ArrayList<Integer> numbers = new ArrayList<>();
            for(String n:num){
                numbers.add(Integer.parseInt(n));
            }
            tupleOrder.add(new Tuple(i, numbers.size(), numbers));
        }
        
        ArrayList<Integer> tupleList = new ArrayList<>();
        HashSet<Integer> numberSet = new HashSet<>();
        while(!tupleOrder.isEmpty()){
            Tuple t = tupleOrder.poll();
            for(int n : t.numbers){
                if(numberSet.contains(n)) continue;
                tupleList.add(n);
                numberSet.add(n);
            }
        }
        
        answer = new int[tupleList.size()];
        for(int i=0; i<tupleList.size(); i++){
            answer[i] = tupleList.get(i);
        }
        
        return answer;
    }
}
