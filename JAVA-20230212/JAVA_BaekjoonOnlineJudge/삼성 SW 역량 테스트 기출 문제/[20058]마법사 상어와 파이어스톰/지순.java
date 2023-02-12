import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_마법사상어와파이어스톰_20058 {
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int N;
	static int largeIce;
	static int map[][];
	static boolean[][] visited;
	
	static class Point{
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		N = (int) Math.pow(2, N);
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			L = (int) Math.pow(2, L);
			rotate(L);
			melt();
		}
		largeIce = Integer.MIN_VALUE;
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				visited[i][j] = true;
				bfs(new Point(i, j));
			}
		}
		
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				result+= map[i][j];
			}
		}
		System.out.println(result);
		if(largeIce == Integer.MIN_VALUE)
			System.out.println(0);
		else
			System.out.println(largeIce);
		
	}
	
	private static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(p);
		
		int cnt = 1;
		while(!queue.isEmpty()) {
			Point pp = queue.poll();
			for(int i=0;i<4;i++) {
				int nx = pp.x + dx[i];
				int ny = pp.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(map[nx][ny] ==0 || visited[nx][ny]) continue;
				
				queue.add(new Point(nx, ny));
				visited[nx][ny] = true;
				cnt++;
			}
		}
		largeIce = Math.max(largeIce, cnt);
	}
	
	private static void melt() {
		ArrayList<Point> meltIce = new ArrayList<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int cnt = 0;
				if(map[i][j] == 0) continue;
				
				for(int d=0;d<4;d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
					if(map[nx][ny] ==0) continue;
					cnt++;
				}
				if(cnt<3)
					meltIce.add(new Point(i, j));
			}
		}
		for (Point point : meltIce) {
			map[point.x][point.y]--;
		}
	}

	private static void rotate(int L) {
		int[][] temp = new int[N][N];

		for (int r = 0; r < N; r += L) {
			for (int c = 0; c < N; c += L) {
				for(int i=0;i<L;i++) {
					for(int j=0;j<L;j++) {
						temp[r+i][c+j] = map[r+L-1-j][c+i];
					}
				}
			}
		}
		
		for(int i=0;i<N;i++) {
			map[i] = temp[i].clone();
		}
	}
}
