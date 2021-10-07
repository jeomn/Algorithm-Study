import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_감시_15683 {
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result;
	static boolean[][] visited;
	static ArrayList<Point> cctvList;
	static int[][][] state = { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 } }, { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };

	static class Point {
		int x, y, cctv;

		Point(int x, int y, int cctv) {
			this.x = x;
			this.y = y;
			this.cctv = cctv;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		cctvList = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new Point(i, j, map[i][j]));
				}
			}
		}
		
		result = Integer.MAX_VALUE;
		solve(0, map);
		System.out.println(result);
	}

	private static void solve(int idx, int[][] arr) {
		if(idx == cctvList.size()) {
			int cnt = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(arr[i][j] == 0) cnt++;
				}
			}
			result = Math.min(result, cnt);
			return;
		}

		int[][] temp = new int[N][M];
		copy(temp, arr);
		Point p = cctvList.get(idx);

		for (int j = 0; j < state[p.cctv - 1].length; j++) {
			for (int k = 0; k < state[p.cctv - 1][j].length; k++) {
				int nx = p.x;
				int ny = p.y;
				while(true) {
					nx += dx[state[p.cctv-1][j][k]];
					ny += dy[state[p.cctv-1][j][k]];
					
					if(nx<0 || nx>=N || ny<0 || ny>=M || temp[nx][ny]==6) break;
					if(temp[nx][ny] != 0) continue;
					temp[nx][ny] = -1;
				}
			}

			solve(idx+1, temp);
			copy(temp, arr);
		}
	}
	
	static void copy(int[][] temp, int[][] orimap) {
		for(int i=0;i<N;i++) 
			temp[i] = orimap[i].clone();
	}
}
