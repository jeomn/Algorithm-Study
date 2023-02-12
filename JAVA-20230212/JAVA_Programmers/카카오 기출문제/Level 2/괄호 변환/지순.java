import java.util.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        answer = make(p);
        return answer;
    }
    
    public String make(String w) {
        if(w.equals("")) return w;
        String u = "";
        String v = "";
        int balance = 0;
        
        for(int i=0;i<w.length();i++) {
            if(w.charAt(i) == '(') balance++;
            else if(w.charAt(i) == ')') balance--;
            if(balance == 0) {
                u = w.substring(0, i+1);
                v = w.substring(i+1);
                break;
            }
        }

        if(check(u)) {
            u += make(v);
        } else {
            String temp = "(" + make(v) +")";
            u = u.substring(1, u.length()-1);
            
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<u.length();i++) {
                if(u.charAt(i) == '(')
                    sb.append(")");
                else
                    sb.append("(");
            }
            temp += sb.toString();
            return temp;
        }
        return u;
    }
    
    public boolean check(String u) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<u.length();i++) {
            if(u.charAt(i) == '(')
                stack.push('(');
            if(u.charAt(i) == ')' && !stack.isEmpty()) {
                stack.pop();
            }
        }
        
        if(stack.size()!=0) return false;
        else return true;
    }
}
