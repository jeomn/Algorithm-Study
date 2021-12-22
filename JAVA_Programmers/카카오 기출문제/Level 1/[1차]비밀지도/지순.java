class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i=0;i<n;i++) {
            String str = Integer.toBinaryString(arr1[i] | arr2[i]);
            if(str.length() != n) {
                String temp = "";
                
                for(int j=0;j<n-str.length();j++) {
                    temp += " ";
                }
                str = temp + str;
            }
            str = str.replaceAll("1", "#");
            str = str.replaceAll("0", " ");
            answer[i] = str;
        }
        return answer;
    }
}
