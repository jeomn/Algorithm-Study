//2017 카카오코드 예선
//20211227


import java.util.*;

class Node{
    int r, c;
    Node(int r, int c){
        this.r = r;
        this.c = c;
    }
}

class Solution {
    int M, N;
    int[][] pic;
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        M = m;
        N = n;
        pic = picture;
        visited = new boolean[m][n];
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(visited[r][c]) continue;
                
                int areaSize = findArea(r, c, picture[r][c]);
                if(picture[r][c] == 0) continue;
                numberOfArea++;
                maxSizeOfOneArea = Math.max(areaSize, maxSizeOfOneArea);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int findArea(int r, int c, int color){
        Deque<Node> route = new LinkedList<>();
        route.add(new Node(r, c));
        visited[r][c] = true;
        int count = 1;
        
        while(!route.isEmpty()){
            Node n = route.poll();
            for(int d=0; d<4; d++){
                int nr = n.r+dx[d];
                int nc = n.c+dy[d];
                
                if(nr<0 || nr>=M || nc<0 || nc>=N) continue;
                if(visited[nr][nc] || pic[nr][nc] != color) continue;
                
                route.add(new Node(nr, nc));
                visited[nr][nc] = true;
                count++;
            }
        }
        return count;
    }
}
