import java.util.*;


class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        ArrayList<Integer> basket = new ArrayList<Integer>();
        for(int m:moves){
            int depth = 0;
            while(depth != board.length){
                if(board[depth][m-1] != 0){
                    basket.add(board[depth][m-1]);
                    board[depth][m-1] = 0;
                    break;
                }
                depth++;
            }
            
            int length = basket.size();
            if(length >= 2){
                if(basket.get(length-1) == basket.get(length-2)){
                    basket.remove(length-1);
                    basket.remove(length-2);
                    answer+=2;
                }
            }
        }
        
        return answer;
    }
}
