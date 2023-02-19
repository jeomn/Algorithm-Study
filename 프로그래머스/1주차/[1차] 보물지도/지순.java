class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i=0;i<n;i++) {
            String temp = "";
            // String result = Integer.toBinaryString(arr1[i] | arr2[i]);
            String result = tenToTwo(arr1[i] | arr2[i]);

            if (result.length() != n) {
                result = "0".repeat(n - result.length()) + result;
            }

            result = result.replaceAll("1", "#");
            answer[i] = result.replaceAll("0", " ");
        }

        return answer;
    }

    public String tenToTwo(int n) {
        String str = "";

        while(n > 0){
            str = String.valueOf(n % 2) + str;
            n /= 2;
        }

        return str;
    }
}