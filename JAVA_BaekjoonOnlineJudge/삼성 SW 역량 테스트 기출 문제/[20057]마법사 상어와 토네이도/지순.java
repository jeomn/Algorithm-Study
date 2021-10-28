import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_마법사상어와토네이도_20057 {
	static int N;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int result;

	static int[][] moveX = { 
        {-1, 1, -2, -1, 1, 2, -1, 1, 0},
        {-1, -1, 0, 0, 0, 0, 1, 1, 2}, 
        {1, -1, 2, 1, -1, -2, 1, -1, 0},
        {1, 1, 0, 0, 0, 0, -1, -1, -2}
	};
	
	static int[][] moveY = {
        {1, 1, 0, 0, 0, 0, -1, -1, -2},
        {-1, 1, -2, -1, 1, 2, -1, 1, 0},    
        {-1, -1, 0, 0, 0, 0, 1, 1, 2},
        {1, -1, 2, 1, -1, -2, 1, -1, 0}
	};
	
	static double percent[] = {0.01,0.01,0.02,0.07,0.07,0.02,0.1, 0.1,0.05};

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		solve();
		System.out.println(result);
	}

	private static void solve() {
		int x = N / 2;
		int y = N / 2;

		int dir = 0;
		int size = 0;
		int idx = 1;
		
		while (true) {
			if (x == 0 && y == 0)
				break;
			x += dx[dir];
			y += dy[dir];
			size++;
			
			int ex = map[x][y];
			int alpa = map[x][y];
			
			map[x][y] = 0;
			for (int i = 0; i < 9; i++) {
				int nx = x + moveX[dir][i];
				int ny = y + moveY[dir][i];
				int sand = (int)(ex*percent[i]);
				alpa -= sand;
				if(nx<0 || nx>=N || ny<0 || ny>=N) {
					result += sand;
					continue;
				}
				
				map[nx][ny] += sand;
			}
			
			if(x+dx[dir]< 0 || x+dx[dir]>=N || y+dy[dir]<0 || y+dy[dir]>=N)
				result += alpa;
			else
				map[x+dx[dir]][y+dy[dir]] += alpa;
			
			if(size == idx) {
				dir++;
				if(dir%4 == 0 || dir%4 == 2) idx++;
				size = 0;
				dir %=4;
			}
		}
	}
}
