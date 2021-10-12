import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_인구이동_16234 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> list;
	
	static class Point{
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
		int ans = 0;
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0;m<N;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			boolean check = false;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						list = new ArrayList<Point>();
						bfs(i, j);
						if(list.size()>1) {
							change();
							check = true;
						}
					}
				}
			}
			if(!check)
				break;
			else
				ans++;
		}
		System.out.println(ans++);
	}
	
	private static void change() {
		int sum = 0;
		
		for(Point p: list) {
			sum += map[p.x][p.y];
		}
		
		int avg = sum / list.size();
		for(Point p: list) {
			map[p.x][p.y] = avg;
		}
//		System.out.println(Arrays.deepToString(map));
	}
	
	private static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<Point>();
		visited[x][y] = true;
		queue.offer(new Point(x, y));
		list.add(new Point(x, y));

		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) continue;

				int dis = Math.abs(map[p.x][p.y] - map[nx][ny]);

				if(dis>=L && dis<=R) {
					list.add(new Point(nx, ny));
					queue.offer(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
}
