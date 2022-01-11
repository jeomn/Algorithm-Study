import java.util.*;
class Solution {
    public int[] solution(String s) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        s = s.substring(2, s.length());
        s = s.substring(0, s.length()-2);
        
        s = s.replace("},{", "-");
        
        String[] str = s.split("-");
        
        Arrays.sort(str, new  Comparator<String>() {
            public int compare(String o1, String o2) {     
                return o1.length() - o2.length();
            }
        });
        
        for(int i=0;i<str.length;i++) {
            String[] st = str[i].split(",");
            for(int j=0;j<st.length;j++) {
                int num = Integer.parseInt(st[j]);
                if(!answer.contains(num)) {
                    answer.add(num);
                }
            }
        }
        
        System.out.println(answer.get(0));
        int[] ans = new int[answer.size()];
        for(int i=0;i<answer.size();i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }
}
