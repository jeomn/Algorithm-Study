import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] grid;
	static boolean[][] isCloud;
	static ArrayList<Cloud> cList;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static class Cloud{
		int x, y;
		Cloud(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		cList = new ArrayList<>();
		cList.add(new Cloud(N-1, 0));
		cList.add(new Cloud(N-1, 1));
		cList.add(new Cloud(N-2, 0));
		cList.add(new Cloud(N-2, 1));
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			
			moveCloud(d, s);
			setWater();
			cList.clear();
			setWaterCopyBug();
			setCloud();
		}
		
		int sumWater = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sumWater += grid[r][c];
			}
		}

		System.out.println(sumWater);
	}

	private static void moveCloud(int d, int s) {
		for(Cloud c: cList) {
			c.x  = (c.x+dx[d]*s)%N;
			c.y  = (c.y+dy[d]*s)%N;
			
			if(c.x <0) c.x = (c.x+N)%N;
			if(c.y <0) c.y = (c.y+N)%N;
		}
	}

	private static void setWater() {
		isCloud = new boolean[N][N];
		for(Cloud c: cList) {
			grid[c.x][c.y] += 1;
			isCloud[c.x][c.y] = true;
		}
	}

	private static void setWaterCopyBug() {
		int[] diaOrder = new int[] {1, 3, 5, 7};
		int nr, nc;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!isCloud[r][c]) continue;
				
				int cnt = 0;
				for(int d=0; d<4; d++) {
					nr = r+dx[diaOrder[d]];
					nc = c+dy[diaOrder[d]];
					
					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
					if(grid[nr][nc] == 0) continue;
					cnt++;
				}
				grid[r][c] += cnt;
			}
		}
	}

	private static void setCloud() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(isCloud[r][c]) continue;
				if(grid[r][c] < 2) continue;
				cList.add(new Cloud(r, c));
				grid[r][c]-=2;
			}
		}
	}

}
