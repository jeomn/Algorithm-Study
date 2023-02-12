import java.lang.*;
import java.util.*;

class Solution {
    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(picture[i][j] > 0 && !visited[i][j]) {
                    int cnt = bfs(i, j, picture, m, n);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
                    numberOfArea++;
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    public int bfs(int x, int y, int[][] picture, int M, int N) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        int cnt = 1;
        
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            
            for(int i=0;i<4;i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx<0 || nx>=M || ny<0 || ny>=N || visited[nx][ny]) {
                    continue;
                }
                if(picture[nx][ny] == picture[p.x][p.y]) {
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
