import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int R, C, catchSharkNum;
	static Shark[][] grid, newGrid;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static class Shark{
		int s, d, z;
		Shark(int s, int d, int z){
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		grid = new Shark[R][C];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			if(d==0 || d==1) s %= (2*(R-1));
			else s %= (2*(C-1));
			
			grid[r][c] = new Shark(s, d, z);
		}
		
		catchSharkNum = 0;
		for(int i=0; i<C; i++) {
			fishing(i);
			moveShark();
		}
		
		System.out.println(catchSharkNum);
	}

	private static void fishing(int c) {
		for(int r=0; r<R; r++) {
			if(grid[r][c] == null) continue;
			catchSharkNum += grid[r][c].z;
			grid[r][c] = null;
			return;
		}
		
	}

	private static void moveShark() {
		newGrid = new Shark[R][C];
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(grid[r][c] == null) continue;
				Shark s = grid[r][c];
				
				int nr = r, nc = c;
				for(int i=0; i<s.s; i++) {
					nr += dx[s.d];
					nc += dy[s.d];
					
					if(nr<0 || nr>=R || nc<0 || nc>=C) {
						s.d = changeDirection(s.d);
						nr += (dx[s.d]*2);
						nc += (dy[s.d]*2);
					}
				}
					
				Shark ns = newGrid[nr][nc];
				if(ns != null) {
					if(ns.z > s.z) continue;
				}
				newGrid[nr][nc] = s;
			}
		}
		grid = newGrid;
	}

	private static int changeDirection(int d) {
		if(d==0) return 1;
		else if(d==1) return 0;
		else if(d==2) return 3;
		else return 2; 
	}
}
