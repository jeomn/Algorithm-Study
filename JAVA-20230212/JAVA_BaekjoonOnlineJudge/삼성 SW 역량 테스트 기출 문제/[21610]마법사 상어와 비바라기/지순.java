import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_마법사상어와비바라기_21610 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> list;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	private static class Point {
		int x, y;
		Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new ArrayList<>();
		list.add(new Point(N-1, 0));
		list.add(new Point(N-1, 1));
		list.add(new Point(N-2, 0));
		list.add(new Point(N-2, 1));
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			visited = new boolean[N][N];
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			solve(d, s);
		}
		
		int result = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}
	
	private static void solve(int d, int s) {
		for(int i=0;i<list.size();i++) {
			Point  p = list.get(i);
			int nx = (p.x + N + dx[d]*(s%N))%N;
			int ny = (p.y + N + dy[d]*(s%N))%N;
			p.x = nx;
			p.y = ny;
			map[nx][ny] += 1;
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
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] >= 2 && !visited[i][j]) {
					map[i][j] -= 2;
					list.add(new Point(i, j));
				}
			}
		}
	}
	
	private static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
