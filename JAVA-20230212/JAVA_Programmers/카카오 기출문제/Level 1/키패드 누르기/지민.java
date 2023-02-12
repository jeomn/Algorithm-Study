//20211224
//2020 카카오 인턴십


class Solution {
    public String solution(int[] numbers, String hand) { 
        String answer = "";
        int lLast = 10;
        int rLast = 12;
        for(int n : numbers){
            if(n==0) n=11;
            if(n==1 || n==4 || n==7){
                answer+="L";
                lLast = n;
            }else if(n==3 || n==6 || n==9){
                answer+="R";
                rLast = n;
            }else{
                int lDiffer = Math.abs(lLast-n);
                int lx = lDiffer/3, ly = lDiffer%3;
                int rDiffer = Math.abs(rLast-n);
                int rx = rDiffer/3, ry = rDiffer%3;
                
                int ld = lx+ly;
                int rd = rx+ry;
                
                if(ld < rd){
                    answer+="L";
                    lLast = n;
                }else if(rd < ld){
                    answer+="R";
                    rLast = n;
                }else{
                    if(hand.equals("left")){
                        answer+="L";
                        lLast = n;
                    }else{
                        answer+="R";
                        rLast = n;
                    }
                }  
            }
        }
        return answer;
    }
}
