import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, sandAmount;
	static int[][] grid;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static double[][][] sandDirc = {
			{{-1, 1, 0.01}, {1, 1, 0.01}, {-2, 0, 0.02}, {2, 0, 0.02}, {-1, 0, 0.07}, {1, 0, 0.07}, {-1, -1, 0.1}, {1, -1, 0.1}, {0, -2, 0.05}, {0, -1, 0.55}},
			{{-1, -1, 0.01}, {-1, 1, 0.01}, {0, -2, 0.02}, {0, 2, 0.02}, {0, -1, 0.07}, {0, 1, 0.07}, {1, -1, 0.1}, {1, 1, 0.1}, {2, 0, 0.05}, {1, 0, 0.55}},
			{{-1, -1, 0.01}, {1, -1, 0.01}, {-2, 0, 0.02}, {2, 0, 0.02}, {-1, 0, 0.07}, {1, 0, 0.07}, {-1, 1, 0.1}, {1, 1, 0.1}, {0, 2, 0.05}, {0, 1, 0.55}},
			{{1, -1, 0.01}, {1, 1, 0.01}, {0, -2, 0.02}, {0, 2, 0.02}, {0, -1, 0.07}, {0, 1, 0.07}, {-1, -1, 0.1}, {-1, 1, 0.1}, {-2, 0, 0.05}, {-1, 0, 0.55}},
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(br.readLine());
		
		grid = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r = N/2, c = N/2, d=0;
		for(int i=1; i<=N; i++) {
			int moveCnt = 0, changeDirection = 0;
			while(moveCnt!= i && changeDirection!=2) {
				if(r==0 && c==0) break;
				int nr = r+dx[d];
				int nc = c+dy[d];
				moveCnt++;
				moveSand(nr, nc, d);
				
				if(moveCnt==i) {
					changeDirection++;
					d = (d+1)%4;
					moveCnt=0;
				}
				r = nr;
				c = nc;
			}
		}
		System.out.println(sandAmount);
	}

	private static void moveSand(int r, int c, int d) {
		int originSand = grid[r][c];
		int restSand = originSand;
		for(int i=0; i<10; i++) {
			int moveSand = (int)(originSand*sandDirc[d][i][2]);
			if(i==9) moveSand = restSand;
			
			int nr = r+(int)sandDirc[d][i][0];
			int nc = c+(int)sandDirc[d][i][1];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N) sandAmount+=moveSand;
			else grid[nr][nc] += moveSand;
			restSand-=moveSand;
		}
		grid[r][c]=0;
	}
}
