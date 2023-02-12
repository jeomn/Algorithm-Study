class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for(int i=1;i<s.length()+1;i++) {
            int count = 1;
            String temp = "";
            String result = "";
            
            int idx = 0;
            for(int j=i;j<=s.length();j+=i) {
                if(temp.equals(s.substring(j-i, j))) {
                    count++;
                } else {
                    if(count>1)
                        result += String.valueOf(count);
                    result += temp;
                    count = 1;
                }
                temp = s.substring(j-i, j);
                idx = j;
            }
            if(count>1)
                result += String.valueOf(count);
            result += temp;
            result += s.substring(idx, s.length());
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}
