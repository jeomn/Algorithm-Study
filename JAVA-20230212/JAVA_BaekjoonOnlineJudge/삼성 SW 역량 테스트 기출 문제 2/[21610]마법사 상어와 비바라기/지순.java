import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_21610_마법사상어와비바라기 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> list;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static boolean[][] visited;
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
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list.add(new Point(N-1, 0));
		list.add(new Point(N-1, 1));
		list.add(new Point(N-2, 0));
		list.add(new Point(N-2, 1));
		
		
		for(int i=0;i<M;i++) {
			visited = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			
			move(d, s);
		}
		
		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}
	
	private static void move(int d, int s) {
		for(int i=0;i<list.size();i++) {
			Point p = list.get(i);
			
			int nx = (p.x + N +dx[d]*(s%N))%N;
			int ny = (p.y + N +dy[d]*(s%N))%N;
			
			p.x = nx;
			p.y = ny;
			
			map[nx][ny]++;
			visited[nx][ny] = true;
		}
		
		for(Point p: list) {
			int cnt = 0;
			for(int i=1;i<8;i+=2) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(map[nx][ny] > 0) cnt++;
			}
			
			map[p.x][p.y] += cnt; 
		}
		
		list.clear();
		
		for(int i=0;i<N;i++ ) {
			for(int j=0;j<N;j++) {
				if(map[i][j]>=2 && !visited[i][j]) {
					map[i][j] -= 2;
					list.add(new Point(i, j));
				}
			}
		}
	}
}
