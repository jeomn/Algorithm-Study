#2018 KAKAO BLIND RECRUITMENT
#20211222

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i=0; i<n; i++){
            int num = arr1[i]|arr2[i];
            String arr = Integer.toBinaryString(num);
            arr = arr.replace("1", "#");
            arr = arr.replace("0", " ");
            if(arr.length() < n) arr = makeString(n-arr.length()) + arr;
            answer[i] = arr;
        }
        return answer;
    }
    
    public String makeString(int num){
        String zero = "";
        for(int i=0; i<num; i++){
            zero += " ";
        }
        return zero;
    }
}
