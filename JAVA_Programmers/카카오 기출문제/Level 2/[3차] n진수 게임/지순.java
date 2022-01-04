class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        
        int idx = 0;
        while(sb.length() <= t*m) {
            sb.append(Integer.toString(idx, n));
            idx++;
        }

        for(int i = p-1;i<t*m;i+=m) {
            answer.append(sb.charAt(i));
        }
        return answer.toString().toUpperCase();
    }
}
