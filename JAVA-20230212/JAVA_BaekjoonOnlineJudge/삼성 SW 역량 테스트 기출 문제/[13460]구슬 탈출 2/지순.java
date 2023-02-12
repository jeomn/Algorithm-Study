import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_구슬탈출2_13460 {
	static int N;
	static int M;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][][][] check;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		Point point = new Point();
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					point.rx = i;
					point.ry = j;
				} else if(map[i][j] == 'B') {
					point.bx = i;
					point.by = j;
				}
			}
		}
		check = new boolean[N][M][N][M];
		bfs(point);
	}
	
	private static void bfs(Point pt) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(pt);

		while(!queue.isEmpty()) {
			Point p = queue.poll();
			check[p.rx][p.ry][p.bx][p.by] = true;
			
			if(p.cnt >= 10) {
				System.out.println(-1);
				return;
			}
			
			for(int i=0;i<4;i++) {
				int r_nx = p.rx;
				int r_ny = p.ry;
				while(true) {
					r_nx += dx[i];
					r_ny += dy[i];
					if(map[r_nx][r_ny] == '#') {
						r_nx -= dx[i];
						r_ny -= dy[i];
						break;
					}
					if(map[r_nx][r_ny] == 'O') {
//						System.out.println("red 탈출구 만남");
						break;
					}
				}
				
				int b_nx = p.bx;
				int b_ny = p.by;
				while(true) {
					b_nx += dx[i];
					b_ny += dy[i];
//					if(b_nx <0 || b_ny)
//					System.out.println(b_nx+", "+b_ny +"/ "+i);
					if(map[b_nx][b_ny] == '#') {
//						System.out.println("걸렸다");
						b_nx -= dx[i];
						b_ny -= dy[i];
						break;
					}
					if(map[b_nx][b_ny] == 'O') {
//						System.out.println("blue 탈출구 만남");
						break;
					}
				}
//				System.out.println(p.bx + " " + p.by);
//				System.out.println("마지막으로 나오는곳: "+b_nx+", "+b_ny);
				if(map[b_nx][b_ny] == 'O') continue;
				if(map[r_nx][r_ny] == 'O') {
//					System.out.println("체크가 안됨");
					System.out.println(p.cnt+1);
					return;
				}
				
				if(r_nx == b_nx && r_ny == b_ny) {
					if(i == 0) {
						if(p.rx>p.bx) r_nx += 1;
						else b_nx += 1;
					} else if(i==1) {
						if(p.ry > p.by) b_ny -= 1;
						else r_ny -= 1;
					} else if(i==2) {
						if(p.rx>p.bx) b_nx -= 1;
						else r_nx -= 1;
					} else if(i==3) {
						if(p.ry > p.by) r_ny += 1;
						else b_ny += 1;
					}
				}
				
				if(!check[r_nx][r_ny][b_nx][b_ny]) {
					queue.add(new Point(r_nx, r_ny, b_nx, b_ny, p.cnt+1));
				}
			}
		}
		System.out.println(-1);
	}
	
	
	
	private static class Point {
		int rx, ry, bx, by, cnt;
		
		public Point() {
			super();
		}

		Point(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
}
