import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_어른상어_19237 {
	private static int N, M, K;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int[][] map;
	private static Smell[][] smell;
	private static Point[] shark;
	private static int[][][] order;
	private static int sharkNum;
	private static int time;
	
	static class Smell {
		int num, time;
		Smell(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
	
	static class Point {
		int x, y, dir, num;
		Point() {}
		Point(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		shark = new Point[M+1];
		order = new int[M+1][4][4];
		smell = new Smell[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num!=0) {
					shark[num] = new Point(i, j, num, 0);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=M;i++) {
			shark[i].dir = Integer.parseInt(st.nextToken())-1;
		}
		
		for(int i=1;i<=M;i++) {
			for(int j=0;j<4;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<4;k++) {
					order[i][j][k] = Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		solve();
		System.out.println(time>1000?-1:time);
	}

	private static void solve() {
		time=0;
		sharkNum = M;
		while(time<1001 && sharkNum!=1) {
			smell();
			move();
			decrease();
			time++;
		}
	}
	
	private static void smell() {
		for(Point s: shark) {
			if(s == null) continue;
			int x = s.x;
			int y = s.y;
			int num = s.num;
			
			if(smell[x][y] == null) {
				smell[x][y] = new Smell(num, K);
			} else {
				smell[x][y].num = num;
				smell[x][y].time = K;
			}
		}
	}
	
	private static void move() {
		for(Point s:shark) {
			if(s == null) continue;
			int x = s.x;
			int y = s.y;
			int num = s.num;
			int dir = s.dir;
			
			boolean check = false; // 4방향 모두 갈 수 없을때
			for(int i=0;i<4;i++) {
				int ndir = order[num][dir][i];
				int nx = x + dx[ndir];
				int ny = y + dy[ndir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				
				if(smell[nx][ny] == null) { // 냄새 없음
					if(map[nx][ny] == 0) { // 상어도 없음
						map[x][y] = 0;
						map[nx][ny] = num;
						s.x = nx;
						s.y = ny;
						s.dir = ndir;
					} else {
						if(map[nx][ny] < num) {
							shark[num] = null;
							sharkNum-=1;
							map[x][y] = 0;
						} else {
							shark[map[nx][ny]] = null;
							sharkNum-=1;
							map[x][y] = 0;
							map[nx][ny] = num;
							s.x = nx;
							s.y = ny;
							s.dir = ndir;
						}
					}
					check = true;
					break;
				} 
			}
			if(check) continue;
			
			for(int i=0;i<4;i++) {
				int ndir = order[num][dir][i];
				int nx = x + dx[ndir];
				int ny = y + dy[ndir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(smell[nx][ny].num == num) {
					map[x][y] = 0;
					map[nx][ny] = num;
					s.x = nx;
					s.y = ny;
					s.dir = ndir;
					break;
				}
			}
		}
	}
	
	private static void decrease() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(smell[i][j] != null) {
					if(smell[i][j].time == 1) {
						smell[i][j] = null;
					} else {
						smell[i][j].time -= 1;
					}
				}
			}
		}
	}
}
