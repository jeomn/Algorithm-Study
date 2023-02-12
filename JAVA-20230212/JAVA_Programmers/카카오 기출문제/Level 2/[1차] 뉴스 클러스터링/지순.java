import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        ArrayList<String> multiSet1 = new ArrayList<String>();
        ArrayList<String> multiSet2 = new ArrayList<String>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i=0;i<str1.length()-1;i++){
            char a = str1.charAt(i);
            char b = str1.charAt(i+1);
            
            if(a>='a' && a<='z' && b >= 'a' && b <='z') {
                String str = a + "";
                str += b+"";
                multiSet1.add(str);
            }
        }
        
        for(int i=0;i<str2.length()-1;i++){
            char a = str2.charAt(i);
            char b = str2.charAt(i+1);
            
            if(a>='a' && a<='z' && b >= 'a' && b <='z') {
                String str = a + "";
                str += b+"";
                multiSet2.add(str);
            }
        }
        
        ArrayList<String> inter = new ArrayList<String>();
        ArrayList<String> union = new ArrayList<String>();
        
        for(int i=0;i<multiSet1.size();i++) {
            String str = multiSet1.get(i);
            if(multiSet2.contains(str)) {
                multiSet2.remove(str);
                inter.add(str);
            }
            union.add(str);
        }
        
        for(int i=0;i<multiSet2.size();i++) {
            union.add(multiSet2.get(i));
        }
        
        double result = 0;
        if(union.size() == 0)
            result = 1.0;
        else
            result = (double)inter.size() / (double)union.size();
        
        answer = (int) (result * 65536);
        return answer;
    }
}
