import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_온풍기안녕_23289 {
	static int R, C, K;
	static int[][] map;
	static boolean[][][] wall;
	static ArrayList<Point> heaters;
	static ArrayList<Point> checks;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] setDir = { 2, 6, 0, 4 };
	static int[][] spreadPoint = { { 1, 2, 3 }, { 7, 6, 5 }, { 7, 0, 1 }, { 5, 4, 3 } };
	static int[] controlDir = {2, 4}; //오하, 온도 조절
	static class Point {
		int x, y, d;

		Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		wall = new boolean[R][C][4]; // 우좌상하
		heaters = new ArrayList<>();
		checks = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > 0 && num < 5)
					heaters.add(new Point(r, c, num));
				if (num == 5)
					checks.add(new Point(r, c, num));
			}
		}

		int W = Integer.parseInt(br.readLine());

		for (int w = 0; w < W; w++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());

			if (t == 0) {
				wall[x][y][2] = true;
				wall[x - 1][y][3] = true; 
			} else {
				wall[x][y][0] = true;
				wall[x][y + 1][1] = true;
			}
		}

		int chocolate = 0;
		while(chocolate <= 100) {
			spread();
			controlTemperature();
			decreaseTemperature();
			chocolate++;
			
			boolean stop = false;
			for(int i=0;i<checks.size();i++) {
				if(map[checks.get(i).x][checks.get(i).y] < K) {
					stop = true;
					break;
				}
			}
			
			if(!stop) {
				break;
			}
		}
		System.out.println(chocolate);
	}
	
	
	private static void decreaseTemperature() {
		int[] decreaseDir = {2, 4, 6, 0};
		
		int x =0, y=0;
		int d = 0;
		while(true) {
			x += dx[decreaseDir[d]];
			y += dy[decreaseDir[d]];
			
			if(x < 0 || x>=R || y< 0 || y>=C) {
				x -= dx[decreaseDir[d]];
				y -= dy[decreaseDir[d]];
				d++;
			} else {
				if(map[x][y]>0)
					map[x][y]--;
			}
			
			if(d == 4) break;
		}
	}
	private static void controlTemperature() {
		int[][] temp = new int[R][C];
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				for(int i=0;i<2;i++) {
					int nx = r + dx[controlDir[i]];
					int ny = c + dy[controlDir[i]];
					
					if(nx< 0|| nx>=R || ny<0 || ny>=C) continue;
					if(i == 0) { // 오른쪽
						if(wall[r][c][0]) continue;
					} else if(i==1) { //아래
						if(wall[r][c][3]) continue;
					}
					
					int num = Math.abs(map[r][c] - map[nx][ny])/4;
					if(map[r][c] > map[nx][ny]) {
						temp[r][c] -= num;
						temp[nx][ny] += num;
					}
					else {
						temp[nx][ny] -= num;
						temp[r][c] += num;
					}
				}
			}
		}
		
		for(int r=0;r<R;r++) {
			for(int c=0;c<C;c++) {
				map[r][c] += temp[r][c];
			}
		}
	}
	private static void spread() {
		for (int i = 0; i < heaters.size(); i++) {
			int x = heaters.get(i).x;
			int y = heaters.get(i).y;
			int dir = heaters.get(i).d - 1;
			boolean[][] visited = new boolean[R][C];
			Queue<Point> queue = new LinkedList<Point>();
			queue.add(new Point(x + dx[setDir[dir]], y + dy[setDir[dir]], 5));

			while (!queue.isEmpty()) {
				Point p = queue.poll();
				visited[p.x][p.y] = true;
				map[p.x][p.y] += p.d;
				if (p.d == 1) {
					continue;
				}

				for (int j = 0; j < 3; j++) {
					int nx = p.x + dx[spreadPoint[dir][j]];
					int ny = p.y + dy[spreadPoint[dir][j]];

					if (nx < 0 || nx >= R || ny < 0 || ny >= C)
						continue;
					if (visited[nx][ny])
						continue;
					if (!checkWall(p.x, p.y, dir, j)) {
						continue;
					}

					visited[nx][ny] = true;
					queue.add(new Point(nx, ny, p.d - 1));
				}
			}
		}
	}

	private static boolean checkWall(int x, int y, int dir, int j) {
		if (j == 1) { // 일직선으로 갈때
			if (!wall[x][y][dir]) {
				return true;
			}
		} else if (j == 0) {
			if (dir == 0) {
				if (!wall[x][y][2] && !wall[x - 1][y][0])
					return true;
			} else if (dir == 1) {
				if (!wall[x][y][2] && !wall[x - 1][y][1])
					return true;
			} else if (dir == 2) {
				if (!wall[x][y][1] && !wall[x][y - 1][2])
					return true;
			} else if (dir == 3) {
				if (!wall[x][y][1] && !wall[x][y - 1][3])
					return true;
			}
		} else if (j == 2) {
			if (dir == 0) {
				if (!wall[x][y][3] && !wall[x + 1][y][0])
					return true;
			} else if (dir == 1) {
				if (!wall[x][y][3] && !wall[x + 1][y][1])
					return true;
			} else if (dir == 2) {
				if (!wall[x][y][0] && !wall[x][y + 1][2])
					return true;
			} else if (dir == 3) {
				if (!wall[x][y][0] && !wall[x][y + 1][3])
					return true;
			}
		}
		return false;
	}
}
