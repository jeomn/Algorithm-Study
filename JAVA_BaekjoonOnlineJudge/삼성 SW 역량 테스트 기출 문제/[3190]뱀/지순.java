import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_뱀_3190 {
	static int[][] map;
	static int N;
	static ArrayList<Point> list;
	static HashMap<Integer, String> info;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int time;
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		info = new HashMap<Integer, String>();
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			info.put(time, dir);
		}
		
		list = new ArrayList<>();
		list.add(new Point(0, 0));
		time = 0;
		
		solve();
		
		System.out.println(time);
	}
	
	private static void solve() {
		int dir = 0;
		int nx = 0;
		int ny = 0;
		
		while(true) {
			++time;
			nx += dx[dir];
			ny += dy[dir];

			if(nx<0 || nx>=N || ny<0 || ny>=N) return;
			
			for(Point p: list) {
				if(p.x == nx && p.y == ny)
					return;
			}
			
			if(map[nx][ny] == 1) {
				list.add(new Point(nx, ny));
				map[nx][ny] = 0;
			} else {
				list.add(new Point(nx, ny));
				list.remove(0);
			}
			
			
			if(info.containsKey(time)) {
				if(info.get(time).equals("D")) {
					dir = (dir+1)%4;
				} else {
					dir = (4+dir-1)%4;
				}
			}
		}
	}
}
