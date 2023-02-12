import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {
	static int N, M;
	static int[][] map;
	static int cnt = 0;
	static int[] dx = {-1, 0, 1, 0}; 
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt++;
		dfs(x, y, d);
		System.out.println(cnt);
	}
	
	public static void dfs(int x, int y, int d) {
		map[x][y] = 2;
		
		for(int i=0;i<4;i++) {
			int nd = (d+3)%4;
			int nx = x + dx[nd];
			int ny = y + dy[nd];
			
			if(nx < 0|| nx>=N || ny<0 || ny>=M) continue;
			if(map[nx][ny] == 0) {
				cnt++;
				dfs(nx, ny, nd);
				return;
			}
			d = nd;
		}
		
		int nx = x + dx[(d+2) %4];
		int ny = y + dy[(d+2) %4];
		
		if(nx>=0 && nx < N && ny>=0 && ny<M && map[nx][ny] != 1) {
			dfs(nx, ny, d);
		}
	}

}
