import java.util.*;

class Solution {
    char[][] map;
    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public class Point {
        int x, y, dis;
        Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer;
        ArrayList<Integer> answerList = new ArrayList<Integer>();
        for(String[] place : places) {
            map = new char[5][5];
            visited = new boolean[5][5];
            boolean check = false;
            for(int i=0;i<place.length;i++) {
                map[i] = place[i].toCharArray();
            }
            
            for(int r=0;r<5;r++) {
                for(int c=0;c<5;c++) {
                    if(map[r][c] == 'P') {
                        if(!bfs(r, c)) {
                            check = true;
                            break;
                        }
                    }
                }
            }
            
            if(check)
                answerList.add(0);
            else
                answerList.add(1);
        }
        
        answer = new int[5];
        for(int i=0;i<5;i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    public boolean bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c, 0));
        visited[r][c] = true;
        boolean check = false;
        
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            
            if(p.dis >= 2) continue;
            
            for(int i=0;i<4;i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                
                if(map[nx][ny] == 'O')
                    queue.add(new Point(nx, ny, p.dis + 1));
                else if(map[nx][ny] == 'P') {
                    if(p.dis <= 2) {
                        check = true;
                        break;
                    }
                }
            }
            
            if(check)
                return false;
        }
        return true;
    }
}
