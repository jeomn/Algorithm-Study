import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_연구소_14502 {
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Point> list;
	static int result = Integer.MIN_VALUE;
	private static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		list = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					list.add(new Point(i, j));
			}
		}
		
		comb(0);
		System.out.println(result);
	}
	
	private static void comb(int cnt) {
		if(cnt == 3) {
			spreadVirus();
			return;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					comb(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void spreadVirus() {
		int[][] temp = new int[N][M];
		for(int i=0;i<N;i++) {
			temp[i] = map[i].clone();
		}
		Queue<Point> queue = new LinkedList<>();
		queue.addAll(list);
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = p.x+dx[i];
				int ny = p.y+dy[i];
				if(nx<0 || nx >=N || ny<0 || ny>=M) continue;
				if(temp[nx][ny] != 0) continue;
				queue.add(new Point(nx, ny));
				temp[nx][ny] = 2;
			}
		}
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(temp[i][j] == 0) 
					cnt++;
			}
		}
		result = Math.max(result, cnt);
	}
}
