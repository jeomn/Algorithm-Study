class Solution {
    char[][] map;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        map = new char[m][n];
        
        for(int i=0;i<board.length;i++) {
            map[i] = board[i].toCharArray();
        }
        
        while(true) {
            int cnt = removeBlock(m, n);
            if(cnt == 0) break;
            answer += cnt;
            dropBlock(m, n);
        }
        
        return answer;
    }
    
    
    public void dropBlock(int m, int n) {
        for(int i=0;i<n;i++) {
            for(int j=m-2;j>=0;j--) {
                if(map[j][i] == '.') 
                    continue;
                
                int w = j+1;
                
                while(true) {
                    if(map[w][i] != '.') {
                        char temp = map[j][i];
                        map[j][i] = '.';
                        map[w-1][i] = temp;
                        break;
                    } else {
                        if(w == m-1) {
                            map[w][i] = map[j][i];
                            map[j][i] = '.';
                            break;
                        } else {
                            w++;
                        }
                    }
                }
                
            }
        }
    }
    
    public int removeBlock(int m, int n) {
        boolean[][] checked = new boolean[m][n];
        int cnt = 0;
        for(int i=0;i<m-1;i++) {
            for(int j=0;j<n-1;j++) {
                if(map[i][j] == '.')
                    continue;
                if(map[i][j] == map[i+1][j] && map[i][j] == map[i][j+1] && map[i][j] == map[i+1][j+1]) {
                    checked[i][j] = true;
                    checked[i+1][j] = true;
                    checked[i][j+1] = true;
                    checked[i+1][j+1] = true;
                }
            }
        }
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(checked[i][j]) {
                    map[i][j] = '.';
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
