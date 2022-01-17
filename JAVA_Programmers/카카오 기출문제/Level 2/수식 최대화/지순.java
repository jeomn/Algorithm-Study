import java.util.*;

class Solution {
    public LinkedList<Long> numList = new LinkedList<>();
    public LinkedList<Character> operList = new LinkedList<>();
    public char[][] order = {
        {'+', '-', '*'}, {'+', '*', '-'},
        {'-', '+', '*'}, {'-', '*', '+'},
        {'*', '+', '-'}, {'*', '-', '+'}
    };
    
    public long solution(String expression) {
        long answer = 0;
        
        char[] exp = expression.toCharArray();
        String str = "";
        for(int i=0;i<exp.length;i++) {
            if(exp[i] == '+' || exp[i] == '-' || exp[i] == '*') {
                numList.add(Long.valueOf(str));
                operList.add(exp[i]);
                str = "";
            } else {
                str += exp[i] +"";
            }
        }
        
        numList.add(Long.valueOf(str));
        
        for(int i=0;i<order.length;i++) {
            LinkedList<Long> temp = new LinkedList<>(numList);
            LinkedList<Character> operTemp = new LinkedList<>(operList);

            for(char c: order[i]) {
                for(int j=0;j<operTemp.size();j++) {
                    char op = operTemp.get(j);
                    if(op != c) continue;
                    
                    long l1 = temp.get(j);
                    long l2 = temp.get(j+1);
                    
                    temp.remove(j+1);
                    temp.remove(j);
                    
                    operTemp.remove(j);
                    temp.add(j, cal(l1, l2, op));
                    j--;
                }
            }
            answer = Math.max(answer, Math.abs(temp.get(0)));
        }
        
        return answer;
    }
    
    public long cal(long l1, long l2, char op) {
        long result = 0;
        if(op == '+') {
            result =  l1 + l2;
        } else if(op == '-') {
            result = l1 - l2;
        } else {
            result = l1 * l2;
        }
        return result;
    }
}
