import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_스타트택시_19238 {
	static int N, M, F;
	static int[][] map;
	static GuestPoint[] guest;
	static Point taxi;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int cnt;
	static class Point {
		int x, y, dis;
		Point(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
	static class GuestPoint implements Comparable<GuestPoint>{
		int sx, sy;
		int ex, ey;
		int time;
		GuestPoint(int sx, int sy, int ex, int ey, int time) {
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
			this.time = time;
		}
		@Override
		public int compareTo(GuestPoint o) {
			// TODO Auto-generated method stub
			if(this.time == o.time) {
				if(this.sx == o.sx)
					return this.sy - o.sy;
				return this.sx - o.sx;
			}
			return this.time-o.time;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		guest = new GuestPoint[M+2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		taxi = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken())-1;
			int sy = Integer.parseInt(st.nextToken())-1;
			int ex = Integer.parseInt(st.nextToken())-1;
			int ey = Integer.parseInt(st.nextToken())-1;
			guest[i+2] = new GuestPoint(sx, sy, ex, ey, 0);
			map[sx][sy] = i+2;
		}
		cnt = 0;
		for(int i=0;i<M;i++) {
			drive();
		}
		
		if(cnt == M)
			System.out.println(F);
		else
			System.out.println(-1);
	}
	
	private static void drive() { 
		int tx = taxi.x;
		int ty = taxi.y;
		
		if(map[tx][ty] > 0 && map[tx][ty]!=1) {
			GuestPoint gp = guest[map[tx][ty]];
			move(gp);
			return;
		}
		
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<GuestPoint> gList = new PriorityQueue<>();
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(tx, ty, 0));
		visited[tx][ty] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny] || map[nx][ny]==1) continue;
				
				if(map[nx][ny] == 0) {
					queue.add(new Point(nx, ny, p.dis+1));
				} else {
					GuestPoint gg = guest[map[nx][ny]];
					gg.time = p.dis+1;
					gList.add(gg);
				}
				visited[nx][ny] = true;
			}
		}
		
		if(gList.size() == 0) return;
		GuestPoint g = gList.poll();
		if(F-g.time <=0) return;
		F-=g.time;
		move(g);
	}
	
	private static void move(GuestPoint gp) {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> queue =new LinkedList<>();
		queue.add(new Point(gp.sx, gp.sy, 0));
		map[gp.sx][gp.sy] = 0;
		
		while(!queue.isEmpty()){
			Point p = queue.poll();
			
			if(p.dis >= F) {
				System.out.println(-1);
				System.exit(0);
			}
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx >=N||ny<0 || ny>=N) continue;
				if(visited[nx][ny] || map[nx][ny] == 1) continue;
				
				if(nx == gp.ex && ny == gp.ey) {
					taxi.x = nx;
					taxi.y = ny;
					cnt+=1;
					F += (p.dis+1);
					return;
				} 
				queue.add(new Point(nx, ny, p.dis+1));
				visited[nx][ny] = true;
			}
		}
	}

}
