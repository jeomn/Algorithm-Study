import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, minCnt = Integer.MAX_VALUE;
	static int[][] office, officeSub;
	static ArrayList<Camera> cctvList;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][][] cctvDirection = new int[][][]{
		{{3}, {0}, {1}, {2}},
		{{2, 3}, {0, 1}},
		{{0, 3}, {3, 1}, {1, 2}, {2, 0}},
		{{2, 0, 3}, {0, 3, 1}, {3, 1, 2}, {1, 2, 0}},
		{{0, 1, 2, 3}}
	};
	static class Camera{
		int x, y, n;
		Camera(int x, int y, int n){
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		cctvList = new ArrayList<>();
		int blind = N*M;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				int temp = Integer.parseInt(st.nextToken());
				office[r][c] = temp;
				
				if(temp == 0) continue;
				blind--;
				if(temp == 6) continue;
				cctvList.add(new Camera(r, c, office[r][c]-1));
			}
		}
		
		officeSub = new int[N][M];
		copyOffice(office, officeSub);
		setCctv(0, 0, blind);
		
		System.out.println(minCnt);
	}


	private static void setCctv(int cnt, int idx, int blind) {
		if(cnt == cctvList.size()) {
			minCnt = Math.min(minCnt, blind);
			return;
		}
		if(idx >= cctvList.size()) return;
		
		int[][] officeTemp = new int[N][M];
		copyOffice(officeSub, officeTemp);
		Camera c = cctvList.get(idx);
		for(int i=0; i<cctvDirection[c.n].length; i++) {
			int bCnt = 0;
			for(int j=0; j<cctvDirection[c.n][i].length; j++) {
				int nx = c.x;
				int ny = c.y;
				int d = cctvDirection[c.n][i][j];
				while(true) {
					nx += dx[d];
					ny += dy[d];
					if(nx<0 || nx>=N || ny<0 || ny>=M) break;
					if(officeSub[nx][ny] == 0) {
						officeSub[nx][ny] = -1;
						bCnt++;
					} else if(officeSub[nx][ny] == 6) break;
					
				}
			}
			setCctv(cnt+1, idx+1, blind-bCnt);
			copyOffice(officeTemp, officeSub);
		}	
	}

	private static void copyOffice(int[][] origin, int[][] copy) {
		for(int r=0; r<N; r++) {
			System.arraycopy(origin[r], 0, copy[r], 0, M);
		}
	}
}
