import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static ArrayList<Node> clouds;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[][] grid;
	static boolean[][] isCloud, newCloud;
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		clouds = new ArrayList<>();
		makeRainCloud();
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			
			isCloud = new boolean[N][N];
			moveCloud(d, s);
			spellWaterCopyBug();
			clouds.clear();
			makeNewCloud();
		}
		
		int result = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				result += grid[r][c];
			}
		}
		System.out.println(result);
	}


	private static void makeRainCloud() {
		clouds.add(new Node(N-1, 0));
		clouds.add(new Node(N-1, 1));
		clouds.add(new Node(N-2, 0));
		clouds.add(new Node(N-2, 1));
	}


	private static void moveCloud(int d, int s) {
		s%=N;
		for(Node n : clouds) {
			int nr = (n.r+(dx[d]*s)+N)%N;
			int nc = (n.c+(dy[d]*s)+N)%N;
			
			isCloud[nr][nc] = true;
			grid[nr][nc]+=1;
			n.r = nr;
			n.c = nc;
		}
	}


	private static void spellWaterCopyBug() {
		int[] diagD = new int[]{1, 3, 5, 7};
		for(Node n: clouds) {
			int cnt=0;
			for(int d=0; d<4; d++) {
				int nr = n.r+dx[diagD[d]];
				int nc = n.c+dy[diagD[d]];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(grid[nr][nc]==0) continue;
				cnt++;
			}
			grid[n.r][n.c]+=cnt;
		}	
	}


	private static void makeNewCloud() {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(grid[r][c]<2) continue;
				if(isCloud[r][c]) continue;
				clouds.add(new Node(r, c));
				grid[r][c]-=2;
			}
		}
		
	}
}
