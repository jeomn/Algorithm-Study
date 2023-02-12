import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_테트로미노dfs_14500 {
	static int N;
	static int M;
	static int result = Integer.MIN_VALUE;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {-1,0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][][] tetromino = {
			{{0,0},{0,1},{0,2},{1,1}},
			{{0,0},{0,1},{0,2},{-1,1}},
			{{0,0},{1,0},{2,0},{1,1}},
			{{0,0},{1,0},{2,0},{1,-1}}
	};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = Integer.MIN_VALUE;
		
		map = new int[N][M];
		check = new boolean[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				check[n][m] = true;
				dfs(n, m, map[n][m], 1);
				check[n][m] = false;
				tet(n, m);
			}
		}
		System.out.println(result);
	}
	private static void dfs(int n, int m, int sum, int cnt) {
		if(cnt == 4) {
			result = Math.max(result, sum);
			return;
		}
		for(int i=0;i<4;i++) {
			int nx = n+dx[i];
			int ny = m+dy[i];
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
			if(check[nx][ny]) continue;
			check[nx][ny] = true;
			dfs(nx, ny, sum+map[nx][ny], cnt+1);
			check[nx][ny] = false;
			
		}
	}
	private static void tet(int n, int m) {
		for(int i=0;i<tetromino.length;i++) {
			int sum = 0;
			boolean check = false;
			for(int j=0;j<4;j++) {
				int nx = n+tetromino[i][j][0];
				int ny = m+tetromino[i][j][1];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) {
					check = true;
					break;
				}
				sum+=map[nx][ny];
			}
			if(!check)
				result = Math.max(result, sum);
		}
	}
}
