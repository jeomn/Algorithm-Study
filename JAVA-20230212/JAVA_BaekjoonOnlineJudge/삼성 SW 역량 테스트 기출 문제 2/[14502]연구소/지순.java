import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14502_연구소 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> virus;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int answer = 0;
	
	private static class Point {
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
		map = new int[N][M];
		virus = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					virus.add(new Point(i, j));
			}
		}
		
		comb(0, 0);
		System.out.println(answer);
	}
	
	private static void comb(int cnt, int idx) {
		if(cnt == 3) {
			spreadVirus();
			return;
		}
		for(int i=idx;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] != 0) continue;
				map[i][j] = 1;
				comb(cnt+1, i);
				map[i][j] = 0;
			}
		}
	}
	
	private static void spreadVirus() {
		int[][] temp = new int[N][M];
		for(int i=0;i<N;i++) 
			temp[i] = map[i].clone();
		
		Queue<Point> queue = new LinkedList<>();
		queue.addAll(virus);
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(temp[nx][ny]!=0) continue;
				temp[nx][ny] = 2;
				queue.add(new Point(nx, ny));
			}
		}
		
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(temp[i][j] == 0) cnt++;
			}
		}
		answer = Math.max(answer, cnt);
	}
}
