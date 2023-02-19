class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        answer = new String[n];

        String length = "%"+n+"s";
        for(int i=0; i<n; i++){
            int numRow = arr1[i]|arr2[i];
            String mapRow = String.format(length, Integer.toBinaryString(numRow));
            mapRow = mapRow.replace("0", " ");
            mapRow = mapRow.replace("1", "#");
            answer[i] = mapRow;
        }

        return answer;
    }
}