import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구이동 {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		while(true) {
			visited = new boolean[N][N];
			boolean isMove = false;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i][j]) continue;
					ArrayList<Point> list = bfs(i, j, new ArrayList<Point>());
					if(list.size() > 1) {
						move(list);
						isMove = true;
					}
				}
			}
			
			if(isMove)
				answer++;
			else
				break;
		}
		System.out.println(answer);
	}
	
	public static void move(ArrayList<Point> list) {
		int sum = 0;
		
		for(Point p : list) {
			sum += map[p.x][p.y];
		}
		
		int avg = sum / list.size();
		
		for(Point p : list) {
			map[p.x][p.y]= avg; 
		}
	}
	
	public static ArrayList<Point> bfs(int x, int y, ArrayList<Point> list) {
		Queue<Point> queue = new LinkedList<>();
		list.add(new Point(x, y));
		queue.add(new Point(x, y));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				 
				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
				
				int diff = Math.abs(map[p.x][p.y]- map[nx][ny]);
				
				if(diff>=L && diff<=R) {
					list.add(new Point(nx, ny));
					queue.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		
		return list;
	}
}
