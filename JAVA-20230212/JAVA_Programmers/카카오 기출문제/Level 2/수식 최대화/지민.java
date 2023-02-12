import java.util.*;

class Solution {
    
    long answer;
    ArrayList<Long> number;
    ArrayList<Character> operation;
    char[] init, operPrior;
    boolean[] isSelect;
    
    public long solution(String expression) {
        
        number = new ArrayList<>();
        operation = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(char e : expression.toCharArray()){
            if(!Character.isDigit(e)){
                number.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
                operation.add(e);
            }else{
                sb.append(e);
            }
        }
        number.add(Long.parseLong(sb.toString()));
        
        init = new char[]{'+', '*', '-'};
        operPrior = new char[3];
        isSelect = new boolean[3];
        setPriority(0);
        
        return answer;
    }
    
    public void setPriority(int cnt){
        if(cnt == 3){
            answer = Math.max(getTotal(), answer);
            return;
        }
        
        for(int i=0; i<3; i++){
            if(isSelect[i]) continue;
            operPrior[cnt] = init[i];
            isSelect[i] = true;
            setPriority(cnt+1);
            isSelect[i] = false;
        }
    }
    
    public long getTotal(){
        ArrayList<Long> tempNum = new ArrayList<>(number);
        ArrayList<Character> tempOper = new ArrayList<>(operation);
        for(int i=0; i<3; i++){
            char o = operPrior[i];
            while(tempNum.size() != 1){
                if(!tempOper.contains(o)) break;
                int idx = tempOper.indexOf(o);
                long sum = calcNum(tempNum.get(idx), tempNum.get(idx+1), o);
                tempNum.set(idx, sum);
                tempNum.remove(idx+1);
                tempOper.remove(idx);
            }
        }
        return Math.abs(tempNum.get(0));
    }
    
    public long calcNum(long a, long b, char o){
        if(o == '+') return a+b;
        else if(o == '*') return a*b;
        else return a-b;
    }
}
