//20220110
//2018 KAKAO BLIND RECRUITMENT



import java.util.*;

class Node implements Comparable<Node>{
    int r, c;
    Node(int r, int c){
        this.r = r;
        this.c = c;
    }
    
    public int compareTo(Node o){
        int x = this.r-o.r;
        if(x==0) return this.c-o.c;
        return x;
    }
}
class Solution {
    
    char[][] game;    
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        game = new char[m][n];
        for(int r=0; r<m; r++){
            game[r] = board[r].toCharArray();
        }

        ArrayList<Node> erase = new ArrayList<>();
         while(true){
            for(int r=0; r<m-1; r++){
                for(int c=0; c<n-1; c++){
                    if(game[r][c] == '0') continue;
                    if(check(r, c, game[r][c])){
                        erase.add(new Node(r, c));
                        erase.add(new Node(r, c+1));
                        erase.add(new Node(r+1, c));
                        erase.add(new Node(r+1, c+1));
                    }
                }
            }
            
             if(erase.size() == 0) break;
             else{
                 boolean[][] visited = new boolean[m][n];
                 Collections.sort(erase);
                 for(Node e : erase){
                     int x = e.r;
                     int y = e.c;
                     if(x==0){
                         game[x][y] = '0';
                     }else{
                         if(game[x][y] == '0' || visited[x][y]) continue;
                         for(int r=x; r>=0; r--){
                             if(r==0) game[r][y] = '0';
                             else game[r][y] = game[r-1][y];
                         }
                     }
                     if(!visited[x][y]) answer++;
                     visited[x][y] = true;
                 }
             }
             erase.clear();
        }
        return answer;
    }
    
    public boolean check(int r, int c, char k){
        int[][] checkList = new int[][]{{r, c}, {r, c+1}, {r+1, c}, {r+1, c+1}};
        for(int[] cl : checkList){
            if(game[cl[0]][cl[1]] != k) return false;
        }
        return true;
    }
}
