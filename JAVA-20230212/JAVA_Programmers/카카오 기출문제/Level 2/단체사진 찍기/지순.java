class Solution {
    char[] ch = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    char[] list;
    boolean[] visited;
    String[] d;
    int answer = 0;
    
    public int solution(int n, String[] data) {
        visited = new boolean[8];
        d = data;
        list = new char[8];
        dfs(n, 0);
        return answer;
    }
    private void dfs(int n, int cnt) {
        if(cnt == 8) {
            if(isPossible(n)) {
                answer++;
            }
            return;
        }
        for(int i=0;i<8;i++) {
            if(visited[i]) continue;
            visited[i] = true;
            list[cnt] = ch[i];
            dfs(n, cnt+1);
            visited[i] = false;
        }
    }
    
    private boolean isPossible(int n) {
        for(int i=0;i<n;i++) {
            char[] data = d[i].toCharArray();
            int from = 0, to = 0;
            for(int j=0;j<8;j++) {
                if(list[j] == data[0]) 
                    from =j;
                if(list[j] == data[2])
                    to = j;
            }
            
            int gap = Math.abs(from - to) - 1;
            int num = data[4] - '0';
            if(data[3] == '=') {
                if(gap != num) return false;
            } else if(data[3] == '>') {
                if(gap<=num) return false;
            } else if(data[3] == '<') {
                if(gap>=num) return false;
            }
        }
        return true;
    }
}
