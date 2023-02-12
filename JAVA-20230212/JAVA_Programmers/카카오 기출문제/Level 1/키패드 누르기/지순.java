class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[][] location = {{3,1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2,2}};
        int[] locL = {3, 0};
        int[] locR = {3, 2};
        
        for(int num: numbers) {
            if(num == 1 || num == 4 || num == 7) {
                answer+="L";
                locL = location[num].clone();
            } else if(num == 3 || num == 6 || num == 9) {
                answer+="R";
                locR = location[num].clone();
            } else {
                int disL = Math.abs(locL[0] - location[num][0]) + Math.abs(locL[1] - location[num][1]);
                int disR = Math.abs(locR[0] - location[num][0]) + Math.abs(locR[1] - location[num][1]);
                if(disL < disR) {
                    answer+="L";
                    locL = location[num].clone();
                } else if(disL > disR) {
                    answer+="R";
                    locR = location[num].clone();
                } else {
                    if("right".equals(hand)) {
                        answer+="R";
                        locR = location[num].clone();
                    } else {
                        answer += "L";
                        locL = location[num].clone();
                    }
                }
            }
        }
        return answer;
    }
}
