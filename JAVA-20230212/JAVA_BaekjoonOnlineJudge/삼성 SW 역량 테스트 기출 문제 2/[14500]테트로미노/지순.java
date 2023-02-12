import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500_테트로미노 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][][] tetromino = {
			{{0, 0}, {0, 1}, {0, 2}, {1, 1}},
			{{0, 0}, {0, 1}, {0, 2}, {-1, 1}},
			{{0, 0}, {1, 0}, {2, 0}, {1, 1}},
			{{0, 0}, {1, 0}, {2, 0}, {1, -1}}
	};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0;i<N;i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dfs(i, j, 0, 0);
				tet(i, j);
			}
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
			if(visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1, sum + map[nx][ny]);
			visited[nx][ny] = false;
		}
	}

	private static void tet(int x, int y) {
		for(int i=0;i<tetromino.length;i++) {
			int sum = 0;
			boolean check = false;
			for(int j=0;j<tetromino[i].length;j++) {
				int nx = x + tetromino[i][j][0];
				int ny = y + tetromino[i][j][1];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) {
					check = true;
					break;
				}
				sum += map[nx][ny];
			}
			if(!check) answer = Math.max(answer, sum);
		}
	}
}
