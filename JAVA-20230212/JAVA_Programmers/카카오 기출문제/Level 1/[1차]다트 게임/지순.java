class Solution {
    public int solution(String dartResult) {
        int[] arr = new int[3];
        int idx = -1;
        
        for(int i=0;i<dartResult.length();i++) {
            char dart = dartResult.charAt(i);
            if(dart >= '0' && dart <= '9') {
                idx++;
                if(dart=='1' && dartResult.charAt(i+1) == '0') {
                    arr[idx] = 10;
                    i++;
                } else {
                    arr[idx] = dart-'0';
                }
            } else if(dart == 'D') {
                arr[idx] *= arr[idx];
            } else if(dart == 'T') {
                arr[idx] *= arr[idx]*arr[idx];
            } else if(dart == '*') {
                arr[idx] *= 2;
                if(idx>0) {
                    arr[idx-1] *=2;
                }
            } else if(dart == '#') {
                arr[idx] *= -1;
            }
        }
        return arr[0] + arr[1] + arr[2];
    }
}
