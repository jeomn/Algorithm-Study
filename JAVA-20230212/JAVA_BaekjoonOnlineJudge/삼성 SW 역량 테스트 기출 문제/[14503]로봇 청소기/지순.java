import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_로봇청소기_14503 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 1;
		dfs(r, c, d);

		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int d) {
		map[r][c] = 2;

		for(int i=1;i<=4;i++) {
			int dir = d-i;
			if(dir<0) dir = 4 + dir;
			int nx = r + dx[dir];
			int ny = c + dy[dir];
			
			if(nx<0 || nx >=N || ny<0 || ny>=M) continue;
			
			if(map[nx][ny] == 0) {
				cnt++;
				dfs(nx, ny, dir);
				return;
			}
		}
		// 후진
		int nx = r + dx[(d+2) %4];
		int ny = c + dy[(d+2) %4];
		
		if(nx>=0 && nx < N && ny>=0 && ny<M && map[nx][ny] != 1) {
			dfs(nx, ny, d); // 방향은 같음
		}
	}
}
