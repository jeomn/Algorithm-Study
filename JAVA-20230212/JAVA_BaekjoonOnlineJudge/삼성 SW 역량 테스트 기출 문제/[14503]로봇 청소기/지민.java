/********************
* 20211006
* 정신...똑바로...차려라.....
********************/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, maxCnt;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static class Robot{
		int x, y, d;
		Robot(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		Robot robot = new Robot(x, y, d);
		map = new int[N][M];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		map[x][y] = 2;
		cleanArea(robot, 1);
		System.out.println(maxCnt);
	}

	private static void cleanArea(Robot n, int cnt) {
		if(cnt < maxCnt) return;
		int nd = n.d;
		for(int idx=0; idx<4; idx++) {
			nd  = (nd+3)%4;
			int nx = n.x+dx[nd];
			int ny = n.y+dy[nd];
			
			if(nx<0 || nx >=N || ny<0 || ny>=M) continue;
			if(map[nx][ny] == 0) {
				map[nx][ny] = 2;
				cleanArea(new Robot(nx, ny, nd), cnt+1);
			}
			
			if(idx==3) {
				nx = n.x-dx[nd];
				ny = n.y-dy[nd];
				
				if(map[nx][ny] == 1) {
					maxCnt = Math.max(maxCnt, cnt);
					return;
				}
				
				cleanArea(new Robot(nx, ny, n.d), cnt);
			}
		}
	}
}
